using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using DatabaseAPI.Models;

namespace DatabaseAPI.Services
{
    public class SearchService : DatabaseBaseService<BaseEntity>
    {
        private readonly ArtistService _artistService;
        private readonly AlbumService _albumService;
        private readonly SongService _songService;
        private readonly ConcertService _concertService;

        public SearchService(IndieWindyDbContext indieWindyDb, ArtistService artistService, SongService songService, 
            AlbumService albumService, ConcertService concertService) : base(indieWindyDb)
        {
            _artistService = artistService;
            _albumService = albumService;
            _songService = songService;
            _concertService = concertService;
        }
        public static bool StartsWith(string name, string query)
        {
            return name.IndexOf(query, StringComparison.OrdinalIgnoreCase) == 0;
        }

        public async Task<List<Artist>> SearchArtist(string query)
        {
            return await _artistService.FindByName(query);
        }
        
        public async Task<List<Album>> SearchAlbum(string query)
        {
            return await _albumService.FindByName(query);
        }
        
        public async Task<List<Song>> SearchSong(string query)
        {
            return await _songService.FindByName(query);
        }
        
        public async Task<List<Concert>> SearchConcert(string query)
        {
            return await _concertService.FindByName(query);
        }
        
    }
}