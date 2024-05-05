CREATE SCHEMA dml;
USE dml;


CREATE TABLE cantor (
    cod_cantor integer NOT NULL auto_increment,
    nome_cantor character varying(50),
    pais character varying(30),
    CONSTRAINT cantor_pkey PRIMARY KEY (cod_cantor)
);


CREATE TABLE categoria (
    cod_categoria integer NOT NULL auto_increment,
    desc_categoria character varying(50),
    CONSTRAINT categoria_pkey PRIMARY KEY (cod_categoria)
);


CREATE TABLE gravadora (
    cod_gravadora integer NOT NULL auto_increment,
    nome_gravadora character varying(50),
    pais character varying(50),
    CONSTRAINT gravadora_pkey PRIMARY KEY (cod_gravadora)
);


CREATE TABLE musica (
    cod_musica integer NOT NULL AUTO_INCREMENT,
    cod_categoria integer NOT NULL,
    duracao integer,
    titulo character varying(100),
    CONSTRAINT musica_pkey PRIMARY KEY (cod_musica),
    CONSTRAINT musica_cod_categoria_fkey FOREIGN KEY (cod_categoria) REFERENCES categoria(cod_categoria)
);

CREATE TABLE gravacao (
    cod_gravacao integer NOT NULL auto_increment,
    cod_gravadora integer NOT NULL,
    cod_cantor integer NOT NULL,
    cod_musica integer NOT NULL,
    data_gravacao date,
    CONSTRAINT gravacao_pkey PRIMARY KEY (cod_gravacao),
    CONSTRAINT gravacao_cod_cantor_fkey FOREIGN KEY (cod_cantor) REFERENCES cantor(cod_cantor),
    CONSTRAINT gravacao_cod_gravadora_fkey FOREIGN KEY (cod_gravadora) REFERENCES gravadora(cod_gravadora),
    CONSTRAINT gravacao_cod_musica_fkey FOREIGN KEY (cod_musica) REFERENCES musica(cod_musica)
);


CREATE TABLE pessoa (
    cod_pessoa integer NOT NULL AUTO_INCREMENT,
    nome_pessoa character varying(70),
    CONSTRAINT pessoa_pkey PRIMARY KEY (cod_pessoa)
);

CREATE TABLE fone (
    cod_fone integer NOT NULL AUTO_INCREMENT,
    numero character varying(80),
    tipo character(1),
    cod_pessoa integer,
    CONSTRAINT fone_pkey PRIMARY KEY (cod_fone),
    CONSTRAINT fone_cod_pessoa_fkey FOREIGN KEY (cod_pessoa) REFERENCES pessoa(cod_pessoa)
);



INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (1, 'Marisa Monte', 'Brasil');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (2, 'Coldplay', 'Inglaterra');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (3, 'U2', 'Irlanda');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (4, 'Djavan', 'Brasil');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (5, 'Laura Pausini', 'Italia');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (6, 'Roberto Leal', 'Portugal');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (7, 'The Corrs', 'Estados Unidos');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (8, 'Legiao Urbana', 'Brasil');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (9, 'Cazuza', 'Brasil');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (10, 'Tom Jobim', 'Brasil');
INSERT INTO cantor (cod_cantor, nome_cantor, pais) VALUES (11, 'Frank Sinatra', 'Estados Unidos');


INSERT INTO categoria (cod_categoria, desc_categoria) VALUES (1, 'MPB');
INSERT INTO categoria (cod_categoria, desc_categoria) VALUES (2, 'Rock');
INSERT INTO categoria (cod_categoria, desc_categoria) VALUES (3, 'Vira');
INSERT INTO categoria (cod_categoria, desc_categoria) VALUES (4, 'Bossa Nova');
INSERT INTO categoria (cod_categoria, desc_categoria) VALUES (5, 'Jazz');
INSERT INTO categoria (cod_categoria, desc_categoria) VALUES (6, 'Pop rock');
INSERT INTO categoria (cod_categoria, desc_categoria) VALUES (7, 'Eletronic');
INSERT INTO categoria (cod_categoria, desc_categoria) VALUES (8, 'Pop');


INSERT INTO gravadora (cod_gravadora, nome_gravadora, pais) VALUES (1, 'Sony', 'Estados Unidos');
INSERT INTO gravadora (cod_gravadora, nome_gravadora, pais) VALUES (2, 'Som livre', 'Brasil');
INSERT INTO gravadora (cod_gravadora, nome_gravadora, pais) VALUES (3, 'EMI', 'Estados Unidos');
INSERT INTO gravadora (cod_gravadora, nome_gravadora, pais) VALUES (4, 'Globo', 'Brasil');
INSERT INTO gravadora (cod_gravadora, nome_gravadora, pais) VALUES (5, 'Trama', 'Brasil');


INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (1, 'Ana');
INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (2, 'Beto');
INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (3, 'Carlos');
INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (4, 'Daniel');
INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (5, 'Eduardo');
INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (6, 'Flavio');
INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (7, 'Giuliana');
INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (8, 'Helena');
INSERT INTO pessoa (cod_pessoa, nome_pessoa) VALUES (9, 'Ivan');


INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (1, '3333-1111', 'R', 1);
INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (2, '4444-1111', 'C', 1);
INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (3, '9999-1111', 'L', 1);
INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (4, '3333-2222', 'R', 2);
INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (5, '4444-2222', 'C', 2);
INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (6, '9999-2222', 'L', 2);
INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (7, '3333-3333', 'R', 3);
INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (9, '8888-3333', 'L', 3);
INSERT INTO fone (cod_fone, numero, tipo, cod_pessoa) VALUES (8, '4444-3333', 'C', 3);


INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (1, 1, 240, 'Amor I love you');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (2, 1, 300, 'Nao e facil');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (3, 1, 250, 'Gentileza');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (4, 2, 500, 'Daniel na cova dos leoes');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (5, 2, 322, 'Fabrica');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (6, 2, 440, 'Tempo perdido');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (7, 2, 312, 'Acrilic on canvas');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (8, 3, 298, 'Vira-vira');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (9, 4, 348, 'Chega de saudade');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (10, 4, 231, 'Luiza');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (11, 4, 355, 'Aguas de marco');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (12, 4, 250, 'Wave');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (13, 6, 333, 'Politik');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (14, 6, 225, 'Green eyes');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (15, 6, 440, 'A Rush of Blood to the head');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (16, 6, 320, 'Clocks');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (17, 6, 300, 'Codinome beija-flor');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (18, 6, 290, 'Faz parte do meu show');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (19, 5, 446, 'New York');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (20, 8, 299, 'Solitudine');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (21, 1, 430, 'Oceano');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (22, 2, 511, 'With or without you');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (23, 2, 300, 'Beautiful day');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (24, 2, 458, 'Bullet The Blue Sky');
INSERT INTO musica (cod_musica, cod_categoria, duracao, titulo) VALUES (25, 1, 300, 'Sua');



INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (1, 1, 1, 1, '2000-07-10');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (2, 1, 1, 2, '2000-12-07');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (3, 1, 1, 3, '2001-05-30');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (4, 3, 8, 4, '2005-12-29');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (5, 3, 8, 5, '1993-04-25');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (6, 3, 8, 6, '1989-01-31');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (7, 3, 8, 7, '1991-12-01');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (8, 3, 6, 8, '1988-07-30');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (9, 4, 10, 9, '1978-10-14');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (10, 4, 10, 10, '1975-08-11');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (11, 4, 10, 11, '1979-05-05');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (12, 4, 10, 12, '1981-04-18');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (13, 1, 2, 13, '2004-09-12');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (14, 1, 2, 14, '2004-09-20');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (16, 1, 2, 16, '2004-10-01');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (17, 4, 9, 17, '1986-06-30');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (18, 5, 9, 18, '1987-07-06');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (19, 1, 11, 19, '1971-08-29');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (20, 3, 5, 20, '1998-10-10');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (21, 5, 4, 21, '1995-01-20');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (22, 1, 3, 22, '1989-05-05');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (23, 1, 3, 23, '1991-10-20');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (24, 1, 3, 24, '1992-03-25');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (25, 5, 1, 25, '1998-10-20');
INSERT INTO gravacao (cod_gravacao, cod_gravadora, cod_cantor, cod_musica, data_gravacao) VALUES (15, 1, 2, 15, '2004-08-30');

