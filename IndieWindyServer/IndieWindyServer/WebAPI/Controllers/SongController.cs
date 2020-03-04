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
        
        // TODO
        // [HttpGet]
        // [Route("findWithAdded/{query}/{userId}")]
        // public async Task<List<(Song s, int AppUserId)>> FindByNameWithAdded(string query, int userId)
        // {
        //     query = "Л";
        //     var res = await _songService.FindByNameWithAdded(query, userId);
        //     return res;
        // }

        [HttpGet]
        [Route("find/{query}")]
        public async Task<List<Song>> FindByName(string query)
        {
            var res = await _songService.FindByName(query);
            return res;
        }
    }
}