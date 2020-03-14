using System;
using System.Linq;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class UserSongLinkService: DatabaseBaseService<UserSongLink>
    {
        public UserSongLinkService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }

    }
}