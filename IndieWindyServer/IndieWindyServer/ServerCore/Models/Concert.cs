using System.ComponentModel.DataAnnotations.Schema;

namespace ServerCore.Models
{
    [Table("concert")]
    public class Concert: BaseEntity
    {
        [Column("id")]
        public int Id { get; set; }
        
        [Column("name")]
        public string Name { get; set; }
        
        [Column("start_time")]
        public string StartTime { get; set; }
        
        [Column("end_time")]
        public string EndTime { get; set; }

        [Column("club_name")]
        public string ClubName { get; set; }
        
        [Column("address")]
        public string Address { get; set; }
        
        [Column("cost")]
        public int Cost { get; set; }
        
        [Column("image_url")]
        public string ImageUrl { get; set; }
    }
}