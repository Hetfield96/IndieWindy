using System.Collections.Generic;
using System.Linq;
using DatabaseAPI.Models;

namespace DatabaseAPI.DB_Services
{
    public class ArtistService : DatabaseBaseService<Artist>
    {
        public ArtistService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public List<Artist> FindByName(string query)
        {
            var artists = _indieWindyDb.Artist.AsEnumerable();
            return artists.Where(a => SearchService.Contains(a.Name, query)).ToList();
        }
    }
}