using System.Threading.Tasks;
using DatabaseAPI.Models;

namespace DatabaseAPI.DB_Services
{
    public class UserArtistLinkSubscriptionsService : DatabaseBaseService<UserArtistLink>
    {
        public UserArtistLinkSubscriptionsService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }

        public async Task<UserArtistLink> AddNewItem(UserArtistLink userArtistLink)
        {
            return await base.AddNewItem(userArtistLink);
        }
    }
}