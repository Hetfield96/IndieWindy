using System;
using System.Collections.Generic;

namespace ServerCore
{
    public partial class ArtistConcertLink
    {
        public int ArtistId { get; set; }
        public int ConcertId { get; set; }

        public virtual Artist Artist { get; set; }
        public virtual Concert Concert { get; set; }
    }
}
