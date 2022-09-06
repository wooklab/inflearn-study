create table users
(
    id         int auto_increment primary key,
    user_id    varchar(20),
    pwd        varchar(20),
    name       varchar(20),
    created_at datetime default NOW()
);


insert into users(user_id, pwd, name) values('user1', 'test1111', 'User name');

insert into users(user_id, pwd, name) values('admin', 'admin1111', 'Administrator');
