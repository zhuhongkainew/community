create table comment
(
    id bigint auto_increment,
    parent_id int not null,
    type int not null,
    content varchar2(1024),
    like_count bigint default 0,
    commentator_id int not null,
    gmt_create bigint,
    gmt_modified bigint,
    constraint COMMENT_PK
        primary key (id)
);