CREATE TABLE Autor(
	Id INT IDENTITY(1,1),
	Nome varchar(max) NOT NULL,
	PRIMARY KEY (Id)
);

CREATE TABLE Editora(
	Id INT IDENTITY(1,1),
	Nome varchar(max) NOT NULL,
	PRIMARY KEY (Id)
);

CREATE TABLE Usuario(
	Id INT IDENTITY(1,1),
	Nome varchar(50) NOT NULL,
	email varchar(255) NOT NULL,
	senha varchar(20) NOT NULL,

	PRIMARY KEY (Id)
);

CREATE TABLE Livros(
	Id INT IDENTITY(1,1),
	Nome varchar(max) NOT NULL,
	Id_Autor int NOT NULL,
	Id_Editora int NOT NULL,
	Data_publicacao char(8),
	Descricao varchar(1000) NOT NULL,
	numero_paginas varchar(10) NOT NULL,
	Url_foto nvarchar(500) NOT NULL,
	--Id_user int NOT NULL,

	PRIMARY KEY (Id),
	FOREIGN KEY (Id_Autor) REFERENCES Autor(Id),
	FOREIGN KEY (Id_Editora) REFERENCES Editora(Id),
	--FOREIGN KEY (Id_user) REFERENCES Usuario(Id)
);

CREATE TABLE Avaliacao(
	Id INT IDENTITY(1,1),
	Nota char(1),
	Comentario varchar(255),
	Id_Livros int NOT NULL,

	PRIMARY KEY (Id),
	FOREIGN KEY (Id_Livros) REFERENCES Livros(Id)

);


CREATE TABLE Status(
    Id INT IDENTITY(1,1),
    Status varchar(10)

)
