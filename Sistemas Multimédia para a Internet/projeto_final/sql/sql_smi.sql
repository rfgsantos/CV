CREATE TABLE UserClient(
	tipo varchar(10) not null,
	nome varchar(160) not null,
	username varchar(20) not null,
	password varchar(160) not null,
	email varchar(160) not null,
	valido boolean not null,
	Fotografia_perfil varchar(160) not null,
	Fotografia_real varchar(160) not null,
	Fotografia_aux varchar(160) not null,
	CONSTRAINT unique_email UNIQUE(email),
	CONSTRAINT pk_username primary key(username)	

	
	
	
);
CREATE TABLE Evento(
	DataHora dateTIME not null DEFAULT Now(),
	nome varchar(160) not null,
	latitude varchar(160) not null,
	longitude varchar(160) not null,
	username varchar(20) not null,
	descricao varchar(512) not null,
	CONSTRAINT fk_username2  foreign key(username) references UserClient(username),
	constraint pk_nomeEvento primary key (nome)
);


create table userclient_Evento_Subscrever(
	username varchar(160) not null,
	nome varchar (160) not null,
	constraint fk_username_client  foreign key(username) references UserClient(username),
	constraint fk_EventName foreign key(nome) references Evento(nome),
	constraint pk_subscribe primary key(username,nome)
);

CREATE TABLE Ficheiro(
	nome varchar(160) not null,
	id int not null,
	username varchar(20) not null,
	mime varchar(15) not null,
	tipo varchar(15) not null,
	nomeEvento varchar(160) not null,
	conteudo varchar(160) not null,
	conteudo_thumbs varchar(160) not null,
	visibilidade varchar(160) not null,
	constraint fk_user foreign key(username) references userclient(username),
	constraint fk_nome foreign key(nomeEvento) references Evento(nome),
	CONSTRAINT pk_nome primary key(nome)
);


