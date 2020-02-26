using System.Collections.Generic;
using System.Threading.Tasks;
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
        public string Index()
        {
            return "ArtistController";
        }

        [HttpGet]
        [Route("all")]
        public IEnumerable<Artist> GetAll()
        {
            return _artistService.GetAll();
        }


        [HttpGet]
        [Route("getByName/{query}")]
        public async Task<Artist> GetByName(string query)
        {
            var res = await _artistService.FindByName(query);
            return res;
        }
    }
}