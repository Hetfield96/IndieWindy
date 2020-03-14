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
    public class ConcertService : DatabaseBaseService<Concert>
    {
        public ConcertService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public async Task<List<UserConcertLink>> FindByName(string query, int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                        from user_concert_link as link
                        right join concert c on link.concert_id = c.id and link.app_user_id = @user
                        where lower(c.name) like @name",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }
        
        public async Task<List<UserConcertLink>> GetNearest(int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            // TODO date >= now
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                        from user_concert_link as link
                        right join concert c on link.concert_id = c.id and link.app_user_id = @user
                        order by c.start_time",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {@user = userId});
            return res.ToList();
        }
        
        public async Task<List<UserConcertLink>> GetBySubscription(int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                    from user_concert_link link
                    right join concert c on link.concert_id = c.id and app_user_id = @userId
                    where concert_id in
                    (select distinct concert_id from artist_concert_link
                    where artist_id in
                    (select artist_id from user_artist_link
                    where app_user_id = @userId));",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {userId});
            return res.ToList();
        }

        public async Task<List<UserArtistLink>> GetArtists(int userId, int concertId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserArtistLink, Artist, UserArtistLink>(
                @"select link.*, a.*
                    from user_artist_link link
                    right join artist_concert_link acl 
                    on link.artist_id = acl.artist_id and link.app_user_id = @userId
                    join artist a on acl.artist_id = a.id
                    where acl.concert_id = @concertId;",
                (link, artist) =>
                {
                    link.Artist = artist;
                    return link;
                },
                param: new {userId, concertId});
            return res.ToList();
        }
    }
}