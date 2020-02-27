-- Исполнитель
insert into "Artist"("Name", "ImageUrl")
values
('Пасош', 'https://indie-windy.s3.eu-north-1.amazonaws.com/photos/1.Pasosh.jpg'),
('Хадн Дадн', null),
('СБПЧ', null);

-- Концерт
insert into "Concert"("Name", "StartTime", "Address", "Cost")
values
('Боль', '2020-01-01 22:00', 'Москва, ул. Пресненский Вал, д. 6, стр. 1', 750);

-- Альбом
insert into "Album"("ArtistId", "Name")
values
(1, '21'),
(1, 'Нам никогда не будет скучно'),
(2, 'Ляоакын'),
(3, 'Наверно, точно');


-- Песня
insert into "Song"("ArtistId", "AlbumId", "Name", "SongUrl")
values
(1, 1, 'Каждый день', null),
(1, 1, 'Никогда', null),
(1, 2, 'Лето', 'https://indie-windy.s3.eu-north-1.amazonaws.com/music/1.Pasosh-Leto.mp3'),
(1, 2, 'Россия', null),
(2, 3, 'Рязань', null),
(2, 3, 'Гулять', null),
(3, 4, 'Злой', null),
(3, 4, 'Король', null);

-- Исполнитель участвует в концерте
insert into "ArtistConcertLink"("ArtistId", "ConcertId")
values
(1, 1),
(2, 1),
(3, 1);



delete from "Album";
delete from "Artist";
delete from "Concert";
delete from "Song";
delete from "UserAlbumLink";
delete from "UserSongLink";
delete from "UserArtistLink";
delete from "UserConcertLink";
delete from "ArtistConcertLink";

ALTER SEQUENCE "Album_Id_seq" RESTART WITH 1;
ALTER SEQUENCE "Artist_Id_seq" RESTART WITH 1;
ALTER SEQUENCE "Concert_Id_seq" RESTART WITH 1;
ALTER SEQUENCE "Song_Id_seq" RESTART WITH 1;
