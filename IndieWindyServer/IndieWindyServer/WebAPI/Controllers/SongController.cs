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
        
        [Route("find/{query}/{userId}")]
        public async Task<List<UserSongLink>> FindByName(string query, int userId)
        {
            return await _songService.FindByName(query, userId);
        }
    }
}