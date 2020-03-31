using Microsoft.EntityFrameworkCore.Migrations;

namespace WebAPI.Migrations
{
    public partial class fixPostSongLink : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_post_song_link_artist_song_id",
                table: "post_song_link");

            migrationBuilder.AddForeignKey(
                name: "FK_post_song_link_song_song_id",
                table: "post_song_link",
                column: "song_id",
                principalTable: "song",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_post_song_link_song_song_id",
                table: "post_song_link");

            migrationBuilder.AddForeignKey(
                name: "FK_post_song_link_artist_song_id",
                table: "post_song_link",
                column: "song_id",
                principalTable: "artist",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
