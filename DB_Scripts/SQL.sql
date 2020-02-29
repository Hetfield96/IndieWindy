select * from "AppUser";
select * from "Artist";
select * from "Album";
select * from "Song";

select * from "UserArtistLink";

select * from "UserAlbumLink";
select * from "UserSongLink";
select * from "UserConcertLink";

delete from "AppUser"
where "Id" > 1