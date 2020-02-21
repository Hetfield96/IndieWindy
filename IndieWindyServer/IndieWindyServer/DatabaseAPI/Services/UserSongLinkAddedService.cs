using ServerCore.Models;

namespace DatabaseAPI.Services
{
    public class UserSongLinkAddedService: DatabaseBaseService<UserSongLinkAdded>
    {
        public UserSongLinkAddedService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
    }
}