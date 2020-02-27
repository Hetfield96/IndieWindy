using System.Threading.Tasks;
using DatabaseAPI.DB_Services;
using DatabaseAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserArtistLinkController : ControllerBase
    {
        private readonly UserArtistLinkService _userArtistLinkService;

        public UserArtistLinkController(UserArtistLinkService userArtistLinkService)
        {
            _userArtistLinkService = userArtistLinkService;
        }

        [HttpPost]
        [Route("add")]
        public async Task<UserArtistLink> AddNewItem([FromBody] UserArtistLink item)
        {
            return await _userArtistLinkService.AddNewItem(item);
        }
    }
}