--liquibase formatted sql

--changeset ks:CRS-2_add_link_sequence

create table if not exists link
(
    id  serial primary key not null,
    url text               not null
);