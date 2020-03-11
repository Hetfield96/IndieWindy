using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class UserArtistLinkService : DatabaseBaseService<UserArtistLink>
    {
        public UserArtistLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
     
        public async Task<Boolean> DeleteAdded(int userId, int artistId)
        {
            try
            {
                UserArtistLink link = new UserArtistLink(userId, artistId);
                _indieWindyDb.UserArtistLink.Remove(link);
                await _indieWindyDb.SaveChangesAsync();
                return true;
            }
            catch (DbUpdateConcurrencyException )
            {
                // No element was removed
                return false;
            }
        }

        public async Task<Boolean> LinkExist(int userId, int artistId)
        {
            return await _indieWindyDb.UserArtistLink
                .AnyAsync(l => l.AppUserId == userId && l.ArtistId == artistId);
        }
    }
}