using System.Collections.Generic;
using DatabaseAPI;
using DatabaseAPI.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using ServerCore.Models;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class AppUserController : ControllerBase
    {
        private readonly DataProvider<AppUser> _dataProvider;
        private readonly AppUserRepository _userRepository;
        
        public AppUserController(IConfiguration configuration)
        {
            _dataProvider = new DataProvider<AppUser>(configuration);
            _userRepository = new AppUserRepository(configuration);
        }
        
        [HttpGet]
        public IEnumerable<AppUser> GetAll()
        {
            var res = _userRepository.GetAll();
            return res;
        }
    }
}