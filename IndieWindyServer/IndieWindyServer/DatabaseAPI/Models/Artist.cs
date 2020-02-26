using System.ComponentModel.DataAnnotations;

namespace DatabaseAPI.Models
{
    public class Artist
    {
        [Key]
        public int Id { get; set; }
        public string Name { get; set; }
        public string ImageUrl { get; set; }
    }
}