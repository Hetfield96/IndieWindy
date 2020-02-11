namespace ServerCore.Models
{
    public partial class UserArtistLinkSubscriptions
    {
        public int UserId { get; set; }
        public int ArtistId { get; set; }

        public virtual Artist Artist { get; set; }
        public virtual AppUser User { get; set; }
    }
}
