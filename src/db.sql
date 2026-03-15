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
    matricula      int         not null unique,
    fkdepartamento int         null,
    constraint fkdepartamento
        foreign key (fkdepartamento) references departamento (id)
);

create index fkdepartamento
    on funcionario (fkdepartamento);

create table registroponto
(
    id          int auto_increment
        primary key,
    fkmatricula int       not null,
    datahora    timestamp not null,
    constraint fkfuncionario
        foreign key (fkmatricula) references funcionario (matricula)
);

create index idx_fkmatricula
    on registroponto (fkmatricula);