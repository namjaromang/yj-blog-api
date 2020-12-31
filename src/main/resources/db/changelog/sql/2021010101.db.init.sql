--liquibase formatted sql
--changeset yong:2020010101

create table user_role
(
    user_role_id        bigint                                 not null primary key,
    user_id          varchar(20)                           not null,
    role_type_id       varchar(100)                           null,
    create_at      datetime   default current_timestamp() not null,
    create_user_id bigint                                 not null,
    update_at      datetime   default current_timestamp() not null on update current_timestamp(),
    update_user_id bigint                                 not null
);

create table role_type
(
    role_type_id      bigint                          not null primary key,
    role_name       varchar(40)                           null,
    create_at      datetime   default current_timestamp() not null,
    create_user_id bigint                                 not null,
    update_at      datetime   default current_timestamp() not null on update current_timestamp(),
    update_user_id bigint                                 not null
);

create table menu
(
    menu_id        bigint                                 not null primary key,
    create_at      datetime   default current_timestamp() not null,
    create_user_id bigint                                 not null,
    update_at      datetime   default current_timestamp() not null on update current_timestamp(),
    update_user_id bigint                                 not null
);

create table content
(
    content_id     bigint                          not null primary key,
    menu_id        bigint                                 null,
    title          varchar(20)                            null,
    content        varchar(100)                           null,
    create_at      datetime   default current_timestamp() not null,
    create_user_id bigint                                 not null,
    update_at      datetime   default current_timestamp() not null on update current_timestamp(),
    update_user_id bigint                                 not null
);