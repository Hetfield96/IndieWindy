using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using ServerCore;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ArtistController : Controller
    {


        [HttpGet]
        public IEnumerable<Artist> GetAll()
        {
//            var res = _databaseBaseService.GetAll();
//            return res;
            return null;
        }
        
    }
}