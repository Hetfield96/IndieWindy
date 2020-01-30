using System.ComponentModel.DataAnnotations.Schema;

namespace ServerCore.Models
{
    [Table("artist")]
    public class Artist: BaseEntity
    {
        [Column("id")]
        public int Id { get; set; }
        
        [Column("name")]
        public string Name { get; set; }
        
        [Column("image_url")]
        public string ImageUrl { get; set; }
    }
}