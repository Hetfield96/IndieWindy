using System.ComponentModel.DataAnnotations.Schema;

namespace ServerCore.Models
{
    [Table("song")]
    public class Song: BaseEntity
    {
        [Column("id")]
        public int Id { get; set; }
        
        [Column("artist_id")]
        public int ArtistId { get; set; }      
        
        [Column("album_id")]
        public int AlbumId { get; set; }
        
        [Column("name")]
        public string Name { get; set; }
        
        [Column("song_url")]
        public string SongUrl { get; set; }
    }
}