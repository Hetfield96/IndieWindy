namespace ServerCore.Models
{
    public partial class UserSongs
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public int SongId { get; set; }
    }
}
