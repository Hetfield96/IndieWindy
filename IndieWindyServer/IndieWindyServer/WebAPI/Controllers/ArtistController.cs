using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ArtistController : ControllerBase
    {
        private readonly ArtistService _artistService;

        public ArtistController(ArtistService artistService)
        {
            _artistService = artistService;
        }

        [HttpGet]
        [Route("all")]
        public IEnumerable<Artist> GetAll()
        {
            return _artistService.GetAll();
        }
        
        [HttpGet]
        [Route("find/{query}")]
        public async Task<List<Artist>> FindByName(string query)
        {
            return await _artistService.FindByName(query);
        }
    }
}