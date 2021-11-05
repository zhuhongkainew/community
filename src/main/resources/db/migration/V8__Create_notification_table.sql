create table notification
(
    id int auto_increment primary key not null,
    sender int not null,
    receiver int not null,
    type int not null,
    type_name varchar(100) not null,
    title_name varchar(256) not null,
    make_date bigint not null,
    send_name varchar(100) not null,
    question_id int not null,
    status int default 0 not null
);