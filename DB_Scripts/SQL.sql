select * from "AppUser";

select * from "Album";

<<<<<<< HEAD
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
=======
-- Поиск групп участвующих в концерте
select a.name from artist_concerts ac
join artist a on ac.artist_id = a.id
where ac.concert_id = 1;

select * from appuser;
select * from artist;
select * from user_artist_link_subscriptions;
select * from user_song_link_added
>>>>>>> [Back] Half-way commit: connection with DB got broken
