using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("donation")]
    public class Donation : BaseEntity
    {
        [Key]
        [Column("app_user_id", Order=1)]
        public int AppUserId { get; set; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column("artist_id", Order=2)]
        public int ArtistId { get; set; }
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }
        
        [Column("amount")]
        public int Amount { get; set; }
        
        [Column("date")]
        public DateTime Date { get; set; }    
    }
}