using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class SongController : ControllerBase
    {
        private readonly SongService _songService;

        public SongController(SongService songService)
        {
            _songService = songService;
        }
        
        [HttpGet]
        [Route("findWithAdded/{query}/{userId}")]
        public async Task<List<UserSongLink>> FindByNameWithAdded(string query, int userId)
        {
            return await _songService.FindByNameWithAdded(query, userId);
        }
        
        // TODO remove
        // [HttpGet]
        // [Route("find/{query}")]
        // public async Task<List<Song>> FindByName(string query)
        // {
        //     return await _songService.FindByName(query);
        // }
    }
}