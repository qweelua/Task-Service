create table tasks
(
    id serial not null,
    "userId" serial not null,
    name varchar not null,
    description varchar not null,
    "dateOfCreate" date not null
);

create unique index tasks_id_uindex
    on tasks (id);

create unique index tasks_userid_uindex
    on tasks ("userId");

