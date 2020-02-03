using System;
using System.Collections.Generic;

namespace ServerCore
{
    public partial class ArtistAlbums
    {
        public int Id { get; set; }
        public int ArtistId { get; set; }
        public int AlbumId { get; set; }
    }
}
