using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class ArtistService : DatabaseBaseService<Artist>
    {
        public ArtistService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public async Task<List<Artist>> FindByName(string query)
        {
            var artists = await _indieWindyDb.Artist.ToListAsync();
            return artists.Where(a => SearchService.StartsWith(a.Name, query)).ToList();
        }
    }
}