using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DatabaseAPI.Services
{
    public class DatabaseBaseService<T> where T : class
    {
        protected readonly IndieWindyDbContext _indieWindyDb;

        public DatabaseBaseService(IndieWindyDbContext indieWindyDb)
        {
            _indieWindyDb = indieWindyDb;
        }

        /// <summary>
        /// Made not implemented for catching error
        /// when user forgot to override this
        /// </summary>
        /// <returns></returns>
        public List<T> GetAll()
        {
            return _indieWindyDb.Set<T>().ToList();
        }

        /// <summary>
        /// Returns an item that was inserted in db
        /// </summary>
        /// <param name="item"></param>
        /// <returns></returns>
        public virtual async Task<T> AddNewItem(T item)
        {
            var itemInDb = await _indieWindyDb.AddAsync(item);
            await SaveChangesAsync();
            return itemInDb.Entity;
        }

        /// <summary>
        /// Returns item found by it's identifier
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public async Task<T> FindItemByIdAsync(string id)
        {
            return await _indieWindyDb.FindAsync<T>(id);
        }

        /// <summary>
        /// Returns true if delete of item is successful. Also updates the context
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public async Task<bool> DeleteItemById(string id)
        {
            var item = await FindItemByIdAsync(id);
            _indieWindyDb.Remove(item);
            return await SaveChangesAsync();
        }

        /// <summary>
        /// Returns true if updating context is successful
        /// </summary>
        /// <returns></returns>
        public async Task<bool> SaveChangesAsync()
        {
            try
            {
                await _indieWindyDb.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}