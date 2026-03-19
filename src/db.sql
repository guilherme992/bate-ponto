create table departamento
(
    id   int auto_increment
        primary key,
    nome varchar(45) not null
);

create table funcionario
(
    id             int auto_increment
        primary key,
    nome           varchar(45) not null,
    matricula      int         not null,
    fkdepartamento int         null,
    constraint matricula
        unique (matricula),
    constraint fkdepartamento
        foreign key (fkdepartamento) references departamento (id)
);

create table registroponto
(
    id            int auto_increment
        primary key,
    fkfuncionario int       not null,
    datahora      timestamp not null,
    constraint fkfuncionario
        foreign key (fkfuncionario) references funcionario (matricula)
);

create index idx_fkmatricula
    on registroponto (fkfuncionario);

