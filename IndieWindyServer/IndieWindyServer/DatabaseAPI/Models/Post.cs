#nullable enable
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("post")]
    public class Post : BaseEntity
    {
        [Key]
        [Column("id")]
        public int Id { get; set; }
        
        [Column("text")]
        public string Text { get; set; }
        
        [Column("time")]
        public DateTime Time { get; set; }
        
        [Column("image_url")]
        public string? ImageUrl { get; set; }
        
        [Column("artist_id")]
        public int ArtistId { get; set; }
        
        [ForeignKey("ArtistId")]
        public Artist Artist { get; set; }
        
        public ICollection<Song> Songs { get; set; }
    }
}