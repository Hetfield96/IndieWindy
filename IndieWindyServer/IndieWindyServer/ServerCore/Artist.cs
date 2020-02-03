using System;
using System.Collections.Generic;

namespace ServerCore
{
    public partial class Artist
    {
        public Artist()
        {
            Album = new HashSet<Album>();
            ArtistConcertLink = new HashSet<ArtistConcertLink>();
            Song = new HashSet<Song>();
            UserArtistLinkSubscriptions = new HashSet<UserArtistLinkSubscriptions>();
        }

        public int Id { get; set; }
        public string Name { get; set; }
        public string ImageUrl { get; set; }

        public virtual ICollection<Album> Album { get; set; }
        public virtual ICollection<ArtistConcertLink> ArtistConcertLink { get; set; }
        public virtual ICollection<Song> Song { get; set; }
        public virtual ICollection<UserArtistLinkSubscriptions> UserArtistLinkSubscriptions { get; set; }
    }
}
