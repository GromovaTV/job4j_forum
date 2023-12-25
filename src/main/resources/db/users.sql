create table IF NOT EXISTS "users"(
id serial primary key,
username varchar(2000) UNIQUE,
password text,
enabled boolean,
authority_id int not null references authorities(id)
);