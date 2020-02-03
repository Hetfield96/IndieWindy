using System;
using System.Collections.Generic;

namespace ServerCore
{
    public partial class UserSongLinkAdded
    {
        public int UserId { get; set; }
        public int SongId { get; set; }

        public virtual Song Song { get; set; }
        public virtual Appuser User { get; set; }
    }
}
