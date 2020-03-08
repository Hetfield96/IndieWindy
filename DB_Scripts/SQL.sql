select * from app_user;
select * from artist;
select * from album;
select * from song;


select * from user_artist_link;
select * from user_album_link;
select * from user_song_link;
select * from user_concert_link;


select * from user_song_link as link
right join song s on link.song_id = s.id and link.app_user_id = 5
join artist on s.artist_id = artist.id
join album on s.album_id = album.id
where s.name like 'К%';

select * from user_song_link as link
right join song s on link.song_id = s.id and link.app_user_id = 5
join artist a on s.artist_id = a.id
join album al on s.album_id = al.id
where s.album_id = 1