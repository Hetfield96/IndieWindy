using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("user_artist_link")]
    public class UserArtistLink : BaseEntity
    {
        public UserArtistLink() { }

        public UserArtistLink(int appUserId, int artistId)
        {
            AppUserId = appUserId;
            ArtistId = artistId;
        }

        [Key]
        [Column("app_user_id", Order=1)]
        public int AppUserId { get; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column("artist_id", Order=2)]
        public int ArtistId { get; }
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }
    }
}