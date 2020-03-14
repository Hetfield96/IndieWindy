using DatabaseAPI.Models;

namespace DatabaseAPI.Services
{
    public class UserConcertLinkService: DatabaseBaseService<UserConcertLink>
    {
        public UserConcertLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) {}
    }
}