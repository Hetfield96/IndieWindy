using System.Collections.Generic;
using System.Linq;
using DatabaseAPI.Models;

namespace DatabaseAPI.Services
{
    public class SongService : DatabaseBaseService<Song>
    {
        public SongService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb)
        { }
        
        public List<Song> FindByName(string query)
        {
            var songs = _indieWindyDb.Song.AsEnumerable();
            return songs.Where(a => SearchService.StartsWith(a.Name, query)).ToList();
        }
    }
}