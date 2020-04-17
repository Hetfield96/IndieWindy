using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Dapper;
using DatabaseAPI.Models;
using Npgsql;

namespace DatabaseAPI.Services
{
    public class PostService : DatabaseBaseService<Post>
    {
        public PostService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }

        public async Task<List<ArtistPostLink>> GetBySubscription(int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<ArtistPostLink, Post, Artist, ArtistPostLink>(
                @"select apl.*, p.*, a.* from artist_post_link apl
                    join post p on apl.post_id = p.id
                    join artist a on apl.artist_id = a.id
                    where apl.artist_id in
                    (select user_artist_link.artist_id from user_artist_link 
                    where app_user_id = @user)
                    order by p.time desc;",
                (link, post, artist) =>
                {
                    link.PostId = post.Id;
                    link.Post = post;
                    link.ArtistId = artist.Id;
                    link.Artist = artist;
                    return link;
                },
                param: new {@user = userId});
            return res.ToList();
        }
        
        public async Task<List<ArtistPostLink>> GetLatest()
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);

            var res = await con.QueryAsync<ArtistPostLink, Post, Artist, ArtistPostLink>(
                @"select apl.*, p.*, a.* from latest_post lp
                        join post p on lp.post_id = p.id
                        join artist a on p.artist_id = a.id
                        join artist_post_link apl on apl.artist_id = a.id and apl.post_id = p.id
                        order by lp.id desc;",
                (link, post, artist) =>
                {
                    link.PostId = post.Id;
                    link.Post = post;
                    link.ArtistId = artist.Id;
                    link.Artist = artist;
                    return link;
                });
            return res.ToList();
        }
        
        public async Task<List<UserSongLink>> GetSongs(int userId, int postId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserSongLink, Song, Artist, Album, UserSongLink>(
                @"select usl.*, s.*, a.*, al.* 
                    from post_song_link psl
                    join song s on psl.song_id = s.id
                    join artist a on s.artist_id = a.id
                    join album al on s.artist_id = al.id
                    left join user_song_link usl on s.id = usl.song_id and usl.app_user_id = @userId
                    where psl.post_id = @postId;",
                (link, song, artist, album) =>
                {
                    song.Album = album;
                    song.Artist = artist;
                    link.Song = song;
                    return link;
                },
                param: new {userId, postId});
            return res.ToList();
        }
    }
}