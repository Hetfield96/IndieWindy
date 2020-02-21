using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;
using ServerCore.Models;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserSongLinkAddedController : ControllerBase
    {
        private readonly UserSongLinkAddedService _userSongLinkAddedService;

        public UserSongLinkAddedController(UserSongLinkAddedService userSongLinkAddedService)
        {
            _userSongLinkAddedService = userSongLinkAddedService;
        }

        [HttpPost]
        [Route("add/{userId}/{songId}")]
        public void Add(int userId, int songId)
        {
            // TODO check
            var item = new UserSongLinkAdded(userId, songId);
            _userSongLinkAddedService.AddNewItem(item);
        }
    }
}