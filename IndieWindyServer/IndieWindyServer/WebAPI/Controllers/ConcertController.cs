using System.Collections.Generic;
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
        [Route("find/{query}")]
        public List<Concert> FindByName(string query)
        {
            var res = _concertService.FindByName(query);
            return res;
        }
    }
}