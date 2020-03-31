using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace WebAPI.Migrations
{
    public partial class posts : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "post",
                columns: table => new
                {
                    id = table.Column<int>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    text = table.Column<string>(nullable: false),
                    time = table.Column<DateTime>(nullable: false),
                    image_url = table.Column<string>(nullable: true),
                    artist_id = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_post", x => x.id);
                    table.ForeignKey(
                        name: "FK_post_artist_artist_id",
                        column: x => x.artist_id,
                        principalTable: "artist",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "artist_post_link",
                columns: table => new
                {
                    artist_id = table.Column<int>(nullable: false),
                    post_id = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_artist_post_link", x => new { x.artist_id, x.post_id });
                    table.ForeignKey(
                        name: "FK_artist_post_link_artist_artist_id",
                        column: x => x.artist_id,
                        principalTable: "artist",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_artist_post_link_post_post_id",
                        column: x => x.post_id,
                        principalTable: "post",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "post_song_link",
                columns: table => new
                {
                    post_id = table.Column<int>(nullable: false),
                    song_id = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_post_song_link", x => new { x.post_id, x.song_id });
                    table.ForeignKey(
                        name: "FK_post_song_link_post_post_id",
                        column: x => x.post_id,
                        principalTable: "post",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_post_song_link_artist_song_id",
                        column: x => x.song_id,
                        principalTable: "artist",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_artist_post_link_post_id",
                table: "artist_post_link",
                column: "post_id");

            migrationBuilder.CreateIndex(
                name: "IX_post_artist_id",
                table: "post",
                column: "artist_id");

            migrationBuilder.CreateIndex(
                name: "IX_post_song_link_song_id",
                table: "post_song_link",
                column: "song_id");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "artist_post_link");

            migrationBuilder.DropTable(
                name: "post_song_link");

            migrationBuilder.DropTable(
                name: "post");
        }
    }
}
