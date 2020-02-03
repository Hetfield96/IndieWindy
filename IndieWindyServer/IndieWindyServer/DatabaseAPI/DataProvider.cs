using System.Collections.Generic;
using System.Linq;
using Microsoft.Extensions.Configuration;
using ServerCore;

namespace DatabaseAPI
{
    public class DataProvider<TEntity> where TEntity : class
    {
        private readonly string _connectionString;
        private readonly DbContext db;
        
        public DataProvider(IConfiguration configuration)
        {
            db = new DbContext(configuration);
            _connectionString = configuration.GetSection("DB")?["ConnectionStrings"];
        }
        
        public IEnumerable<TEntity> GetAll()
        {
            var res = db.Set<TEntity>().ToList();
            return res;
        }
    }
}