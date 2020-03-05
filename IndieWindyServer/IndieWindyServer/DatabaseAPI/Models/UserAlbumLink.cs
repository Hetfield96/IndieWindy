using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("user_album_link")]
    public class UserAlbumLink : BaseEntity
    {
        [Key]
        [Column("app_user_id", Order=1)]
        public int AppUserId { get; set; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column("album_id", Order=2)]
        public int AlbumId { get; set; }
        [ForeignKey("AlbumId")]
        public Album Album { get; set; }
    }
}