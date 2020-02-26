using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI.DB_Services;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    
    [ApiController]
    [Route("[controller]")]
    public class AppUserController : ControllerBase
    {
        private readonly AppUserService _appUserService;

        public AppUserController(AppUserService appUserService)
        {
            _appUserService = appUserService;
        }

        [HttpGet]
        public string Index()
        {
            return "Hello! This is AppUserController!";
        }

        [HttpGet]
        [Route("all")]
        public IEnumerable<AppUser> GetAll()
        {
            return _appUserService.GetAll();
        }

        [HttpPost]
        [Route("register")]
        public async Task<AppUser> CreateAppUser([FromBody] AppUser user)
        {
            var duplicateCheck = await _appUserService.FindByName(user.Name);
            return duplicateCheck == null ? await _appUserService.AddNewItem(user) : null;
        }

        [HttpPost]
        [Route("login")]
        public async Task<AppUser> Login([FromBody] AppUser loginUser)
        {
            var user = await _appUserService.FindByName(loginUser.Name);
            if (user == null)
                return null;
            var res = PasswordHasherService.VerifyMd5Hash(loginUser.Password, user.Password);
            return res ? user : null;
        }
    }
}