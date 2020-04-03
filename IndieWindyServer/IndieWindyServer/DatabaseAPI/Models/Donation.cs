using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("donation")]
    public class Donation : BaseEntity
    {
        [Key]
        [Column("id", Order=1)]
        public int Id { get; set; }
        
        [Column("app_user_id")]
        public int AppUserId { get; set; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Column("artist_id")]
        public int ArtistId { get; set; }
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }
        
        [Column("amount")]
        public int Amount { get; set; }
        
        [Column("date")]
        public DateTime Date { get; set; }    
    }
}