namespace ServerCore.Models
{
    public partial class UserArtists
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public int ArtistId { get; set; }
    }
}
