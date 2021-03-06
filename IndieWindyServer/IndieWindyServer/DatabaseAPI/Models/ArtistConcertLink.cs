using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("artist_concert_link")]
    public class ArtistConcertLink : BaseEntity
    {
        [Key]
        [Column("artist_id", Order=1)]
        public int ArtistId { get; set; }
        
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }
        
        [Key]
        [Column("concert_id", Order=2)]
        public int ConcertId { get; set; }
        [ForeignKey("ConcertId")]
        public Concert Concert { get; set; }
    }
}