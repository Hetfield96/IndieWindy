-- Поиск песен добавленных пользователем
select art.name as artist_name, a.name as album_name, s.name as song_name
from user_songs us
join song s on us.song_id = s.id
join album a on s.album_id = a.id
join artist art on a.artist_id = art.id
where us.user_id = 1;

-- Поиск концертов группы
select c.* from artist_concerts ac
join concert c on ac.concert_id = c.id
where ac.artist_id = 1;

-- Поиск групп участвующих в концерте
select a.name from artist_concerts ac
join artist a on ac.artist_id = a.id
where ac.concert_id = 1;