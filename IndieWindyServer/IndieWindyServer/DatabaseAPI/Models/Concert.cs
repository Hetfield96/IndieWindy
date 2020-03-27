using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseAPI.Models
{
    [Table("concert")]
    public class Concert : BaseEntity
    {
        [Key]
        [Column("id")]
        public int Id { get; set; }
        
        [Column("name")]
        public string Name { get; set; }
        
        [Column("image_url")]
        public string ImageUrl { get; set; }
        
        [Column("cost")]
        public int Cost { get; set; }
        
        [Column("start_time")]
        public DateTime StartTime { get; set; }
        
        [Column("address")]
        public string Address { get; set; }
        
        [Column("ticket_link")]
        public string TicketLink { get; set; }
        
        [Column("description")]
        public string Description { get; set; }
    }
}