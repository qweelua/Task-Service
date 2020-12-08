create table tasks
(
    id serial not null,
    name varchar not null,
    description varchar not null,
    "dateOfCreate" date not null
);

create unique index tasks_id_uindex
    on tasks (id);

