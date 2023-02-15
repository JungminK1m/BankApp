CREATE TABLE user_tb(
    id int auto_increment primary key,
    username varchar unique not null,
    password varchar not null,
    email varchar not null,
    profile varchar,
    created_at timestamp not null
);