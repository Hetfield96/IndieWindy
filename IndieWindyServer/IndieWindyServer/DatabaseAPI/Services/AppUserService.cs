using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;
using ServerCore.Models;

namespace DatabaseAPI.Services
{
    public class AppUserService : DatabaseBaseService<AppUser>
    {
        public AppUserService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }

        public override async Task<AppUser> AddNewItem(AppUser user)
        {
            user.Password = PasswordHasherService.GetMd5Hash(user.Password);
            return await base.AddNewItem(user);
        }

        public async Task<AppUser> FindByName(string name)
        {
            return await _indieWindyDb.Appuser.
                SingleOrDefaultAsync(u => u.Name.Equals(name));
        }
    }
}