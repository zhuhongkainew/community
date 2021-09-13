alter table USER add password varchar2(255);
alter table USER add salt varchar2(6);
alter table USER add email varchar2(255);
alter table USER add status int default 0;