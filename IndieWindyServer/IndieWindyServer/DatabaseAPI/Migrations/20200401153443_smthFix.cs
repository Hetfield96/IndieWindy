using Microsoft.EntityFrameworkCore.Migrations;

namespace WebAPI.Migrations
{
    public partial class smthFix : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_latest_post_post_post_id",
                table: "latest_post");

            migrationBuilder.DropIndex(
                name: "IX_latest_post_post_id",
                table: "latest_post");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_latest_post_post_id",
                table: "latest_post",
                column: "post_id");

            migrationBuilder.AddForeignKey(
                name: "FK_latest_post_post_post_id",
                table: "latest_post",
                column: "post_id",
                principalTable: "post",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
