using Microsoft.EntityFrameworkCore.Migrations;

namespace WebAPI.Migrations
{
    public partial class concertsImprovement : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "description",
                table: "concert",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "ticket_link",
                table: "concert",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "description",
                table: "concert");

            migrationBuilder.DropColumn(
                name: "ticket_link",
                table: "concert");
        }
    }
}
