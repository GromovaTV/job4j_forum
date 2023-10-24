create table IF NOT EXISTS posts (
id serial primary key,
name varchar(2000),
description text,
created timestamp without time zone not null default now()
);

create table IF NOT EXISTS users (
id serial primary key,
password varchar(100) NOT NULL,
username varchar(50) NOT NULL unique,
enabled boolean
);