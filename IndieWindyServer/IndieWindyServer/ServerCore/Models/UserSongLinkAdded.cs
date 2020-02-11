namespace ServerCore.Models
{
    public partial class UserSongLinkAdded
    {
        public int UserId { get; set; }
        public int SongId { get; set; }

        public virtual Song Song { get; set; }
        public virtual AppUser User { get; set; }
    }
}
