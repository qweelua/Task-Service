create table users
(
    id   serial  not null
        constraint users_pk
            primary key,
    name varchar not null
);

alter table users
    owner to postgres;

create unique index users_id_uindex
    on users (id);

