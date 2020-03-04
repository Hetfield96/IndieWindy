using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Runtime.CompilerServices;

namespace DatabaseAPI.Models
{
    public class UserSongLink : BaseEntity
    {
        public UserSongLink(int appUserId, int songId)
        {
            AppUserId = appUserId;
            SongId = songId;
        }
        
        [Key]
        [Column(Order=1)]
        public int AppUserId { get; set; }
        [ForeignKey("AppUserId")]
        public AppUser AppUser { get; set; }
        
        [Key]
        [Column(Order=2)]
        public int SongId { get; set; }
        [ForeignKey("SongId")]
        public Song Song { get; set; }
    }
}