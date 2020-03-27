using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI.Services
{
    public class DonationService : DatabaseBaseService<Donation>
    {
        public DonationService(IndieWindyDbContext indieWindyDb) : base(indieWindyDb) { }
    }
}