using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    public class UserConcertLink : BaseEntity
    {
        [Key]
        [Column(Order=1)]
        public int AppUserId { get; set; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column(Order=2)]
        public int ConcertId { get; set; }
        [ForeignKey("ConcertId")]
        public Concert Concert { get; set; }
    }
}