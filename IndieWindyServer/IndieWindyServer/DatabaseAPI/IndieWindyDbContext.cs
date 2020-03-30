using DatabaseAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DatabaseAPI
{
/* Migrations commands: from DatabaseAPI folder
dotnet ef --startup-project ../WebAPI/ migrations add concertsImprovement
dotnet ef --startup-project ../WebAPI/ database update
*/
/* Database migrate to Azure
pg_dump -Fc -v --host=localhost --port=5430 --username=postgres --dbname=indieWindyDB -f indiewindydb.dump
pg_restore -v --no-owner --host=indiewindydb.postgres.database.azure.com --port=5432 --username=indie_windy_admin@indiewindydb --dbname=postgres indiewindydb.dump
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
        public DbSet<ArtistConcertLink> ArtistConcertLink { get; set; }
        
        public DbSet<UserArtistLink> UserArtistLink { get; set; }
        public DbSet<UserAlbumLink> UserAlbumLink { get; set; }
        public DbSet<UserSongLink> UserSongLink { get; set; }
        public DbSet<UserConcertLink> UserConcertLink { get; set; }
        
        public DbSet<Donation> Donation { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ArtistConcertLink>()
                .HasKey(i => new { i.ArtistId, i.ConcertId });
            
            modelBuilder.Entity<UserAlbumLink>()
                .HasKey(i => new { i.AppUserId, i.AlbumId });
            
            modelBuilder.Entity<UserArtistLink>()
                .HasKey(i => new { i.AppUserId, i.ArtistId });
            
            modelBuilder.Entity<UserConcertLink>()
                .HasKey(i => new { i.AppUserId, i.ConcertId });
            
            modelBuilder.Entity<UserSongLink>()
                .HasKey(i => new { i.AppUserId, i.SongId });
            
            modelBuilder.Entity<Donation>()
                .HasKey(d => new { d.AppUserId, d.ArtistId });
        }
    }
}