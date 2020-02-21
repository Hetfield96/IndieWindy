using ServerCore.Models;

namespace DatabaseAPI.Services
{
    public class UserArtistLinkSubscriptionsService : DatabaseBaseService<UserArtistLinkSubscriptions>
    {
        public UserArtistLinkSubscriptionsService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
    }
}