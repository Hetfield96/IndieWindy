using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("post_song_link")]
    public class PostSongLink : BaseEntity
    {
        [Key]
        [Column("post_id", Order=1)]
        public int PostId { get; set; }
        [ForeignKey("PostId")]
        public Post Post { get; set; }
        
        [Key]
        [Column("song_id", Order=2)]
        public int SongId { get; set; }
        
        [ForeignKey("SongId")]
        public Artist Song { get; set; }
    }
}