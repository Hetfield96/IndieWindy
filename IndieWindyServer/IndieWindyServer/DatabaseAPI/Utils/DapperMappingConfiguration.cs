using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using Dapper;
using DatabaseAPI.Models;

namespace DatabaseAPI.Utils
{
    public static class DapperMappingConfiguration
    {
        public static void Configure()
        {
            ConfigureType<Album>();
            ConfigureType<AppUser>();
            ConfigureType<Artist>();
            ConfigureType<ArtistConcertLink>();
            ConfigureType<ArtistPostLink>();
            ConfigureType<Donation>();
            ConfigureType<Concert>();
            ConfigureType<Song>();
            ConfigureType<Post>();
            ConfigureType<PostSongLink>();
            ConfigureType<UserAlbumLink>();
            ConfigureType<UserArtistLink>();
            ConfigureType<UserConcertLink>();
            ConfigureType<UserSongLink>();
        }

        private static void ConfigureType<T>()
        {
            SqlMapper.SetTypeMap(typeof(T), 
                new CustomPropertyTypeMap(typeof(T), 
                (type, columnName) => type.GetProperties()
                    .FirstOrDefault(prop => prop.GetCustomAttributes(false)
                        .OfType<ColumnAttribute>()
                        .Any(attr => attr.Name == columnName))));
        }
        
    }
}