using System.Collections.Generic;

namespace ServerCore.Models
{
    public partial class Song
    {
        public Song()
        {
            UserSongLinkAdded = new HashSet<UserSongLinkAdded>();
        }

        public int Id { get; set; }
        public int ArtistId { get; set; }
        public int? AlbumId { get; set; }
        public string Name { get; set; }
        public string SongUrl { get; set; }

        public virtual Album Album { get; set; }
        public virtual Artist Artist { get; set; }
        public virtual ICollection<UserSongLinkAdded> UserSongLinkAdded { get; set; }
    }
}
