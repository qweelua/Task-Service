create table columns
(
    id serial not null,
    name varchar not null
);

create unique index columns_id_uindex
    on columns (id);

alter table columns
    add constraint columns_pk
        primary key (id);

