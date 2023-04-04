--liquibase formatted sql

--changeset ks:CRS-1_add_client_sequence

create table if not exists Client
(
    chat_id   bigint primary key not null,
    user_name text               not null
);