using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.Extensions.Configuration;

namespace ServerCore
{
    public partial class DbContext : Microsoft.EntityFrameworkCore.DbContext
    {
        private readonly string _connectionString;
        
        public DbContext(IConfiguration configuration)
        {
            _connectionString = configuration.GetSection("DB")?["ConnectionStrings"];
        }

        public DbContext(IConfiguration configuration, DbContextOptions<DbContext> options)
            : base(options)
        {
            _connectionString = configuration.GetSection("DB")?["ConnectionStrings"];
        }

        public virtual DbSet<Album> Album { get; set; }
        public virtual DbSet<AppUser> Appuser { get; set; }
        public virtual DbSet<Artist> Artist { get; set; }
        public virtual DbSet<ArtistAlbums> ArtistAlbums { get; set; }
        public virtual DbSet<ArtistConcertLink> ArtistConcertLink { get; set; }
        public virtual DbSet<ArtistConcerts> ArtistConcerts { get; set; }
        public virtual DbSet<Concert> Concert { get; set; }
        public virtual DbSet<Song> Song { get; set; }
        public virtual DbSet<UserArtistLinkSubscriptions> UserArtistLinkSubscriptions { get; set; }
        public virtual DbSet<UserArtists> UserArtists { get; set; }
        public virtual DbSet<UserSongLinkAdded> UserSongLinkAdded { get; set; }
        public virtual DbSet<UserSongs> UserSongs { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseNpgsql(_connectionString, x => x.UseNodaTime());
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.HasPostgresExtension("pgcrypto");

            modelBuilder.Entity<Album>(entity =>
            {
                entity.ToTable("album");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.ArtistId).HasColumnName("artist_id");

                entity.Property(e => e.ImageUrl)
                    .HasColumnName("image_url")
                    .HasColumnType("character varying");

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnName("name")
                    .HasMaxLength(30);

                entity.HasOne(d => d.Artist)
                    .WithMany(p => p.Album)
                    .HasForeignKey(d => d.ArtistId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("album_artist_id_fkey");
            });

            modelBuilder.Entity<AppUser>(entity =>
            {
                entity.ToTable("appuser");

                entity.HasIndex(e => e.Name)
                    .HasName("appuser_name_key")
                    .IsUnique();

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Name)
                    .HasColumnName("name")
                    .HasMaxLength(20);

                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasColumnName("password");
            });

            modelBuilder.Entity<Artist>(entity =>
            {
                entity.ToTable("artist");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.ImageUrl)
                    .HasColumnName("image_url")
                    .HasMaxLength(200);

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnName("name")
                    .HasMaxLength(30);
            });

            modelBuilder.Entity<ArtistAlbums>(entity =>
            {
                entity.ToTable("artist_albums");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.AlbumId).HasColumnName("album_id");

                entity.Property(e => e.ArtistId).HasColumnName("artist_id");
            });

            modelBuilder.Entity<ArtistConcertLink>(entity =>
            {
                entity.HasKey(e => new { e.ArtistId, e.ConcertId })
                    .HasName("artist_concert_link_pkey");

                entity.ToTable("artist_concert_link");

                entity.Property(e => e.ArtistId).HasColumnName("artist_id");

                entity.Property(e => e.ConcertId).HasColumnName("concert_id");

                entity.HasOne(d => d.Artist)
                    .WithMany(p => p.ArtistConcertLink)
                    .HasForeignKey(d => d.ArtistId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("artist_concert_link_artist_id_fkey");

                entity.HasOne(d => d.Concert)
                    .WithMany(p => p.ArtistConcertLink)
                    .HasForeignKey(d => d.ConcertId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("artist_concert_link_concert_id_fkey");
            });

            modelBuilder.Entity<ArtistConcerts>(entity =>
            {
                entity.ToTable("artist_concerts");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.ArtistId).HasColumnName("artist_id");

                entity.Property(e => e.ConcertId).HasColumnName("concert_id");
            });

            modelBuilder.Entity<Concert>(entity =>
            {
                entity.ToTable("concert");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Address)
                    .IsRequired()
                    .HasColumnName("address")
                    .HasColumnType("character varying");

                entity.Property(e => e.ClubName)
                    .IsRequired()
                    .HasColumnName("club_name")
                    .HasMaxLength(30);

                entity.Property(e => e.Cost).HasColumnName("cost");

                entity.Property(e => e.ImageUrl)
                    .HasColumnName("image_url")
                    .HasColumnType("character varying");

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnName("name")
                    .HasMaxLength(50);

                entity.Property(e => e.StartTime)
                    .HasColumnName("start_time")
                    .HasColumnType("timestamp without time zone");
            });

            modelBuilder.Entity<Song>(entity =>
            {
                entity.ToTable("song");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.AlbumId).HasColumnName("album_id");

                entity.Property(e => e.ArtistId).HasColumnName("artist_id");

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnName("name")
                    .HasMaxLength(30);

                entity.Property(e => e.SongUrl)
                    .HasColumnName("song_url")
                    .HasColumnType("character varying");

                entity.HasOne(d => d.Album)
                    .WithMany(p => p.Song)
                    .HasForeignKey(d => d.AlbumId)
                    .HasConstraintName("song_album_id_fkey");

                entity.HasOne(d => d.Artist)
                    .WithMany(p => p.Song)
                    .HasForeignKey(d => d.ArtistId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("song_artist_id_fkey");
            });

            modelBuilder.Entity<UserArtistLinkSubscriptions>(entity =>
            {
                entity.HasKey(e => new { e.UserId, e.ArtistId })
                    .HasName("user_artist_link_subscriptions_pkey");

                entity.ToTable("user_artist_link_subscriptions");

                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.Property(e => e.ArtistId).HasColumnName("artist_id");

                entity.HasOne(d => d.Artist)
                    .WithMany(p => p.UserArtistLinkSubscriptions)
                    .HasForeignKey(d => d.ArtistId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("user_artist_link_subscriptions_artist_id_fkey");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserArtistLinkSubscriptions)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("user_artist_link_subscriptions_user_id_fkey");
            });

            modelBuilder.Entity<UserArtists>(entity =>
            {
                entity.ToTable("user_artists");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.ArtistId).HasColumnName("artist_id");

                entity.Property(e => e.UserId).HasColumnName("user_id");
            });

            modelBuilder.Entity<UserSongLinkAdded>(entity =>
            {
                entity.HasKey(e => new { e.UserId, e.SongId })
                    .HasName("user_song_link_added_pkey");

                entity.ToTable("user_song_link_added");

                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.Property(e => e.SongId).HasColumnName("song_id");

                entity.HasOne(d => d.Song)
                    .WithMany(p => p.UserSongLinkAdded)
                    .HasForeignKey(d => d.SongId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("user_song_link_added_song_id_fkey");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserSongLinkAdded)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("user_song_link_added_user_id_fkey");
            });

            modelBuilder.Entity<UserSongs>(entity =>
            {
                entity.ToTable("user_songs");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.SongId).HasColumnName("song_id");

                entity.Property(e => e.UserId).HasColumnName("user_id");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
