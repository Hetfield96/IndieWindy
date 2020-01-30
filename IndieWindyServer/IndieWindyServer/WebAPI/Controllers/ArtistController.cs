using System.Collections.Generic;
using Dapper;
using DatabaseAPI;
using DatabaseAPI.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Npgsql;
using ServerCore.Models;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ArtistController : Controller
    {
        private ArtistRepository _artistRepository;
        private DataProvider<Artist> _dataProvider;

        public ArtistController(IConfiguration configuration)
        {
            _dataProvider = new DataProvider<Artist>(configuration);
            _artistRepository = new ArtistRepository(configuration);
        }

        [HttpGet]
        public IEnumerable<Artist> GetAll()
        {
            var res = _dataProvider.GetAll();
            return res;
        }
        
    }
}