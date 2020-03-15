using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Dapper;
using DatabaseAPI.Models;
using DatabaseAPI.Utils;
using Microsoft.EntityFrameworkCore;
using Npgsql;

namespace DatabaseAPI.Services
{
    public class AlbumService : DatabaseBaseService<Album>
    {
        public AlbumService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb)
        { }
        
        public async Task<List<UserAlbumLink>> FindByName(string query, int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserAlbumLink, Album, Artist, UserAlbumLink>(
                @"select link.*, al.*, a.*
                        from user_album_link as link
                        right join album al on link.album_id = al.id and link.app_user_id = @user
                        join artist a on al.artist_id = a.id
                        where lower(al.name) like @name",
                (link, album, artist) =>
                {
                    album.Artist = artist;
                    link.Album = album;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }
        
        public async Task<List<UserAlbumLink>> FindByNameLinked(string query, int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            // TODO sort
            var res = await con.QueryAsync<UserAlbumLink, Album, Artist, UserAlbumLink>(
                @"select link.*, al.*, a.*
                        from user_album_link as link
                        right join album al on link.album_id = al.id and link.app_user_id = @user
                        join artist a on al.artist_id = a.id
                        where lower(al.name) like @name and link.app_user_id is not null",
                (link, album, artist) =>
                {
                    album.Artist = artist;
                    link.Album = album;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }

        public async Task<List<UserSongLink>> GetSongs(int albumId, int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserSongLink, Song, Artist, Album, UserSongLink>(
                @"select * from user_song_link as link
                        right join song s on link.song_id = s.id and link.app_user_id = @userId
                        join artist a on s.artist_id = a.id
                        join album al on s.album_id = al.id
                        where s.album_id = @albumId",
                (link, song, artist, album) =>
                {
                    album.Artist = artist;
                    song.Album = album;
                    song.Artist = artist;
                    link.Song = song;
                    return link;
                },
                param: new {albumId, userId});
            return res.ToList();
        }
    }
}