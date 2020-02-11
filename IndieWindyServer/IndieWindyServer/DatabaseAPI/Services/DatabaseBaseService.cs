using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ServerCore;

namespace DatabaseAPI.Services
{
    public class DatabaseBaseService<T> where T : class
    {
        private readonly IndieWindyDbContext _dbContext;

        protected DatabaseBaseService(IndieWindyDbContext indieWindyDb)
        {
            _dbContext = indieWindyDb;
        }

        /// <summary>
        /// Made not implemented for catching error
        /// when user forgot to override this
        /// </summary>
        /// <returns></returns>
        public List<T> GetAll()
        {
            return _dbContext.Set<T>().ToList();
        }

        /// <summary>
        /// Returns an item that was inserted in db
        /// </summary>
        /// <param name="item"></param>
        /// <returns></returns>
        public virtual async Task<T> AddNewItem(T item)
        {
            var itemInDb = await _dbContext.AddAsync(item);
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
            return await _dbContext.FindAsync<T>(id);
        }

        /// <summary>
        /// Returns true if delete of item is successful. Also updates the context
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public async Task<bool> DeleteItemById(string id)
        {
            var item = await FindItemByIdAsync(id);
            _dbContext.Remove(item);
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
                await _dbContext.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}