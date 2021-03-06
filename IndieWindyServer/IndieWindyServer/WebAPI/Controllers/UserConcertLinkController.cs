using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserConcertLinkController : ControllerBase
    {
        private readonly UserConcertLinkService _userConcertLinkService;

        public UserConcertLinkController(UserConcertLinkService userConcertLinkService)
        {
            _userConcertLinkService = userConcertLinkService;
        }

        [HttpPost]
        [Route("add")]
        public async Task<UserConcertLink> AddNewItem([FromBody] UserConcertLink item)
        {
            return await _userConcertLinkService.AddNewItem(item);
        }
        
        [HttpDelete]
        [Route("delete/{userId}/{concertId}")]
        public async Task<Boolean> Delete(int userId, int concertId)
        {
            UserConcertLink link = new UserConcertLink(userId, concertId);
            return await _userConcertLinkService.DeleteItem(link);
        }
    }
}