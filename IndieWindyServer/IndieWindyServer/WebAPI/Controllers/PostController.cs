using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class PostController : ControllerBase
    {
        private readonly PostService _postService;

        public PostController(PostService postService)
        {
            _postService = postService;
        }

        [HttpGet]
        [Route("getBySubscription/{userId}")]
        public async Task<List<ArtistPostLink>> GetBySubscription(int userId)
        {
            return await _postService.GetBySubscription(userId);
        }
    }
}