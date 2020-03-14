using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ConcertController : ControllerBase
    {
        private readonly ConcertService _concertService;

        public ConcertController(ConcertService concertService)
        {
            _concertService = concertService;
        }

        [HttpGet]
        [Route("find/{query}/{userId}")]
        public async Task<List<UserConcertLink>> FindByName(string query, int userId)
        {
            return await _concertService.FindByName(query, userId);
        }
        
        [HttpGet]
        [Route("getNearest/{userId}")]
        public async Task<List<UserConcertLink>> GetNearest(int userId)
        {
            return await _concertService.GetNearest(userId);
        }

        // Returns UserConcertLinks for concerts where subscribed artists participate
        [HttpGet]
        [Route("getBySubscription/{userId}")]
        public async Task<List<UserConcertLink>> GetBySubscription(int userId)
        {
            return await _concertService.GetBySubscription(userId);
        }
        
        [HttpGet]
        [Route("{userId}/{concertId}/artists")]
        public async Task<List<UserArtistLink>> GetArtists(int userId, int concertId)
        {
            return await _concertService.GetArtists(userId, concertId);
        }
    }
}