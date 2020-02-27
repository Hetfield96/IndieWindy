using DatabaseAPI.Models;

namespace DatabaseAPI.DB_Services
{
    public class UserConcertLinkService: DatabaseBaseService<UserConcertLink>
    {
        public UserConcertLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) {}
    }
}