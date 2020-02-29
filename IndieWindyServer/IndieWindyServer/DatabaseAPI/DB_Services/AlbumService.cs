using System.Collections.Generic;
using System.Linq;
using DatabaseAPI.Models;

namespace DatabaseAPI.DB_Services
{
    public class AlbumService : DatabaseBaseService<Album>
    {
        public AlbumService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb)
        { }
        
        public List<Album> FindByName(string query)
        {
            var albums = _indieWindyDb.Album.AsEnumerable();
            return albums.Where(a => SearchService.Contains(a.Name, query)).ToList();
        }
    }
}