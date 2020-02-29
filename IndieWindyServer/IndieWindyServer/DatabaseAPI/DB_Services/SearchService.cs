using System;
using System.Collections.Generic;
using DatabaseAPI.Models;

namespace DatabaseAPI.DB_Services
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
        public static bool Contains(string name, string query)
        {
            return name.IndexOf(query, StringComparison.OrdinalIgnoreCase) >= 0;
        }

        public List<Artist> SearchArtist(string query)
        {
            return _artistService.FindByName(query);
        }
        
        public List<Album> SearchAlbum(string query)
        {
            return _albumService.FindByName(query);
        }
        
        public List<Song> SearchSong(string query)
        {
            return _songService.FindByName(query);
        }
        
        public List<Concert> SearchConcert(string query)
        {
            return _concertService.FindByName(query);
        }
        
    }
}