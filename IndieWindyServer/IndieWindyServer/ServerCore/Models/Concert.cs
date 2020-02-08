using System;
using System.Collections.Generic;
using NodaTime;

namespace ServerCore
{
    public partial class Concert
    {
        public Concert()
        {
            ArtistConcertLink = new HashSet<ArtistConcertLink>();
        }

        public int Id { get; set; }
        public string Name { get; set; }
        public DateTime StartTime { get; set; }
        public string ClubName { get; set; }
        public string Address { get; set; }
        public int Cost { get; set; }
        public string ImageUrl { get; set; }

        public virtual ICollection<ArtistConcertLink> ArtistConcertLink { get; set; }
    }
}
