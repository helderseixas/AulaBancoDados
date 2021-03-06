CREATE TABLE CURSO
(
	ID INTEGER NOT NULL PRIMARY KEY IDENTITY,
	NOME VARCHAR(50) NOT NULL UNIQUE,
	SIGLA CHAR(4) NOT NULL UNIQUE,
	AREA VARCHAR(50) NOT NULL
);

CREATE TABLE ALUNO
(
	MATRICULA INTEGER NOT NULL PRIMARY KEY,
	NOME VARCHAR(50) NOT NULL,
	CPF CHAR(14) NOT NULL UNIQUE,
	DATA_NASCIMENTO DATE NOT NULL,
	SEXO CHAR(1) NOT NULL,
	TELEFONE VARCHAR(20) NULL,
	ENDERECO VARCHAR(100) NOT NULL, 
	NOTA_INGRESSO INTEGER NOT NULL,
	ID_CURSO INTEGER NOT NULL REFERENCES CURSO(ID)
);
