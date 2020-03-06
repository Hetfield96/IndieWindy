using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Dapper;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;
using Npgsql;

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
        
        public async Task<List<UserSongLink>> FindByNameWithAdded(string query, int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserSongLink, Song, Artist, Album, UserSongLink>(
                @"select link.*, s.*, a.*, al.*
                          from user_song_link as link
                          right join song s on link.song_id = s.id and link.app_user_id = @user
                          join artist a on s.artist_id = a.id
                          join album al on s.album_id = al.id
                          where lower(s.name) like @name",
                (link, song, artist, album) =>
                {
                    song.Artist = artist;
                    song.Album = album;
                    link.Song = song;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }

    }
}