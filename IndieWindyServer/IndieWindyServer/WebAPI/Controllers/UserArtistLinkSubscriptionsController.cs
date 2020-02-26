using System;
using DatabaseAPI.DB_Services;
using DatabaseAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserArtistLinkSubscriptionsController : ControllerBase
    {
        
        private readonly UserArtistLinkSubscriptionsService _userArtistLinkSubscriptionsService;

        public UserArtistLinkSubscriptionsController(UserArtistLinkSubscriptionsService userArtistLinkSubscriptionsService)
        {
            _userArtistLinkSubscriptionsService = userArtistLinkSubscriptionsService;
        }
        
        [HttpPost]
        [Route("add")]
        public async void Add([FromBody] UserArtistLink item)
        {
            await _userArtistLinkSubscriptionsService.AddNewItem(item);
        }

        [HttpGet]
        [Route("get")]
        public void GetSubscriptions()
        {
            var res =  _userArtistLinkSubscriptionsService.GetAll();
            Console.WriteLine("res");
        }
        
    }
}