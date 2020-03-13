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

        [Route("find/{query}/{userId}")]
        public async Task<List<UserConcertLink>> FindByName(string query, int userId)
        {
            query = "б";
            return await _concertService.FindByName(query, userId);
        }
    }
}