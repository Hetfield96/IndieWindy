select * from "AppUser";

select * from "Album";

delete from "Album";
delete from "Artist";
delete from "Concert";
delete from "Song";
delete from "UserAlbumLink";
delete from "UserSongLink";
delete from "UserArtistLink";
delete from "ArtistConcertLink";

ALTER SEQUENCE "Album_Id_seq" RESTART WITH 1;
ALTER SEQUENCE "Artist_Id_seq" RESTART WITH 1;
ALTER SEQUENCE "Concert_Id_seq" RESTART WITH 1;
ALTER SEQUENCE "Song_Id_seq" RESTART WITH 1;
