delete from usuario;

insert into usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email) values (1, 'Adilson Silva', '123456', 1, '2019-04-20 23:00:00', '2020-04-20 23:00:00', 'teste@teste.com.br');

insert into usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email) values (2, 'Teste da Silva', '1234aa', 1, '2019-04-20 23:00:00', '2020-04-20 23:00:00','teste1@teste.com.br');

insert into usuario (id, nome, senha, ativo, data_cadastro, data_expiracao, email) values (3, 'Adilson Rodrigues', 'aa3456', 1, '2019-04-20 23:00:00', '2020-04-20 23:00:00','teste2@teste.com.br');

alter sequence usuario_seq restart with 4;
