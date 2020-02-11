using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;
using ServerCore;
using ServerCore.Models;

namespace DatabaseAPI.Services
{
    public class AppUserService : DatabaseBaseService<AppUser>
    {
        private readonly IndieWindyDbContext _indieWindyDb;
        
        public AppUserService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb)
        {
            _indieWindyDb = indieWindyDb; // TODO What will happen if remove this line?
        }

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