using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("user_album_link")]
    public class UserAlbumLink : BaseEntity
    {
        public UserAlbumLink() {}

        public UserAlbumLink(int userId, int albumId)
        {
            AppUserId = userId;
            AlbumId = albumId;
        }
        
        [Key]
        [Column("app_user_id", Order=1)]
        public int AppUserId { get; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column("album_id", Order=2)]
        public int AlbumId { get; }
        [ForeignKey("AlbumId")]
        public Album Album { get; set; }
    }
}