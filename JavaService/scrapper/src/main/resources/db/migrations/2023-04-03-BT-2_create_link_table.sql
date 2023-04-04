--liquibase formatted sql

--changeset ks:CRS-2_add_link_sequence

create table if not exists Link
(
    id      serial primary key not null,
    link    text               not null,
    user_id bigint references Client (chat_id)
);