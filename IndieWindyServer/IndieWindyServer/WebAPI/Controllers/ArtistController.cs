using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using ServerCore.Models;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ArtistController : Controller
    {
        
        [HttpGet]
        public IEnumerable<Artist> GetAll()
        {
            throw new NotImplementedException();
        }
        
    }
}