using Microsoft.EntityFrameworkCore.Migrations;

namespace WebAPI.Migrations
{
    public partial class donationsTableRename : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_donations_app_user_app_user_id",
                table: "donations");

            migrationBuilder.DropForeignKey(
                name: "FK_donations_artist_artist_id",
                table: "donations");

            migrationBuilder.DropPrimaryKey(
                name: "PK_donations",
                table: "donations");

            migrationBuilder.RenameTable(
                name: "donations",
                newName: "donation");

            migrationBuilder.RenameIndex(
                name: "IX_donations_artist_id",
                table: "donation",
                newName: "IX_donation_artist_id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_donation",
                table: "donation",
                columns: new[] { "app_user_id", "artist_id" });

            migrationBuilder.AddForeignKey(
                name: "FK_donation_app_user_app_user_id",
                table: "donation",
                column: "app_user_id",
                principalTable: "app_user",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_donation_artist_artist_id",
                table: "donation",
                column: "artist_id",
                principalTable: "artist",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_donation_app_user_app_user_id",
                table: "donation");

            migrationBuilder.DropForeignKey(
                name: "FK_donation_artist_artist_id",
                table: "donation");

            migrationBuilder.DropPrimaryKey(
                name: "PK_donation",
                table: "donation");

            migrationBuilder.RenameTable(
                name: "donation",
                newName: "donations");

            migrationBuilder.RenameIndex(
                name: "IX_donation_artist_id",
                table: "donations",
                newName: "IX_donations_artist_id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_donations",
                table: "donations",
                columns: new[] { "app_user_id", "artist_id" });

            migrationBuilder.AddForeignKey(
                name: "FK_donations_app_user_app_user_id",
                table: "donations",
                column: "app_user_id",
                principalTable: "app_user",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_donations_artist_artist_id",
                table: "donations",
                column: "artist_id",
                principalTable: "artist",
                principalColumn: "id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
