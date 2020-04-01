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
        
        [HttpGet]
        [Route("getLatest")]
        public async Task<List<ArtistPostLink>> GetLatest()
        {
            return await _postService.GetLatest();
        }
        
        [HttpGet]
        [Route("getSongs/{userId}/{postId}")]
        public async Task<List<UserSongLink>> GetSongs(int userId, int postId)
        {
            return await _postService.GetSongs(userId, postId);
        }
    }
}