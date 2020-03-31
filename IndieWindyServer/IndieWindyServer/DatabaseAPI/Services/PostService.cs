using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Dapper;
using DatabaseAPI.Models;
using Npgsql;

namespace DatabaseAPI.Services
{
    public class PostService : DatabaseBaseService<Concert>
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
                    where app_user_id = @user);",
                (link, post, artist) =>
                {
                    link.Post = post;
                    link.Artist = artist;
                    return link;
                },
                param: new {@user = userId});
            return res.ToList();
        }
    }
}