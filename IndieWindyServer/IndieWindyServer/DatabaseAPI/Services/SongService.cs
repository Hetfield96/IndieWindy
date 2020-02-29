using System.Collections.Generic;
using System.Linq;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class SongService : DatabaseBaseService<Song>
    {
        public SongService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb)
        { }
        
        public List<Song> FindByName(string query)
        {
            var songs = _indieWindyDb.Song
                .Include(s => s.Artist)
                .Include(s => s.Album)
                .AsEnumerable();
            return songs.Where(s => SearchService.StartsWith(s.Name, query)).ToList();
        }
    }
}