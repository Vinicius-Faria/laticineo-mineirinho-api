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
CREATE TABLE public.produto (
	id serial NOT NULL,
	codigo text NOT NULL,
	nome text NOT NULL,
	preco real,
	descricao text,
	quantidade real
);
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_schema = 'public' and table_name = 'produto'
