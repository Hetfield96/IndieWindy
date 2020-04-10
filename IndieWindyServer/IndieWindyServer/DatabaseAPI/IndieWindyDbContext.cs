using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI
{
    
/* Migrations commands: from DatabaseAPI folder
dotnet ef --startup-project ../WebAPI/ migrations add *name*
dotnet ef --startup-project ../WebAPI/ database update
*/

    public class IndieWindyDbContext:DbContext
    {
        public static string ConnectionString;

        public IndieWindyDbContext(DbContextOptions<IndieWindyDbContext> options) : base(options)
        {
            ConnectionString = Database.GetDbConnection()?.ConnectionString;
        }
        
        public DbSet<AppUser> AppUser { get; set; }
        public DbSet<Artist> Artist { get; set; }
        public DbSet<Album> Album { get; set; }
        public DbSet<Song> Song { get; set; }
        public DbSet<Concert> Concert { get; set; }
        public DbSet<Donation> Donation { get; set; }
        public DbSet<Post> Post { get; set; }
        public DbSet<LatestPost> LatestPost { get; set; }
        
        
        public DbSet<ArtistConcertLink> ArtistConcertLink { get; set; }
        public DbSet<ArtistPostLink> ArtistPostLink { get; set; }
        public DbSet<PostSongLink> PostSongLink { get; set; }
        public DbSet<UserArtistLink> UserArtistLink { get; set; }
        public DbSet<UserAlbumLink> UserAlbumLink { get; set; }
        public DbSet<UserSongLink> UserSongLink { get; set; }
        public DbSet<UserConcertLink> UserConcertLink { get; set; }
        
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ArtistConcertLink>()
                .HasKey(i => new { i.ArtistId, i.ConcertId });
            
            modelBuilder.Entity<ArtistPostLink>()
                .HasKey(i => new { i.ArtistId, i.PostId });
            
            modelBuilder.Entity<PostSongLink>()
                .HasKey(i => new { i.PostId, i.SongId });
            
            modelBuilder.Entity<UserAlbumLink>()
                .HasKey(i => new { i.AppUserId, i.AlbumId });
            
            modelBuilder.Entity<UserArtistLink>()
                .HasKey(i => new { i.AppUserId, i.ArtistId });
            
            modelBuilder.Entity<UserConcertLink>()
                .HasKey(i => new { i.AppUserId, i.ConcertId });
            
            modelBuilder.Entity<UserSongLink>()
                .HasKey(i => new { i.AppUserId, i.SongId });
        }
    }
}