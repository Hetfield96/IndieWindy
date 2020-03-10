using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Dapper;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;
using Npgsql;

namespace DatabaseAPI.Services
{
    public class ArtistService : DatabaseBaseService<Artist>
    {
        public ArtistService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public async Task<List<Artist>> FindByName(string query)
        {
            var artists = await _indieWindyDb.Artist.ToListAsync();
            return artists.Where(a => SearchService.StartsWith(a.Name, query)).ToList();
        }
        
        public async Task<List<UserAlbumLink>> GetAlbums(int artistId, int userId)
        {
            await using var con = new NpgsqlConnection(IndieWindyDbContext.ConnectionString);
            
            var res = await con.QueryAsync<UserAlbumLink, Album, UserAlbumLink>(
                @"select * from user_album_link as link
                        right join album a on link.album_id = a.id and link.app_user_id = @userId
                        where a.artist_id = @artistId",
                (link, album) =>
                {
                    link.Album = album;
                    return link;
                },
                param: new {artistId, userId});
            return res.ToList();
        }
    }
}