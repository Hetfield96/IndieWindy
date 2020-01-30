using System.ComponentModel.DataAnnotations.Schema;

namespace ServerCore.Models
{
    [Table("appUser")]
    public class AppUser : BaseEntity
    {
        [Column("id")]
        public int Id { get; set; }
        
        [Column("name")]
        public string Name { get; set; }
        
        [Column("password")]
        public string Password { get; set; }
    }
}