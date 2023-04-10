--liquibase formatted sql

--changeset ks:CRS-3_add_userlinks_sequence

create table if not exists userlinks
(
    id       serial primary key not null,
    user_id  bigint references client (chat_id),
    links_id bigint references link (id)
);