using DatabaseAPI.Models;

namespace DatabaseAPI.Services
{
    public class UserAlbumLinkService: DatabaseBaseService<UserAlbumLink>
    {
        public UserAlbumLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
    }
}