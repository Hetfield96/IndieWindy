﻿using System;
using System.Collections.Generic;

namespace ServerCore
{
    public partial class UserArtistLinkSubscriptions
    {
        public int UserId { get; set; }
        public int ArtistId { get; set; }

        public virtual Artist Artist { get; set; }
        public virtual Appuser User { get; set; }
    }
}
