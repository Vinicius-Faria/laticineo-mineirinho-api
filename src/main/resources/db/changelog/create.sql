--liquibase formatted sql

--changeset viniciusfaria:1
CREATE TABLE public.login (
	id serial NOT NULL,
	login text NOT NULL,
	senha text NOT NULL
);
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_schema = 'public' and table_name = 'login'

--changeset viniciusfaria:2
INSERT INTO public.login (login, senha) VALUES ('admin', 'admin');
--preconditions onFail:MARK_RAN onError:HALT

--changeset viniciusfaria:3
CREATE TABLE public.produto (
	id serial NOT NULL,
	codigo text NOT NULL,
	nome text NOT NULL,
	preco text,
	descricao text,
	quantidade text
);
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_schema = 'public' and table_name = 'produto'

--changeset viniciusfaria:4
CREATE TABLE public.entrada (
	id serial NOT NULL,
	produto text NOT NULL,
	valor text,
	quantidade text
);
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_schema = 'public' and table_name = 'entrada'

--changeset viniciusfaria:5
CREATE TABLE public.saida (
	id serial,
	nome text NOT NULL,
	preco text NOT NULL,
	quantidade text NOT NULL,
	totalProd text,
	total text,
	data timestamp without time zone
);
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_schema = 'public' and table_name = 'saida'

--changeset viniciusfaria:6
ALTER TABLE public.saida ADD COLUMN venda text;
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.columns where table_schema = 'public' and table_name='evento_rms' and column_name='equipamento_id';

--changeset viniciusfaria:7
INSERT INTO public.saida (nome, preco, quantidade, totalProd, total, data, venda) 
VALUES ('0', '0', '0', '0', '0', '2022-01-01 23:00:00', '0');
--preconditions onFail:MARK_RAN onError:HALT
