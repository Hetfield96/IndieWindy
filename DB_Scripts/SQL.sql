select * from "AppUser";
select * from "Artist";
select * from "Album";
select * from "Song";

select * from "UserArtistLink";

select * from "UserAlbumLink";
select * from "UserSongLink";
select * from "UserConcertLink";

delete from "UserSongLink"
where "AppUserId" = 4;

delete from "AppUser"
where "Id" > 1;

update "Song"
set "SongUrl" = 'http://indie-windy.s3.eu-north-1.amazonaws.com/music/Pasosh/Pasosh-rossiya.mp3'
where "Id" = 4;


select * from "Song" as s
left join "UserSongLink" as link
on s."Id" = link."SongId" and link."AppUserId" = 4
where s."Name" like 'К%';