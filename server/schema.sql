drop database if exists yet_another_movie_app;

create database yet_another_movie_app;

use yet_another_movie_app;

create table roles (
    id int not null auto_increment primary key,
    name enum('ROLE_USER', 'ROLE_ADMIN')
);

create table users (
    id bigint not null auto_increment primary key,
    username varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(120) not null,
    unique (username)
);

create table user_roles (
    user_id bigint,
    role_id int,
    primary key (user_id, role_id),
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

insert into roles(name) values('ROLE_ADMIN');
insert into roles(name) values('ROLE_USER');

grant all privileges on yet_another_movie_app.* to 'cce'@'%';

flush privileges;

