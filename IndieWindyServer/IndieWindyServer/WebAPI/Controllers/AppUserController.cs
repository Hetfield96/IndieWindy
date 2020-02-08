using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI;
using Microsoft.AspNetCore.Mvc;
using ServerCore;
using ServerCore.Models;

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
        public IEnumerable<AppUser> GetAll()
        {
            return _appUserService.GetAll();
        }

        [HttpPost]
        [Route("register")]
        public async Task<AppUser> CreateAppUser(AppUser user)
        {
            return await _appUserService.AddNewItem(user);
        }

        [HttpPost]
        [Route("login")]
        public async Task<bool> Login(AppUser loginUser)
        {
            var user = await _appUserService.FindByName(loginUser.Name);
            if (user == null)
                return false;
            return PasswordHasherService.VerifyMd5Hash(loginUser.Password, user.Password);
        }
    }
}