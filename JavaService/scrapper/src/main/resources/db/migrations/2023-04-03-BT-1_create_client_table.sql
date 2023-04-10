--liquibase formatted sql

--changeset ks:CRS-1_add_client_sequence

create table if not exists client
(
    chat_id   serial primary key not null,
    user_name text               not null
);