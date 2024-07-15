create table roles (
    id bigint not null auto_increment,
    name varchar(20) not null unique,

    primary key(id)
);
create table usuario_roles (
    usuario_id bigint not null,
    role_id bigint not null,

    primary key(usuario_id, role_id),
    constraint fk_usuario_roles_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_usuario_roles_role_id foreign key(role_id) references roles(id)
);
insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN');
