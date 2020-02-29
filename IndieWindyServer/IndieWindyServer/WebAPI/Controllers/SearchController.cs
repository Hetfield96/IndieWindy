using System.Collections.Generic;
using DatabaseAPI.DB_Services;
using DatabaseAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class SearchController : ControllerBase
    {
        private readonly SearchService _searchService;

        public SearchController(SearchService searchService)
        {
            _searchService = searchService;
        }

        [HttpGet]
        [Route("{query}")]
        public List<Artist> GetByName(string query)
        {
            var res = _searchService.SearchArtist(query);
            return res;
        }
        
        // TODO search by album, artist, concert and combine together...
    }
}