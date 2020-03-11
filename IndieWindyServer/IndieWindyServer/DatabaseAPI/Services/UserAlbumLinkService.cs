using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class UserAlbumLinkService: DatabaseBaseService<UserAlbumLink>
    {
        public UserAlbumLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public async Task<Boolean> DeleteAdded(int userId, int albumId)
        {
            try
            {
                UserAlbumLink link = new UserAlbumLink(userId, albumId);
                _indieWindyDb.UserAlbumLink.Remove(link);
                await _indieWindyDb.SaveChangesAsync();
                return true;
            }
            catch (DbUpdateConcurrencyException )
            {
                // No element was removed
                return false;
            }
        }

        public async Task<Boolean> linkExist(int userId, int albumId)
        {
            return await _indieWindyDb.UserAlbumLink
                .AnyAsync(l => l.AppUserId == userId && l.AlbumId == albumId);
        }
    }
}