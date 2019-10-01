 CREATE TABLE lancamento(
 id bigint(20) primary key auto_increment,
   descricao varchar(50) not null,
   data_vencimento date not null ,
   data_pagamento date,
   valor decimal(10,2) not null ,
   observacao varchar(100),
   tipo varchar(20) not null ,
   codigo_categoria bigint(20) not null ,
   codigo_pessoa bigint(20) not null,
   foreign key (codigo_categoria) references categoria(id),
   foreign key (codigo_pessoa) references pessoa(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUE ('Ferrari','2019-3-13','2019-9-25',454.10,'Ferrari 2012','g311',7,8);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUE ('Burgat','2019-4-11','2019-10-23',464.9,'Burgat 2010','h309',6,7);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUE ('Lamborguine','2019-5-9','2019-11-21',474.8,'Lamborguine 2008','i307',5,6);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUE ('Nissan','2019-6-7','2019-12-19',484.7,'Nissan 2006','j305',4,5);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUE ('F1','2019-7-5','2019-1-17',494.6,'F1 2004','k303',3,4);
insert into lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUE ('Lamozine','2019-8-3','2019-2-15',4104.5,'Lamozine 2002','l301',2,3);