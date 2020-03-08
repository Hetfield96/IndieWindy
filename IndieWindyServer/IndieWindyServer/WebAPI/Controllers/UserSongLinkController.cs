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

        // TODO not used now
        // [HttpGet]
        // [Route("isSongAdded/{userId}/{songId}")]
        // public async Task<Boolean> IsSongAdded(int userId, int songId)
        // {
        //     return await _userSongLinkService.IsSongAdded(userId, songId);
        // }
        
        [HttpDelete]
        [Route("delete/{userId}/{songId}")]
        public async Task<Boolean> DeleteAdded(int userId, int songId)
        {
            return await _userSongLinkService.DeleteAdded(userId, songId);
        }
    }
}