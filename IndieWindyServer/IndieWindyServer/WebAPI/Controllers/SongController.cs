using System.Collections.Generic;
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
        [Route("find/{query}")]
        public List<Song> FindByName(string query)
        {
            var res = _songService.FindByName(query);
            return res;
        }
    }
}