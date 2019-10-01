create table usuario(
id bigint(20) primary key ,
nome varchar(30) not null ,
email varchar(50) not null ,
senha varchar(150) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table permisao(
    id bigint(20) primary key ,
    descricao varchar(50) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table usuario_permisao(
    id_usuario bigint(20) not null ,
    id_permisao bigint(20) not null ,
    primary key (id_usuario,id_permisao),
    foreign key (id_usuario) references usuario(id),
    foreign key (id_permisao) references permisao(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into usuario(id, nome, email, senha) VALUE(1,'Octaniel','otanieljose30@gmail.com','$2a$10$4x2N27RRbbC1jlrssP1OMeIquCD59wXZ9xtTyUVIdrdEwATs2GlYC');
insert into usuario(id, nome, email, senha) VALUE(2,'José','jose30@gmail.com','$2a$10$w5Ci/ochyBq./23YWpCXMeGhVmOOt69JSI4PYIy6NbpUIFqDcUBr.');

insert into permisao(id, descricao) VALUE (1,'ROLE_CADASTRAR_CATEGORIA');
insert into permisao(id, descricao) VALUE (2,'ROLE_CADASTRAR_PESSOA');
insert into permisao(id, descricao) VALUE (3,'ROLE_CADASTRAR_LANCAMENTO');
insert into permisao(id, descricao) VALUE (4,'ROLE_PESQUISAR_CATEGORIA');
insert into permisao(id, descricao) VALUE (5,'ROLE_PESQUISAR_PESSOA');
insert into permisao(id, descricao) VALUE (6,'ROLE_PESQUISAR_LANCAMENTO');
insert into permisao(id, descricao) VALUE (7,'ROLE_REMOVER_PESSOA');
insert into permisao(id, descricao) VALUE (8,'ROLE_REMOVER_LANCAMENTO');

INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (1,1);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (1,2);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (1,3);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (1,4);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (1,5);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (1,6);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (1,7);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (1,8);

INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (2,4);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (2,5);
INSERT INTO usuario_permisao(id_usuario, id_permisao) VALUE (2,6);