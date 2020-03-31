using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("artist_post_link")]
    public class ArtistPostLink : BaseEntity
    {
        [Key]
        [Column("artist_id", Order=1)]
        public int ArtistId { get; set; }
        
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }
        
        [Key]
        [Column("post_id", Order=2)]
        public int PostId { get; set; }
        [ForeignKey("PostId")]
        public Post Post { get; set; }
    }
}