using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.DB_Services
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