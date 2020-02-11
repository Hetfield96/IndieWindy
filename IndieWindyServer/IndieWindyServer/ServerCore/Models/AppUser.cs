using System.Collections.Generic;

namespace ServerCore.Models
{
    public partial class AppUser
    {
        public AppUser()
        {
            UserArtistLinkSubscriptions = new HashSet<UserArtistLinkSubscriptions>();
            UserSongLinkAdded = new HashSet<UserSongLinkAdded>();
        }

        public int Id { get; set; }
        public string Name { get; set; }
        public string Password { get; set; }

        public virtual ICollection<UserArtistLinkSubscriptions> UserArtistLinkSubscriptions { get; set; }
        public virtual ICollection<UserSongLinkAdded> UserSongLinkAdded { get; set; }
    }
}
