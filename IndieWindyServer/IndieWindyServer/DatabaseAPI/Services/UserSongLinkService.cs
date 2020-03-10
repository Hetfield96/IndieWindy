using System;
using System.Linq;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class UserSongLinkService: DatabaseBaseService<UserSongLink>
    {
        public UserSongLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }

        public async Task<Boolean> DeleteAdded(int userId, int songId)
        {
            try
            {
                UserSongLink link = new UserSongLink(userId, songId);
                _indieWindyDb.UserSongLink.Remove(link);
                await _indieWindyDb.SaveChangesAsync();
                return true;
            }
            catch (DbUpdateConcurrencyException )
            {
                // No element was removed
                return false;
            }
        }
    }
}