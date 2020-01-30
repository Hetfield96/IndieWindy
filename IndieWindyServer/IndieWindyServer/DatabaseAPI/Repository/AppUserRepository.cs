using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using Dapper;
using Microsoft.Extensions.Configuration;
using Npgsql;
using ServerCore.Models;

namespace DatabaseAPI.Repository
{
    public class AppUserRepository
    {
        private readonly string _connectionString = null;
        
        public AppUserRepository(IConfiguration configuration)
        {
            _connectionString = configuration.GetSection("DB")?["ConnectionStrings"];
        }
        
        public void Create(AppUser user)
        {
            using (IDbConnection db = new SqlConnection(_connectionString))
            {
                db.Query("insert into appuser (name, password) values (@name,  pgp_sym_encrypt(@password, 'AES_KEY'))",
                    new { name = user.Name, password = user.Password });
            }
        }

        // TODO password return as null
        public List<AppUser> GetAll()
        {
            List<AppUser> res;
            using (IDbConnection db = new NpgsqlConnection(_connectionString))
            {
                res = db.Query<AppUser>("select id, name, pgp_sym_decrypt(password::bytea, 'AES_KEY') from appuser").ToList();
            }

            return res;
        }
    }
}