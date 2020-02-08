/*
CREATE DATABASE indie_windy_db;
CREATE USER indie_admin WITH ENCRYPTED PASSWORD 'pass' SUPERUSER;
GRANT ALL PRIVILEGES ON DATABASE indie_windy_db TO indie_admin;
*/

drop table if exists appUser cascade;
drop table if exists artist cascade;
drop table if exists album cascade;
drop table if exists song cascade;
drop table if exists concert cascade;
drop table if exists user_artist_link_subscriptions cascade;
drop table if exists user_song_link_added cascade;
drop table if exists artist_concert_link cascade;

-- Пользователь приложения
create table appUser(
    id serial primary key,
    name varchar(20) unique,
    password text not null
);
insert into appUser(name, password)
values
('royt', 'dfdfdfr547gh45gh458gh45'),
('fedos', '554545454vtrgv45fesdlg');

-- Исполнитель
create table artist(
    id serial primary key,
    name varchar(30) not null,
    image_url varchar(200) null
);
insert into artist(name, image_url)
values
('Пасош', 'https://indie-windy.s3.eu-north-1.amazonaws.com/photos/1.Pasosh.jpg'),
('Хадн Дадн', null),
('СБПЧ', null);

-- Альбом
create table album(
    id serial primary key,
    artist_id int not null,
    name varchar(30) not null,
    image_url varchar null,
    foreign key (artist_id) references artist
);
insert into album(artist_id, name)
values
(1, '21'),
(1, 'Нам никогда не будет скучно'),
(2, 'Ляоакын'),
(3, 'Наверно, точно');

-- Песня
create table song(
    id serial primary key,
    artist_id int not null,
    album_id int,
    name varchar(30) not null,
    song_url varchar null,
    foreign key (album_id) references album(id),
    foreign key (artist_id) references artist(id)
);
insert into song(artist_id, album_id, name, song_url)
values
(1, 1, 'Каждый день', null),
(1, 1, 'Никогда', null),
(1, 2, 'Лето', 'https://indie-windy.s3.eu-north-1.amazonaws.com/music/1.Pasosh-Leto.mp3'),
(1, 2, 'Россия', null),
(2, 3, 'Рязань', null),
(2, 3, 'Гулять', null),
(3, 4, 'Злой', null),
(3, 4, 'Король', null);

-- Концерт
create table concert(
    id serial primary key,
    name varchar(50) not null,
    start_time timestamp not null,
    club_name varchar(30) not null,
    address varchar not null,
    cost int not null,
    image_url varchar null
);
insert into concert(name, start_time, club_name, address, cost)
values
('Боль', '2020-01-01 22:00', '16 ТОНН', 'Москва, ул. Пресненский Вал, д. 6, стр. 1', 750);

-- Исполнитель участвует в концерте
create table artist_concert_link(
    artist_id int not null,
    concert_id int not null,
    foreign key (artist_id) references artist(id),
    foreign key (concert_id) references concert(id),
    primary key (artist_id, concert_id)
);
insert into artist_concert_link(artist_id, concert_id)
values
(1, 1),
(2, 1),
(3, 1);

-- Подписка пользователя на артиста
create table user_artist_link_subscriptions(
    user_id int not null,
    artist_id int not null,
    foreign key (user_id) references appUser(id),
    foreign key (artist_id) references artist(id),
    primary key (user_id, artist_id)
);
insert into user_artist_link_subscriptions(user_id, artist_id)
values
(1, 2),
(1, 3),
(2, 1),
(2, 3);

-- Добавленные пользователем песни
create table user_song_link_added(
    user_id int not null,
    song_id int not null,
    foreign key (user_id) references appUser(id),
    foreign key (song_id) references song(id),
    primary key (user_id, song_id)
);
insert into  user_song_link_added(user_id, song_id)
values
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(2, 1),
(2, 2),
(2, 3),
(2, 4);