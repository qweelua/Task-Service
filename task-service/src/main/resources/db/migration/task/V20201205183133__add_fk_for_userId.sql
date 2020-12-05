alter table tasks
    add constraint tasks_users_id_fk
        foreign key ("userId") references users;