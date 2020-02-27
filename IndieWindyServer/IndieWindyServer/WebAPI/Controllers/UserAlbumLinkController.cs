using System.Threading.Tasks;
using DatabaseAPI.DB_Services;
using DatabaseAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserAlbumLinkController : ControllerBase
    {
        private readonly UserAlbumLinkService _userAlbumLinkService;

        public UserAlbumLinkController(UserAlbumLinkService userAlbumLinkService)
        {
            _userAlbumLinkService = userAlbumLinkService;
        }

        [HttpPost]
        [Route("add")]
        public async Task<UserAlbumLink> AddNewItem([FromBody] UserAlbumLink item)
        {
            return await _userAlbumLinkService.AddNewItem(item);
        }
    }
}