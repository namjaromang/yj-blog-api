--liquibase formatted sql
--changeset yong:2021010102

INSERT INTO role_type (role_type_id, role_name, create_at, create_user_id, update_at, update_user_id)
VALUES (1, 'ROLE_ADMIN', DEFAULT, 1, DEFAULT, 1),
       (2, 'ROLE_USER', DEFAULT, 1, DEFAULT, 1);
