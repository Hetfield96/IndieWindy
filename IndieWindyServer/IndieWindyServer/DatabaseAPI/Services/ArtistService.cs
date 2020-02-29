using System.Collections.Generic;
using System.Linq;
using DatabaseAPI.Models;

namespace DatabaseAPI.Services
{
    public class ArtistService : DatabaseBaseService<Artist>
    {
        public ArtistService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public List<Artist> FindByName(string query)
        {
            var artists = _indieWindyDb.Artist.AsEnumerable();
            return artists.Where(a => SearchService.StartsWith(a.Name, query)).ToList();
        }
    }
}