select u.name, s.name, album.name, a.name
from user_song_link_added added
join "user" u on u.id = added.user_id
join song s on added.song_id = s.id
join album on s.album_id = album.id
join artist a on s.artist_id = a.id
where u.name = 'Sergey Roytman'