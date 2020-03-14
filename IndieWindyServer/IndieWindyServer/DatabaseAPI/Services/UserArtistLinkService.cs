using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class UserArtistLinkService : DatabaseBaseService<UserArtistLink>
    {
        public UserArtistLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }

        public async Task<Boolean> LinkExist(int userId, int artistId)
        {
            return await _indieWindyDb.UserArtistLink
                .AnyAsync(l => l.AppUserId == userId && l.ArtistId == artistId);
        }
    }
}