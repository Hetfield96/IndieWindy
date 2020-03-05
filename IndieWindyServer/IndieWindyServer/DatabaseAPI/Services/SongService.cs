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
            // TODO change on where and like - dapper
            return songs.Where(s => SearchService.StartsWith(s.Name, query)).ToList();
        }
        
        public async Task<List<UserSongLink>> FindByNameWithAdded(string query, int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserSongLink, Song, UserSongLink>(
                @"select link.app_user_id, link.song_id, s.id, s.name, s.song_url, s.artist_id
                          from user_song_link as link
                          join song s on link.song_id = s.id
                          where s.name like @name and link.app_user_id = @user",
                (link, song) =>
                {
                    link.Song = song;
                    return link;
                },
                new {@name = $"{query}%", @user = userId});
            return res.ToList();
        }

    }
}