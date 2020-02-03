using System;
using System.Collections.Generic;

namespace ServerCore
{
    public partial class Album
    {
        public Album()
        {
            Song = new HashSet<Song>();
        }

        public int Id { get; set; }
        public int ArtistId { get; set; }
        public string Name { get; set; }
        public string ImageUrl { get; set; }

        public virtual Artist Artist { get; set; }
        public virtual ICollection<Song> Song { get; set; }
    }
}
