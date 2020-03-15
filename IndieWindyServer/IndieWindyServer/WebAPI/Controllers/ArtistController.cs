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

        public string Index()
        {
            return "This is ArtistController";
        }
        
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
        
        [Route("find/{query}/{userId}")]
        public async Task<List<UserArtistLink>> FindByName(string query, int userId)
        {
            return await _artistService.FindByName(query, userId);
        }
        
        [HttpGet]
        [Route("{userId}/{artistId}/albums")]
        public async Task<List<UserAlbumLink>> GetAlbums(int userId, int artistId)
        {
            return await _artistService.GetAlbums(artistId, userId);
        }
    }
}