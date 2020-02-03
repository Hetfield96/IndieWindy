using System.Collections.Generic;
using DatabaseAPI;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using ServerCore;

namespace WebAPI.Controllers
{
    
    [ApiController]
    [Route("[controller]")]
    public class AppUserController : ControllerBase
    {
        private readonly DataProvider<AppUser> _dataProvider;
        //private readonly AppUserRepository _userRepository;
        
        public AppUserController(IConfiguration configuration)
        {
            
            _dataProvider = new DataProvider<AppUser>(configuration);
            //_userRepository = new AppUserRepository(configuration);
        }
        
        [HttpGet]
        public IEnumerable<AppUser> GetAll()
        {
            var res = _dataProvider.GetAll();
            return res;
        }
    }
}