using Microsoft.EntityFrameworkCore.Migrations;

namespace WebAPI.Migrations
{
    public partial class LowerCaseRenaming : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Album_Artist_ArtistId",
                table: "Album");

            migrationBuilder.DropForeignKey(
                name: "FK_ArtistConcertLink_Artist_ArtistId",
                table: "ArtistConcertLink");

            migrationBuilder.DropForeignKey(
                name: "FK_ArtistConcertLink_Concert_ConcertId",
                table: "ArtistConcertLink");

            migrationBuilder.DropForeignKey(
                name: "FK_Song_Album_AlbumId",
                table: "Song");

            migrationBuilder.DropForeignKey(
                name: "FK_Song_Artist_ArtistId",
                table: "Song");

            migrationBuilder.DropForeignKey(
                name: "FK_UserAlbumLink_Album_AlbumId",
                table: "UserAlbumLink");

            migrationBuilder.DropForeignKey(
                name: "FK_UserAlbumLink_AppUser_AppUserId",
                table: "UserAlbumLink");

            migrationBuilder.DropForeignKey(
                name: "FK_UserArtistLink_AppUser_AppUserId",
                table: "UserArtistLink");

            migrationBuilder.DropForeignKey(
                name: "FK_UserArtistLink_Artist_ArtistId",
                table: "UserArtistLink");

            migrationBuilder.DropForeignKey(
                name: "FK_UserConcertLink_AppUser_AppUserId",
                table: "UserConcertLink");

            migrationBuilder.DropForeignKey(
                name: "FK_UserConcertLink_Concert_ConcertId",
                table: "UserConcertLink");

            migrationBuilder.DropForeignKey(
                name: "FK_UserSongLink_AppUser_AppUserId",
                table: "UserSongLink");

            migrationBuilder.DropForeignKey(
                name: "FK_UserSongLink_Song_SongId",
                table: "UserSongLink");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Song",
                table: "Song");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Concert",
                table: "Concert");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Artist",
                table: "Artist");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Album",
                table: "Album");

            migrationBuilder.DropPrimaryKey(
                name: "PK_UserSongLink",
                table: "UserSongLink");

            migrationBuilder.DropPrimaryKey(
                name: "PK_UserConcertLink",
                table: "UserConcertLink");

            migrationBuilder.DropPrimaryKey(
                name: "PK_UserArtistLink",
                table: "UserArtistLink");

            migrationBuilder.DropPrimaryKey(
                name: "PK_UserAlbumLink",
                table: "UserAlbumLink");

            migrationBuilder.DropPrimaryKey(
                name: "PK_ArtistConcertLink",
                table: "ArtistConcertLink");

            migrationBuilder.DropPrimaryKey(
                name: "PK_AppUser",
                table: "AppUser");

            migrationBuilder.RenameTable(
                name: "Song",
                newName: "song");

            migrationBuilder.RenameTable(
                name: "Concert",
                newName: "concert");

            migrationBuilder.RenameTable(
                name: "Artist",
                newName: "artist");

            migrationBuilder.RenameTable(
                name: "Album",
                newName: "album");

            migrationBuilder.RenameTable(
                name: "UserSongLink",
                newName: "user_song_link");

            migrationBuilder.RenameTable(
                name: "UserConcertLink",
                newName: "user_concert_link");

            migrationBuilder.RenameTable(
                name: "UserArtistLink",
                newName: "user_artist_link");

            migrationBuilder.RenameTable(
                name: "UserAlbumLink",
                newName: "user_album_link");

            migrationBuilder.RenameTable(
                name: "ArtistConcertLink",
                newName: "artist_concert_link");

            migrationBuilder.RenameTable(
                name: "AppUser",
                newName: "app_user");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "song",
                newName: "name");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "song",
                newName: "id");

            migrationBuilder.RenameColumn(
                name: "SongUrl",
                table: "song",
                newName: "song_url");

            migrationBuilder.RenameColumn(
                name: "ArtistId",
                table: "song",
                newName: "artist_id");

            migrationBuilder.RenameColumn(
                name: "AlbumId",
                table: "song",
                newName: "album_id");

            migrationBuilder.RenameIndex(
                name: "IX_Song_ArtistId",
                table: "song",
                newName: "IX_song_artist_id");

            migrationBuilder.RenameIndex(
                name: "IX_Song_AlbumId",
                table: "song",
                newName: "IX_song_album_id");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "concert",
                newName: "name");

            migrationBuilder.RenameColumn(
                name: "Cost",
                table: "concert",
                newName: "cost");

            migrationBuilder.RenameColumn(
                name: "Address",
                table: "concert",
                newName: "address");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "concert",
                newName: "id");

            migrationBuilder.RenameColumn(
                name: "StartTime",
                table: "concert",
                newName: "start_time");

            migrationBuilder.RenameColumn(
                name: "ImageUrl",
                table: "concert",
                newName: "image_url");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "artist",
                newName: "name");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "artist",
                newName: "id");

            migrationBuilder.RenameColumn(
                name: "ImageUrl",
                table: "artist",
                newName: "image_url");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "album",
                newName: "name");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "album",
                newName: "id");

            migrationBuilder.RenameColumn(
                name: "ImageUrl",
                table: "album",
                newName: "image_url");

            migrationBuilder.RenameColumn(
                name: "ArtistId",
                table: "album",
                newName: "artist_id");

            migrationBuilder.RenameIndex(
                name: "IX_Album_ArtistId",
                table: "album",
                newName: "IX_album_artist_id");

            migrationBuilder.RenameColumn(
                name: "SongId",
                table: "user_song_link",
                newName: "song_id");

            migrationBuilder.RenameColumn(
                name: "AppUserId",
                table: "user_song_link",
                newName: "app_user_id");

            migrationBuilder.RenameIndex(
                name: "IX_UserSongLink_SongId",
                table: "user_song_link",
                newName: "IX_user_song_link_song_id");

            migrationBuilder.RenameColumn(
                name: "ConcertId",
                table: "user_concert_link",
                newName: "concert_id");

            migrationBuilder.RenameColumn(
                name: "AppUserId",
                table: "user_concert_link",
                newName: "app_user_id");

            migrationBuilder.RenameIndex(
                name: "IX_UserConcertLink_ConcertId",
                table: "user_concert_link",
                newName: "IX_user_concert_link_concert_id");

            migrationBuilder.RenameColumn(
                name: "ArtistId",
                table: "user_artist_link",
                newName: "artist_id");

            migrationBuilder.RenameColumn(
                name: "AppUserId",
                table: "user_artist_link",
                newName: "app_user_id");

            migrationBuilder.RenameIndex(
                name: "IX_UserArtistLink_ArtistId",
                table: "user_artist_link",
                newName: "IX_user_artist_link_artist_id");

            migrationBuilder.RenameColumn(
                name: "AlbumId",
                table: "user_album_link",
                newName: "album_id");

            migrationBuilder.RenameColumn(
                name: "AppUserId",
                table: "user_album_link",
                newName: "app_user_id");

            migrationBuilder.RenameIndex(
                name: "IX_UserAlbumLink_AlbumId",
                table: "user_album_link",
                newName: "IX_user_album_link_album_id");

            migrationBuilder.RenameColumn(
                name: "ConcertId",
                table: "artist_concert_link",
                newName: "concert_id");

            migrationBuilder.RenameColumn(
                name: "ArtistId",
                table: "artist_concert_link",
                newName: "artist_id");

            migrationBuilder.RenameIndex(
                name: "IX_ArtistConcertLink_ConcertId",
                table: "artist_concert_link",
                newName: "IX_artist_concert_link_concert_id");

            migrationBuilder.RenameColumn(
                name: "Password",
                table: "app_user",
                newName: "password");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "app_user",
                newName: "name");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "app_user",
                newName: "id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_song",
                table: "song",
                column: "id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_concert",
                table: "concert",
                column: "id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_artist",
                table: "artist",
                column: "id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_album",
                table: "album",
                column: "id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_user_song_link",
                table: "user_song_link",
                columns: new[] { "app_user_id", "song_id" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_user_concert_link",
                table: "user_concert_link",
                columns: new[] { "app_user_id", "concert_id" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_user_artist_link",
                table: "user_artist_link",
                columns: new[] { "app_user_id", "artist_id" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_user_album_link",
                table: "user_album_link",
                columns: new[] { "app_user_id", "album_id" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_artist_concert_link",
                table: "artist_concert_link",
                columns: new[] { "artist_id", "concert_id" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_app_user",
                table: "app_user",
                column: "id");

            migrationBuilder.AddForeignKey(
                name: "FK_album_artist_artist_id",
                table: "album",
                column: "artist_id",
                principalTable: "artist",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_artist_concert_link_artist_artist_id",
                table: "artist_concert_link",
                column: "artist_id",
                principalTable: "artist",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_artist_concert_link_concert_concert_id",
                table: "artist_concert_link",
                column: "concert_id",
                principalTable: "concert",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_song_album_album_id",
                table: "song",
                column: "album_id",
                principalTable: "album",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_song_artist_artist_id",
                table: "song",
                column: "artist_id",
                principalTable: "artist",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_user_album_link_album_album_id",
                table: "user_album_link",
                column: "album_id",
                principalTable: "album",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_user_album_link_app_user_app_user_id",
                table: "user_album_link",
                column: "app_user_id",
                principalTable: "app_user",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_user_artist_link_app_user_app_user_id",
                table: "user_artist_link",
                column: "app_user_id",
                principalTable: "app_user",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_user_artist_link_artist_artist_id",
                table: "user_artist_link",
                column: "artist_id",
                principalTable: "artist",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_user_concert_link_app_user_app_user_id",
                table: "user_concert_link",
                column: "app_user_id",
                principalTable: "app_user",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_user_concert_link_concert_concert_id",
                table: "user_concert_link",
                column: "concert_id",
                principalTable: "concert",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_user_song_link_app_user_app_user_id",
                table: "user_song_link",
                column: "app_user_id",
                principalTable: "app_user",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_user_song_link_song_song_id",
                table: "user_song_link",
                column: "song_id",
                principalTable: "song",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_album_artist_artist_id",
                table: "album");

            migrationBuilder.DropForeignKey(
                name: "FK_artist_concert_link_artist_artist_id",
                table: "artist_concert_link");

            migrationBuilder.DropForeignKey(
                name: "FK_artist_concert_link_concert_concert_id",
                table: "artist_concert_link");

            migrationBuilder.DropForeignKey(
                name: "FK_song_album_album_id",
                table: "song");

            migrationBuilder.DropForeignKey(
                name: "FK_song_artist_artist_id",
                table: "song");

            migrationBuilder.DropForeignKey(
                name: "FK_user_album_link_album_album_id",
                table: "user_album_link");

            migrationBuilder.DropForeignKey(
                name: "FK_user_album_link_app_user_app_user_id",
                table: "user_album_link");

            migrationBuilder.DropForeignKey(
                name: "FK_user_artist_link_app_user_app_user_id",
                table: "user_artist_link");

            migrationBuilder.DropForeignKey(
                name: "FK_user_artist_link_artist_artist_id",
                table: "user_artist_link");

            migrationBuilder.DropForeignKey(
                name: "FK_user_concert_link_app_user_app_user_id",
                table: "user_concert_link");

            migrationBuilder.DropForeignKey(
                name: "FK_user_concert_link_concert_concert_id",
                table: "user_concert_link");

            migrationBuilder.DropForeignKey(
                name: "FK_user_song_link_app_user_app_user_id",
                table: "user_song_link");

            migrationBuilder.DropForeignKey(
                name: "FK_user_song_link_song_song_id",
                table: "user_song_link");

            migrationBuilder.DropPrimaryKey(
                name: "PK_song",
                table: "song");

            migrationBuilder.DropPrimaryKey(
                name: "PK_concert",
                table: "concert");

            migrationBuilder.DropPrimaryKey(
                name: "PK_artist",
                table: "artist");

            migrationBuilder.DropPrimaryKey(
                name: "PK_album",
                table: "album");

            migrationBuilder.DropPrimaryKey(
                name: "PK_user_song_link",
                table: "user_song_link");

            migrationBuilder.DropPrimaryKey(
                name: "PK_user_concert_link",
                table: "user_concert_link");

            migrationBuilder.DropPrimaryKey(
                name: "PK_user_artist_link",
                table: "user_artist_link");

            migrationBuilder.DropPrimaryKey(
                name: "PK_user_album_link",
                table: "user_album_link");

            migrationBuilder.DropPrimaryKey(
                name: "PK_artist_concert_link",
                table: "artist_concert_link");

            migrationBuilder.DropPrimaryKey(
                name: "PK_app_user",
                table: "app_user");

            migrationBuilder.RenameTable(
                name: "song",
                newName: "Song");

            migrationBuilder.RenameTable(
                name: "concert",
                newName: "Concert");

            migrationBuilder.RenameTable(
                name: "artist",
                newName: "Artist");

            migrationBuilder.RenameTable(
                name: "album",
                newName: "Album");

            migrationBuilder.RenameTable(
                name: "user_song_link",
                newName: "UserSongLink");

            migrationBuilder.RenameTable(
                name: "user_concert_link",
                newName: "UserConcertLink");

            migrationBuilder.RenameTable(
                name: "user_artist_link",
                newName: "UserArtistLink");

            migrationBuilder.RenameTable(
                name: "user_album_link",
                newName: "UserAlbumLink");

            migrationBuilder.RenameTable(
                name: "artist_concert_link",
                newName: "ArtistConcertLink");

            migrationBuilder.RenameTable(
                name: "app_user",
                newName: "AppUser");

            migrationBuilder.RenameColumn(
                name: "name",
                table: "Song",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "id",
                table: "Song",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "song_url",
                table: "Song",
                newName: "SongUrl");

            migrationBuilder.RenameColumn(
                name: "artist_id",
                table: "Song",
                newName: "ArtistId");

            migrationBuilder.RenameColumn(
                name: "album_id",
                table: "Song",
                newName: "AlbumId");

            migrationBuilder.RenameIndex(
                name: "IX_song_artist_id",
                table: "Song",
                newName: "IX_Song_ArtistId");

            migrationBuilder.RenameIndex(
                name: "IX_song_album_id",
                table: "Song",
                newName: "IX_Song_AlbumId");

            migrationBuilder.RenameColumn(
                name: "name",
                table: "Concert",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "cost",
                table: "Concert",
                newName: "Cost");

            migrationBuilder.RenameColumn(
                name: "address",
                table: "Concert",
                newName: "Address");

            migrationBuilder.RenameColumn(
                name: "id",
                table: "Concert",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "start_time",
                table: "Concert",
                newName: "StartTime");

            migrationBuilder.RenameColumn(
                name: "image_url",
                table: "Concert",
                newName: "ImageUrl");

            migrationBuilder.RenameColumn(
                name: "name",
                table: "Artist",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "id",
                table: "Artist",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "image_url",
                table: "Artist",
                newName: "ImageUrl");

            migrationBuilder.RenameColumn(
                name: "name",
                table: "Album",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "id",
                table: "Album",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "image_url",
                table: "Album",
                newName: "ImageUrl");

            migrationBuilder.RenameColumn(
                name: "artist_id",
                table: "Album",
                newName: "ArtistId");

            migrationBuilder.RenameIndex(
                name: "IX_album_artist_id",
                table: "Album",
                newName: "IX_Album_ArtistId");

            migrationBuilder.RenameColumn(
                name: "song_id",
                table: "UserSongLink",
                newName: "SongId");

            migrationBuilder.RenameColumn(
                name: "app_user_id",
                table: "UserSongLink",
                newName: "AppUserId");

            migrationBuilder.RenameIndex(
                name: "IX_user_song_link_song_id",
                table: "UserSongLink",
                newName: "IX_UserSongLink_SongId");

            migrationBuilder.RenameColumn(
                name: "concert_id",
                table: "UserConcertLink",
                newName: "ConcertId");

            migrationBuilder.RenameColumn(
                name: "app_user_id",
                table: "UserConcertLink",
                newName: "AppUserId");

            migrationBuilder.RenameIndex(
                name: "IX_user_concert_link_concert_id",
                table: "UserConcertLink",
                newName: "IX_UserConcertLink_ConcertId");

            migrationBuilder.RenameColumn(
                name: "artist_id",
                table: "UserArtistLink",
                newName: "ArtistId");

            migrationBuilder.RenameColumn(
                name: "app_user_id",
                table: "UserArtistLink",
                newName: "AppUserId");

            migrationBuilder.RenameIndex(
                name: "IX_user_artist_link_artist_id",
                table: "UserArtistLink",
                newName: "IX_UserArtistLink_ArtistId");

            migrationBuilder.RenameColumn(
                name: "album_id",
                table: "UserAlbumLink",
                newName: "AlbumId");

            migrationBuilder.RenameColumn(
                name: "app_user_id",
                table: "UserAlbumLink",
                newName: "AppUserId");

            migrationBuilder.RenameIndex(
                name: "IX_user_album_link_album_id",
                table: "UserAlbumLink",
                newName: "IX_UserAlbumLink_AlbumId");

            migrationBuilder.RenameColumn(
                name: "concert_id",
                table: "ArtistConcertLink",
                newName: "ConcertId");

            migrationBuilder.RenameColumn(
                name: "artist_id",
                table: "ArtistConcertLink",
                newName: "ArtistId");

            migrationBuilder.RenameIndex(
                name: "IX_artist_concert_link_concert_id",
                table: "ArtistConcertLink",
                newName: "IX_ArtistConcertLink_ConcertId");

            migrationBuilder.RenameColumn(
                name: "password",
                table: "AppUser",
                newName: "Password");

            migrationBuilder.RenameColumn(
                name: "name",
                table: "AppUser",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "id",
                table: "AppUser",
                newName: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Song",
                table: "Song",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Concert",
                table: "Concert",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Artist",
                table: "Artist",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Album",
                table: "Album",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_UserSongLink",
                table: "UserSongLink",
                columns: new[] { "AppUserId", "SongId" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_UserConcertLink",
                table: "UserConcertLink",
                columns: new[] { "AppUserId", "ConcertId" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_UserArtistLink",
                table: "UserArtistLink",
                columns: new[] { "AppUserId", "ArtistId" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_UserAlbumLink",
                table: "UserAlbumLink",
                columns: new[] { "AppUserId", "AlbumId" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_ArtistConcertLink",
                table: "ArtistConcertLink",
                columns: new[] { "ArtistId", "ConcertId" });

            migrationBuilder.AddPrimaryKey(
                name: "PK_AppUser",
                table: "AppUser",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Album_Artist_ArtistId",
                table: "Album",
                column: "ArtistId",
                principalTable: "Artist",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ArtistConcertLink_Artist_ArtistId",
                table: "ArtistConcertLink",
                column: "ArtistId",
                principalTable: "Artist",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ArtistConcertLink_Concert_ConcertId",
                table: "ArtistConcertLink",
                column: "ConcertId",
                principalTable: "Concert",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Song_Album_AlbumId",
                table: "Song",
                column: "AlbumId",
                principalTable: "Album",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Song_Artist_ArtistId",
                table: "Song",
                column: "ArtistId",
                principalTable: "Artist",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserAlbumLink_Album_AlbumId",
                table: "UserAlbumLink",
                column: "AlbumId",
                principalTable: "Album",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserAlbumLink_AppUser_AppUserId",
                table: "UserAlbumLink",
                column: "AppUserId",
                principalTable: "AppUser",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserArtistLink_AppUser_AppUserId",
                table: "UserArtistLink",
                column: "AppUserId",
                principalTable: "AppUser",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserArtistLink_Artist_ArtistId",
                table: "UserArtistLink",
                column: "ArtistId",
                principalTable: "Artist",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserConcertLink_AppUser_AppUserId",
                table: "UserConcertLink",
                column: "AppUserId",
                principalTable: "AppUser",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserConcertLink_Concert_ConcertId",
                table: "UserConcertLink",
                column: "ConcertId",
                principalTable: "Concert",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserSongLink_AppUser_AppUserId",
                table: "UserSongLink",
                column: "AppUserId",
                principalTable: "AppUser",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserSongLink_Song_SongId",
                table: "UserSongLink",
                column: "SongId",
                principalTable: "Song",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
