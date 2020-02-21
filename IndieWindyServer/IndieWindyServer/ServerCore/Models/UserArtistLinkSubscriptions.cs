using System.ComponentModel.DataAnnotations.Schema;

namespace ServerCore.Models
{
    public partial class UserArtistLinkSubscriptions
    {

        public UserArtistLinkSubscriptions(int userId, int artistId)
        {
            UserId = userId;
            ArtistId = artistId;
        }
        
        public int UserId { get; set; }
        public int ArtistId { get; set; }
        public virtual Artist Artist { get; set; }
        public virtual AppUser User { get; set; }
    }
}
