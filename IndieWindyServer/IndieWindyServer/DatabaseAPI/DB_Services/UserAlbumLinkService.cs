using DatabaseAPI.Models;

namespace DatabaseAPI.DB_Services
{
    public class UserAlbumLinkService: DatabaseBaseService<UserAlbumLink>
    {
        public UserAlbumLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
    }
}