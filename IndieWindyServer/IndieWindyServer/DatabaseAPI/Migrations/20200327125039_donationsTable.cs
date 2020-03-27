using Microsoft.EntityFrameworkCore.Migrations;

namespace WebAPI.Migrations
{
    public partial class donationsTable : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "donations",
                columns: table => new
                {
                    app_user_id = table.Column<int>(nullable: false),
                    artist_id = table.Column<int>(nullable: false),
                    amount = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_donations", x => new { x.app_user_id, x.artist_id });
                    table.ForeignKey(
                        name: "FK_donations_app_user_app_user_id",
                        column: x => x.app_user_id,
                        principalTable: "app_user",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_donations_artist_artist_id",
                        column: x => x.artist_id,
                        principalTable: "artist",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_donations_artist_id",
                table: "donations",
                column: "artist_id");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "donations");
        }
    }
}
