using System.Collections.Generic;
using System.Threading.Tasks;
using System.Data.SqlClient;
using Dapper.Contrib.Extensions;
using Microsoft.Extensions.Configuration;
using Npgsql;

namespace DatabaseAPI
{
    public class DataProvider<TEntity> where TEntity : class
    {
        private readonly string _connectionString;
        
        public DataProvider(IConfiguration configuration)
        {
            _connectionString = configuration.GetSection("DB")?["ConnectionStrings"];
        }
        
        public IEnumerable<TEntity> GetAll()
        {
            using var connection = new NpgsqlConnection(_connectionString);
            return connection.GetAll<TEntity>();
        }
    }
}