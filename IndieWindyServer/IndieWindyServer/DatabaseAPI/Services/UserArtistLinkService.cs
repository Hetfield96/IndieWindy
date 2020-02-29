using DatabaseAPI.Models;

namespace DatabaseAPI.Services
{
    public class UserArtistLinkService : DatabaseBaseService<UserArtistLink>
    {
        public UserArtistLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
    }
}