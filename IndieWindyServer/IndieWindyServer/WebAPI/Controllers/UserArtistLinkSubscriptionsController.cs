using System.Threading.Tasks;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;
using ServerCore.Models;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserArtistLinkSubscriptionsController : ControllerBase
    {
        private readonly UserArtistLinkSubscriptionsService _userArtistLinkSubscriptionsService;

        public UserArtistLinkSubscriptionsController(UserArtistLinkSubscriptionsService userArtistLinkSubscriptionsService)
        {
            _userArtistLinkSubscriptionsService = userArtistLinkSubscriptionsService;
        }

        [HttpPost]
        [Route("add/{userId}/{artistId}")]
        public void Add(int userId, int artistId)
        {
            var item = new UserArtistLinkSubscriptions(userId, artistId);
            _userArtistLinkSubscriptionsService.AddNewItem(item);
        }
    }
}