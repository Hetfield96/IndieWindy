using System;
using System.ComponentModel.DataAnnotations;

namespace DatabaseAPI.Models
{
    public class Concert
    {
        [Key]
        public int Id { get; set; }
        public string Name { get; set; }
        public string ImageUrl { get; set; }
        public int Cost { get; set; }
        public DateTime StartTime { get; set; }
        public string Address { get; set; }
    }
}