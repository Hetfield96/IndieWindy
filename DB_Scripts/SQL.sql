select * from "AppUser";
select * from "Artist";
select * from "Album";
select * from "Song";

select * from "UserArtistLink";

select * from "UserAlbumLink";
select * from "UserSongLink";
select * from "UserConcertLink";

delete from "AppUser"
where "Id" > 1;

update "Song"
set "SongUrl" = 'http://indie-windy.s3.eu-north-1.amazonaws.com/music/Pasosh/Pasosh-rossiya.mp3'
where "Id" = 4;