CREATE TABLE categoria(
   id bigint(20) primary key auto_increment,
   nome varchar(50) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into categoria(nome) value('Alimentação');
insert into categoria(nome) value('Lazer');
insert into categoria(nome) value('Farmacia');
insert into categoria(nome) value('Supermercado');
insert into categoria(nome) value('Outros');