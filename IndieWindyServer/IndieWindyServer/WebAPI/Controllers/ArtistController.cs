using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;
using ServerCore.Models;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ArtistController : Controller
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