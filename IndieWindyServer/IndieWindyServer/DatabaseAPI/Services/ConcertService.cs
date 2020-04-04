using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Dapper;
using DatabaseAPI.Models;
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
        
        public async Task<List<UserConcertLink>> GetNearest(int userId, string query)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                        from user_concert_link as link
                        right join concert c on link.concert_id = c.id and link.app_user_id = @user
                        where start_time > now() and lower(c.name) like @name 
                        order by c.start_time",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }
        
        public async Task<List<UserConcertLink>> GetNearestByArtist(int userId, string query)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                    from user_concert_link as link
                    right join concert c on link.concert_id = c.id and link.app_user_id = @user
                    where c.start_time > now() and c.id in 
                    (select distinct acl.concert_id from artist_concert_link acl
                    join artist a on acl.artist_id = a.id
                    where lower(a.name) like @name)
                    order by c.start_time;",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }
        
        public async Task<List<UserConcertLink>> GetBySubscription(int userId, string query)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                    from user_concert_link link
                    right join concert c on link.concert_id = c.id and app_user_id = @user
                    where lower(c.name) like @name and c.id in
                    (select distinct concert_id from artist_concert_link
                    where artist_id in
                    (select artist_id from user_artist_link
                    where app_user_id = @user))
                    order by c.start_time;",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }
        
        public async Task<List<UserConcertLink>> GetBySubscriptionByArtist(int userId, string query)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                    from user_concert_link link
                    right join concert c on link.concert_id = c.id and app_user_id = @user
                    where c.id in
                    (select distinct concert_id from artist_concert_link
                    where artist_id in
                    (select artist_id from user_artist_link
                    join artist a on user_artist_link.artist_id = a.id
                    where app_user_id = @user and lower(a.name) like @name))
                    order by c.start_time;",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }
        
        public async Task<List<UserConcertLink>> GetSaved(int userId, string query)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                    from user_concert_link link
                    right join concert c on link.concert_id = c.id and app_user_id = @user
                    where link.app_user_id is not null and lower(c.name) like @name 
                    order by c.start_time;",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }
        
        public async Task<List<UserConcertLink>> GetSavedByArtist(int userId, string query)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserConcertLink, Concert, UserConcertLink>(
                @"select link.*, c.*
                    from user_concert_link link
                    right join concert c on link.concert_id = c.id and app_user_id = @user
                    where link.app_user_id is not null and c.id in 
                    (select distinct acl.concert_id from artist_concert_link acl
                    join artist a on acl.artist_id = a.id
                    where lower(a.name) like @name)
                    order by c.start_time;",
                (link, concert) =>
                {
                    link.Concert = concert;
                    return link;
                },
                param: new {@name = $"{query.ToLower()}%", @user = userId});
            return res.ToList();
        }

        public async Task<List<UserArtistLink>> GetArtists(int userId, int concertId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserArtistLink, Artist, UserArtistLink>(
                @"select link.*, a.*
                    from user_artist_link link
                    right join artist_concert_link acl 
                    on link.artist_id = acl.artist_id and link.app_user_id = @user
                    join artist a on acl.artist_id = a.id
                    where acl.concert_id = @concert;",
                (link, artist) =>
                {
                    link.Artist = artist;
                    return link;
                },
                param: new {@user = userId, @concert = concertId});
            return res.ToList();
        }
    }
}