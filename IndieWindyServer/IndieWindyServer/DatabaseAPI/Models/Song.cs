using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("song")]
    public class Song : BaseEntity
    {
        [Key]
        [Column("id")]
        public int Id { get; set; }
        
        [Column("name")]
        public string Name { get; set; }
        
        [Column("song_url")]
        public string SongUrl { get; set; }
        
        [Column("artist_id")]
        public int ArtistId { get; set; }
        
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }

        [Column("album_id")]
        public int AlbumId { get; set; }
        [ForeignKey("AlbumId")]
        public Album Album { get; set; }
    }
}