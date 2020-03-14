using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class UserConcertLinkService: DatabaseBaseService<UserConcertLink>
    {
        public UserConcertLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) {}
        
        public async Task<Boolean> DeleteAdded(int userId, int concertId)
        {
            try
            {
                UserConcertLink link = new UserConcertLink(userId, concertId);
                _indieWindyDb.UserConcertLink.Remove(link);
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