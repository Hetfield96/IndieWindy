create table "user"(
    id int primary key,
    name varchar not null,
    login varchar not null,
    password varchar not null
);

create table artist(
    id int primary key,
    name varchar not null
);

create table album(
    id int primary key,
    name varchar not null,
    artist_id int not null,
    foreign key (artist_id) references artist
);

create table song(
    id int primary key,
    name varchar not null,
    album_id int,
    artist_id int not null,
    foreign key (album_id) references album(id),
    foreign key (artist_id) references artist(id)
);

create table concert(
    id int primary key,
    name varchar not null,
    start_time timestamp not null,
    address varchar not null,
    cost int not null
);

create table user_artist_link_subscriptions(
    user_id int not null,
    artist_id int not null,
    foreign key (user_id) references "user"(id),
    foreign key (artist_id) references artist(id),
    primary key (user_id, artist_id)
);

create table user_song_link_added(
    user_id int not null,
    song_id int not null,
    foreign key (user_id) references "user"(id),
    foreign key (song_id) references song(id),
    primary key (user_id, song_id)
);

create table artist_concert_link(

);

