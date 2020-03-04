using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class ConcertService : DatabaseBaseService<Concert>
    {
        public ConcertService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public async Task<List<Concert>> FindByName(string query)
        {
            var concerts = await _indieWindyDb.Concert.ToListAsync();
            return concerts.Where(a => SearchService.StartsWith(a.Name, query)).ToList();
        }
    }
}