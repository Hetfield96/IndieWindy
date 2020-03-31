using Microsoft.EntityFrameworkCore.Migrations;

namespace WebAPI.Migrations
{
    public partial class songsInPosts : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "PostId",
                table: "song",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_song_PostId",
                table: "song",
                column: "PostId");

            migrationBuilder.AddForeignKey(
                name: "FK_song_post_PostId",
                table: "song",
                column: "PostId",
                principalTable: "post",
                principalColumn: "id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_song_post_PostId",
                table: "song");

            migrationBuilder.DropIndex(
                name: "IX_song_PostId",
                table: "song");

            migrationBuilder.DropColumn(
                name: "PostId",
                table: "song");
        }
    }
}
