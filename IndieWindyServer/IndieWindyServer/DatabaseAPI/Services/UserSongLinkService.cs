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

        // private async Task<Song> GetSong(int userId, int songId)
        // {
        //     return await _indieWindyDb.Song
        // }
        
        // TODO make it not through separate requests but through one
        // Maybe return linkId here?
        public async Task<Boolean> IsSongAdded(int userId, int songId)
        {
            return await _indieWindyDb.UserSongLink
                .AnyAsync(l => l.AppUserId == userId && l.SongId == songId);
        }

        public async Task<Boolean> DeleteAdded(int userId, int songId)
        {
            try
            {
                UserSongLink link = new UserSongLink(userId, songId);
                _indieWindyDb.UserSongLink.Remove(link);
                await _indieWindyDb.SaveChangesAsync();
                return true;
            }
            catch (DbUpdateConcurrencyException e)
            {
                // No element was removed
                return false;
            }
        }
    }
}