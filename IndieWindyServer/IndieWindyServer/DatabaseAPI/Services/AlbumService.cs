using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class AlbumService : DatabaseBaseService<Album>
    {
        public AlbumService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb)
        { }
        
        public async Task<List<Album>> FindByName(string query)
        {
            var albums = await _indieWindyDb.Album.ToListAsync();
            return albums.Where(a => SearchService.StartsWith(a.Name, query)).ToList();
        }
    }
}