using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("user_concert_link")]
    public class UserConcertLink : BaseEntity
    {
        public UserConcertLink()
        {
        }

        public UserConcertLink(int appUserId, int concertId)
        {
            AppUserId = appUserId;
            ConcertId = concertId;
        }

        [Key]
        [Column("app_user_id", Order=1)]
        public int AppUserId { get; set; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column("concert_id", Order=2)]
        public int ConcertId { get; set; }
        [ForeignKey("ConcertId")]
        public Concert Concert { get; set; }
    }
}