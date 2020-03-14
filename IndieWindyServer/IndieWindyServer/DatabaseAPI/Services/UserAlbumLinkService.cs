using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class UserAlbumLinkService: DatabaseBaseService<UserAlbumLink>
    {
        public UserAlbumLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public async Task<Boolean> LinkExist(int userId, int albumId)
        {
            return await _indieWindyDb.UserAlbumLink
                .AnyAsync(l => l.AppUserId == userId && l.AlbumId == albumId);
        }
    }
}