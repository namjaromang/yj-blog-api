--liquibase formatted sql
--changeset yong:2020122301

create table user
(
    user_id        bigint                                 not null primary key,
    login_id          varchar(20)                           not null comment '로그인아이디',
    password       varchar(100)                           null,
    username       varchar(50)                            not null,
    is_enable     tinyint(1) default 0                   not null,
    withdraw       tinyint(1) default 0                   not null,
    last_login_at  datetime   default current_timestamp() not null,
    create_at      datetime   default current_timestamp() not null,
    create_user_id bigint                                 not null,
    update_at      datetime   default current_timestamp() not null on update current_timestamp(),
    update_user_id bigint                                 not null
);