﻿// <auto-generated />
using System;
using DatabaseAPI;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace WebAPI.Migrations
{
    [DbContext(typeof(IndieWindyDbContext))]
    partial class MyWebApiContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn)
                .HasAnnotation("ProductVersion", "3.1.2")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            modelBuilder.Entity("DatabaseAPI.Models.Album", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnName("id")
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

                    b.Property<int>("ArtistId")
                        .HasColumnName("artist_id")
                        .HasColumnType("integer");

                    b.Property<string>("ImageUrl")
                        .HasColumnName("image_url")
                        .HasColumnType("text");

                    b.Property<string>("Name")
                        .HasColumnName("name")
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.HasIndex("ArtistId");

                    b.ToTable("album");
                });

            modelBuilder.Entity("DatabaseAPI.Models.AppUser", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnName("id")
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

                    b.Property<string>("Name")
                        .HasColumnName("name")
                        .HasColumnType("text");

                    b.Property<string>("Password")
                        .HasColumnName("password")
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.ToTable("app_user");
                });

            modelBuilder.Entity("DatabaseAPI.Models.Artist", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnName("id")
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

                    b.Property<string>("ImageUrl")
                        .HasColumnName("image_url")
                        .HasColumnType("text");

                    b.Property<string>("Name")
                        .HasColumnName("name")
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.ToTable("artist");
                });

            modelBuilder.Entity("DatabaseAPI.Models.ArtistConcertLink", b =>
                {
                    b.Property<int>("ArtistId")
                        .HasColumnName("artist_id")
                        .HasColumnType("integer");

                    b.Property<int>("ConcertId")
                        .HasColumnName("concert_id")
                        .HasColumnType("integer");

                    b.HasKey("ArtistId", "ConcertId");

                    b.HasIndex("ConcertId");

                    b.ToTable("artist_concert_link");
                });

            modelBuilder.Entity("DatabaseAPI.Models.Concert", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnName("id")
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

                    b.Property<string>("Address")
                        .HasColumnName("address")
                        .HasColumnType("text");

                    b.Property<int>("Cost")
                        .HasColumnName("cost")
                        .HasColumnType("integer");

                    b.Property<string>("ImageUrl")
                        .HasColumnName("image_url")
                        .HasColumnType("text");

                    b.Property<string>("Name")
                        .HasColumnName("name")
                        .HasColumnType("text");

                    b.Property<DateTime>("StartTime")
                        .HasColumnName("start_time")
                        .HasColumnType("timestamp without time zone");

                    b.HasKey("Id");

                    b.ToTable("concert");
                });

            modelBuilder.Entity("DatabaseAPI.Models.Song", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnName("id")
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

                    b.Property<int>("AlbumId")
                        .HasColumnName("album_id")
                        .HasColumnType("integer");

                    b.Property<int>("ArtistId")
                        .HasColumnName("artist_id")
                        .HasColumnType("integer");

                    b.Property<string>("Name")
                        .HasColumnName("name")
                        .HasColumnType("text");

                    b.Property<string>("SongUrl")
                        .HasColumnName("song_url")
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.HasIndex("AlbumId");

                    b.HasIndex("ArtistId");

                    b.ToTable("song");
                });

            modelBuilder.Entity("DatabaseAPI.Models.UserAlbumLink", b =>
                {
                    b.Property<int>("AppUserId")
                        .HasColumnName("app_user_id")
                        .HasColumnType("integer");

                    b.Property<int>("AlbumId")
                        .HasColumnName("album_id")
                        .HasColumnType("integer");

                    b.HasKey("AppUserId", "AlbumId");

                    b.HasIndex("AlbumId");

                    b.ToTable("user_album_link");
                });

            modelBuilder.Entity("DatabaseAPI.Models.UserArtistLink", b =>
                {
                    b.Property<int>("AppUserId")
                        .HasColumnName("app_user_id")
                        .HasColumnType("integer");

                    b.Property<int>("ArtistId")
                        .HasColumnName("artist_id")
                        .HasColumnType("integer");

                    b.HasKey("AppUserId", "ArtistId");

                    b.HasIndex("ArtistId");

                    b.ToTable("user_artist_link");
                });

            modelBuilder.Entity("DatabaseAPI.Models.UserConcertLink", b =>
                {
                    b.Property<int>("AppUserId")
                        .HasColumnName("app_user_id")
                        .HasColumnType("integer");

                    b.Property<int>("ConcertId")
                        .HasColumnName("concert_id")
                        .HasColumnType("integer");

                    b.HasKey("AppUserId", "ConcertId");

                    b.HasIndex("ConcertId");

                    b.ToTable("user_concert_link");
                });

            modelBuilder.Entity("DatabaseAPI.Models.UserSongLink", b =>
                {
                    b.Property<int>("AppUserId")
                        .HasColumnName("app_user_id")
                        .HasColumnType("integer");

                    b.Property<int>("SongId")
                        .HasColumnName("song_id")
                        .HasColumnType("integer");

                    b.HasKey("AppUserId", "SongId");

                    b.HasIndex("SongId");

                    b.ToTable("user_song_link");
                });

            modelBuilder.Entity("DatabaseAPI.Models.Album", b =>
                {
                    b.HasOne("DatabaseAPI.Models.Artist", "Artist")
                        .WithMany()
                        .HasForeignKey("ArtistId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("DatabaseAPI.Models.ArtistConcertLink", b =>
                {
                    b.HasOne("DatabaseAPI.Models.Artist", "Artist")
                        .WithMany()
                        .HasForeignKey("ArtistId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("DatabaseAPI.Models.Concert", "Concert")
                        .WithMany()
                        .HasForeignKey("ConcertId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("DatabaseAPI.Models.Song", b =>
                {
                    b.HasOne("DatabaseAPI.Models.Album", "Album")
                        .WithMany()
                        .HasForeignKey("AlbumId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("DatabaseAPI.Models.Artist", "Artist")
                        .WithMany()
                        .HasForeignKey("ArtistId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("DatabaseAPI.Models.UserAlbumLink", b =>
                {
                    b.HasOne("DatabaseAPI.Models.Album", "Album")
                        .WithMany()
                        .HasForeignKey("AlbumId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("DatabaseAPI.Models.AppUser", "AppUser")
                        .WithMany()
                        .HasForeignKey("AppUserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("DatabaseAPI.Models.UserArtistLink", b =>
                {
                    b.HasOne("DatabaseAPI.Models.AppUser", "AppUser")
                        .WithMany()
                        .HasForeignKey("AppUserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("DatabaseAPI.Models.Artist", "Artist")
                        .WithMany()
                        .HasForeignKey("ArtistId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("DatabaseAPI.Models.UserConcertLink", b =>
                {
                    b.HasOne("DatabaseAPI.Models.AppUser", "AppUser")
                        .WithMany()
                        .HasForeignKey("AppUserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("DatabaseAPI.Models.Concert", "Concert")
                        .WithMany()
                        .HasForeignKey("ConcertId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("DatabaseAPI.Models.UserSongLink", b =>
                {
                    b.HasOne("DatabaseAPI.Models.AppUser", "AppUser")
                        .WithMany()
                        .HasForeignKey("AppUserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("DatabaseAPI.Models.Song", "Song")
                        .WithMany()
                        .HasForeignKey("SongId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });
#pragma warning restore 612, 618
        }
    }
}
