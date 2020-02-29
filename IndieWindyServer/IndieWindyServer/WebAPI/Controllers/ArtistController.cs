using System.Collections.Generic;
using DatabaseAPI.DB_Services;
using DatabaseAPI.Models;
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
        public List<Artist> FindByName(string query)
        {
            var res = _artistService.FindByName(query);
            return res;
        }
    }
}