using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    public class UserArtistLink : BaseEntity
    {
        [Key]
        [Column(Order=1)]
        public int AppUserId { get; set; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column(Order=2)]
        public int ArtistId { get; set; }
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }
    }
}