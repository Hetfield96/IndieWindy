using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserAlbumLinkController : ControllerBase
    {
        private readonly UserAlbumLinkService _userAlbumLinkService;

        public UserAlbumLinkController(UserAlbumLinkService userAlbumLinkService)
        {
            _userAlbumLinkService = userAlbumLinkService;
        }

        [HttpPost]
        [Route("add")]
        public async Task<UserAlbumLink> AddNewItem([FromBody] UserAlbumLink item)
        {
            return await _userAlbumLinkService.AddNewItem(item);
        }
        
        [HttpDelete]
        [Route("delete/{userId}/{albumId}")]
        public async Task<Boolean> DeleteAdded(int userId, int albumId)
        {
            return await _userAlbumLinkService.DeleteAdded(userId, albumId);
        }
    }
}