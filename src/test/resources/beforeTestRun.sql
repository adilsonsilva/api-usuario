delete from usuario;

insert into usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email, usuario_admin) values (1, 'Adilson Silva', '123456', true, '2019-04-20 23:00:00', '2020-04-20 23:00:00', 'teste@teste.com.br', false);

insert into usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email, usuario_admin) values (2, 'Teste da Silva', '1234aa', true, '2019-04-20 23:00:00', '2020-04-20 23:00:00','teste1@teste.com.br', false);

insert into usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email, usuario_admin) values (3, 'Adilson Rodrigues', 'aa3456', true, '2019-04-20 23:00:00', '2020-04-20 23:00:00','teste2@teste.com.br', false);

insert into usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email, usuario_admin) values (4, 'Adilson Rodrigues da Silva', 'aa34511', false, '2019-04-20 23:00:00', '2020-04-20 23:00:00','teste3@teste.com.br', false);


alter sequence usuario_seq restart with 5;
