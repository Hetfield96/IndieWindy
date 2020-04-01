using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("latest_post")]
    public class LatestPost : BaseEntity
    {
        [Key]
        [Column("id")]
        public int Id { get; set; }
        
        [Column("post_id")]
        public int PostId { get; set; }
    }
}