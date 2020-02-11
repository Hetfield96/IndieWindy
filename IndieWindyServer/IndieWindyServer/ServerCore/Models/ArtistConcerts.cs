namespace ServerCore.Models
{
    public partial class ArtistConcerts
    {
        public int Id { get; set; }
        public int ArtistId { get; set; }
        public int ConcertId { get; set; }
    }
}
