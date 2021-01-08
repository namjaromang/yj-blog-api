--liquibase formatted sql
--changeset yong:2021010108

/** jpa sequence table */
create table hibernate_sequence
(
    next_val bigint null
)
    engine = MyISAM;

/* id값들을 1억 이상으로 디폴트 셋팅 */
insert into hibernate_sequence (next_val) values (100000000);
