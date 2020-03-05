using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("album")]
    public class Album : BaseEntity
    {
        [Key]
        [Column("id")]
        public int Id { get; set; }
        
        [Column("name")]
        public string Name { get; set; }
        
        [Column("image_url")]
        public string ImageUrl { get; set; }
        
        [Column("artist_id")]
        public int ArtistId { get; set; }
        
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }
    }
}