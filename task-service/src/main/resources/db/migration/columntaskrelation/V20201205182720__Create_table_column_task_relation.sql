create table column_task_relation
(
    "columnId" integer
        constraint column_task_relation_columns_id_fk
            references columns,
    "taskId"   integer not null
        constraint column_task_relation_pk
            primary key
        constraint column_task_relation_tasks_id_fk
            references tasks (id)
);

alter table column_task_relation
    owner to postgres;

