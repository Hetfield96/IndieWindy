using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class SongService : DatabaseBaseService<Song>
    {
        public SongService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb)
        { }
        
        public async Task<List<Song>> FindByName(string query)
        {
            var songs = await _indieWindyDb.Song
                .Include(s => s.Artist)
                .Include(s => s.Album)
                .ToListAsync();
            return songs.Where(s => SearchService.StartsWith(s.Name, query)).ToList();
        }
    }
}