using System.ComponentModel.DataAnnotations;

namespace DatabaseAPI.Models
{
    public class AppUser : BaseEntity
    {
        [Key]
        public int Id { get; set; }
        public string Name { get; set; }
        public string Password { get; set; }
    }
}