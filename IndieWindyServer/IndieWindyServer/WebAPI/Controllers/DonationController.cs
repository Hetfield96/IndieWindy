using System;
using System.Threading.Tasks;
using DatabaseAPI.Models;
using DatabaseAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class DonationController: ControllerBase
    {
        private readonly DonationService _donationService;
        
        public DonationController(DonationService donationService)
        {
            _donationService = donationService;
        }
        
        [HttpPost]
        [Route("add")]
        public async Task<Donation> AddNewItem([FromBody] Donation item)
        {
            item.Date = DateTime.Now;
            return await _donationService.AddNewItem(item);
        }
    }
}