select * from app_user;
select * from artist;
select * from album;
select * from song;


select * from user_artist_link;
select * from user_album_link;
select * from user_song_link;
select * from user_concert_link;


select * from song as s
left join user_song_link as link
on s.id= link.song_id and link.app_user_id = 5
where s.name like 'Л%';

select * from user_song_link as link
right join song s on link.song_id = s.id and link.app_user_id = 5
join artist on s.artist_id = artist.id
join album on s.album_id = album.id
where s.name like 'К%';