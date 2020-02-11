namespace ServerCore.Models
{
    public partial class ArtistAlbums
    {
        public int Id { get; set; }
        public int ArtistId { get; set; }
        public int AlbumId { get; set; }
    }
}
