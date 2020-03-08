using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class AlbumController : ControllerBase
    {
        private readonly AlbumService _albumService;

        public AlbumController(AlbumService albumService)
        {
            _albumService = albumService;
        }

        [HttpGet]
        [Route("find/{query}")]
        public async Task<List<Album>> FindByName(string query)
        {
            return await _albumService.FindByName(query);
        }

        [HttpGet]
        [Route("{userId}/{albumId}/songs")]
        public async Task<List<UserSongLink>> GetSongs(int userId, int albumId)
        {
            return await _albumService.GetSongs(albumId, userId);
        }
    }
}