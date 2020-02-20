using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ServerCore.Models;

namespace DatabaseAPI.Services
{
    public class ArtistService : DatabaseBaseService<Artist>
    {
        public ArtistService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
        
        public async Task<Artist> FindByName(string name)
        {
            return await _indieWindyDb.Artist.
                SingleOrDefaultAsync(a => a.Name.Equals(name));
        }
    }
}