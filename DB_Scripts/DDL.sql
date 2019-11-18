drop table "user" cascade;
drop table artist cascade;
drop table album cascade;
drop table song cascade;
drop table concert cascade;
drop table user_artist_link_subscriptions cascade;
drop table user_song_link_added cascade;
drop table artist_concert_link cascade;

-- Пользователь приложения
create table "user"(
    id serial primary key,
    name varchar not null,
    login varchar not null,
    password varchar not null
);
insert into "user"(name, login, password)
values
('Sergey Roytman', 'royt', '0341da'),
('Fedor Haleev', 'fedos', '1234');

-- Исполнитель
create table artist(
    id serial primary key,
    name varchar not null
);
insert into artist(name)
values
('Пасош'),
('Хадн Дадн'),
('СБПЧ');

-- Альбом
create table album(
    id serial primary key,
    artist_id int not null,
    name varchar not null,
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
    name varchar not null,
    foreign key (album_id) references album(id),
    foreign key (artist_id) references artist(id)
);
insert into song(artist_id, album_id, name)
values
(1, 1, 'Каждый день'),
(1, 1, 'Никогда'),
(1, 2, 'Лето'),
(1, 2, 'Россия'),
(2, 3, 'Рязань'),
(2, 3, 'Гулять'),
(3, 4, 'Злой'),
(3, 4, 'Король');

-- Концерт
create table concert(
    id serial primary key,
    name varchar not null,
    start_time timestamp not null,
    club_name varchar not null,
    address varchar not null,
    cost int not null
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
    foreign key (user_id) references "user"(id),
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
    foreign key (user_id) references "user"(id),
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