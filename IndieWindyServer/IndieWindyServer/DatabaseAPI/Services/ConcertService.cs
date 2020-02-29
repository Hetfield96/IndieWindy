using System.Collections.Generic;
using System.Linq;
using DatabaseAPI.Models;

namespace DatabaseAPI.Services
{
    public class ConcertService : DatabaseBaseService<Concert>
    {
        public ConcertService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public List<Concert> FindByName(string query)
        {
            var concerts = _indieWindyDb.Concert.AsEnumerable();
            return concerts.Where(a => SearchService.StartsWith(a.Name, query)).ToList();
        }
    }
}