using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserSongLinkController : ControllerBase
    {
        private readonly UserSongLinkService _userSongLinkService;

        public UserSongLinkController(UserSongLinkService userSongLinkService)
        {
            _userSongLinkService = userSongLinkService;
        }

        [HttpPost]
        [Route("add")]
        public async Task<UserSongLink> AddNewItem([FromBody] UserSongLink item)
        {
            return await _userSongLinkService.AddNewItem(item);
        }

        [HttpDelete]
        [Route("delete/{userId}/{songId}")]
        public async Task<Boolean> Delete(int userId, int songId)
        {
            UserSongLink link = new UserSongLink(userId, songId);
            return await _userSongLinkService.DeleteItem(link);
        }

    }
}