CREATE TABLE dbo.usuario (
	id int4 NOT NULL,
	ativo bool NULL,
	data_cadastro timestamp NULL,
	data_expiracao timestamp NULL,
	email varchar(50) NULL,
	nome varchar(50) NULL,
	senha varchar(10) NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

insert into dbo.usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email) values (1, 'Adilson Silva', '123456', 1, '2019-04-20 23:00:00', '2020-04-20 23:00:00', 'teste@teste.com.br')

insert into dbo.usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email) values (2, 'Teste da Silva', '1234aa', 1, '2019-04-20 23:00:00', '2020-04-20 23:00:00','teste1@teste.com.br')

insert into dbo.usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email) values (3, 'Adilson Rodrigues', 'aa3456', 1, '2019-04-20 23:00:00', '2020-04-20 23:00:00','teste2@teste.com.br')
