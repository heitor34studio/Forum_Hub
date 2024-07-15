create table cursos(
    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    categoria varchar(100) not null,

    primary key(id)
);
create table usuarios(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(256) not null,

    primary key(id)
);
create table topicos(
    id bigint not null auto_increment,
    titulo varchar(200) not null,
    mensagem varchar(1000) not null,
    data datetime not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    constraint fk_topicos_autor_id foreign key(autor_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)
);
create table respostas(
    id bigint not null auto_increment,
    mensagem varchar(100) not null,
    solucao varchar(1000) not null,
    data datetime not null,
    topico_id bigint not null,
    autor_id bigint not null,

    primary key(id),
    constraint fk_respostas_usuario_id foreign key(topico_id) references topicos(id),
    constraint fk_respostas_curso_id foreign key(autor_id) references usuarios(id)
);