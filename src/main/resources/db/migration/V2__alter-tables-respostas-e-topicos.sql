alter table topicos
modify column data datetime not null default current_timestamp;
alter table respostas
modify column data datetime not null default current_timestamp;