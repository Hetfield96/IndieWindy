select * from app_user;
select * from artist;
select * from album;
select * from song;

select * from user_artist_link;
select * from user_album_link;
select * from user_song_link;
select * from user_concert_link;

delete from user_song_link
where app_user_id = 4;

delete from app_user
where id > 1;

update song
set song_url = 'http://indie-windy.s3.eu-north-1.amazonaws.com/music/Pasosh/Pasosh-rossiya.mp3'
where id = 4;


select * from song as s
left join user_song_link as link
on s.id= link.song_id and link.app_user_id = 4
where s.name like 'Л%';

select * from user_song_link as link
  right join song s on link.song_id = s.id
  where s.name like 'Л%' and link.app_user_id = 4;