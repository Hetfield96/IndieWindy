using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("user_song_link")]
    public class UserSongLink : BaseEntity
    {
        public UserSongLink(int appUserId, int songId)
        {
            AppUserId = appUserId;
            SongId = songId;
        }
        
        [Key]
        [Column("app_user_id", Order=1)]
        public int AppUserId { get; set; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column("song_id", Order=2)]
        public int SongId { get; set; }
        [ForeignKey("SongId")]
        public Song Song { get; set; }
    }
}