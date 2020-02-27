using DatabaseAPI.Models;

namespace DatabaseAPI.DB_Services
{
    public class UserArtistLinkService : DatabaseBaseService<UserArtistLink>
    {
        public UserArtistLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
    }
}