CREATE TABLE pessoa(
   id bigint(20) primary key auto_increment,
   nome varchar(50) not null,
   zona varchar(20),
   bairo varchar(20),
   distrito varchar(20),
   numero varchar(20),
   ativo boolean
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Octaniel','Madre de deus','Deposito novo','Agua Grande','9888584',true);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Augusta','Madre de deus','Deposito velho','Agua Grande','9865584',false);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('José','Agua doutor','Centro','Principe','9888509',true);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Tavinho','Bobó Foro','Baixo','Agua Grande','9438584',false);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Marciel','Picão','Praia Inhame','Principe','9875384',true);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Maci','Aeroporto','Ceração','Principe','9882345',false);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Lizania','São João','Centro','Agua Grande','6448584',true);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Carvalho','Madre de deus',null,'Agua Grande',null,false);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Maria','Cruzamento de Picão',null,'Principe','9876546',true);
INSERT INTO pessoa(nome, zona, bairo, distrito, numero, ativo) VALUE ('Fernanda','Fundação','Popular','Agua Grande','9898784',false);