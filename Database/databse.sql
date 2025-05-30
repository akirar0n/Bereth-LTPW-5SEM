create schema berethdb;
use berethdb;


create table usuario(
	idUsuario int auto_increment,
	acesso enum("Administrador","Cliente"),
	email varchar(255),
    senha varchar(255),
	cpf varchar(16),
	rg varchar(16), -- por alguma razão o RG está saindo como 'rq' no select, mesmo verificadas a ortografia no banco e classe.
	nome varchar(255),
	idade int,
	nascimento date,
		primary key(idUsuario)
);


create table veiculo(
	idVeiculo INT auto_increment,
	categoria ENUM("Carro", "Moto"),
	tipo ENUM("Sedan", "SUV", "Hatch", "Picape", "Minivan", "Cupê",
			"Perua", "Roadster", "Utilitário","Esportiva", "Naked", 
			"Custom", "Touring", "Big Trail", "Scooter", "Ciclomotor", 
			"Motocross", "Enduro"),
	marca VARCHAR(30),
	modelo VARCHAR(30),
	cor VARCHAR(30),
	rodas INT,
	motorizacao FLOAT,
	pesoKg FLOAT,
	capacidadeTanqueL FLOAT,
	assentos INT(11),
	anoFabricacao YEAR(4),
	anoModelo YEAR(4),
	placa VARCHAR(8),
	chassi VARCHAR(17),
		primary key(idVeiculo)
);

create table venda(
	idVenda int auto_increment,
    idUsuario int, -- comprador
    idVeiculo int,
    formaPagamento enum("Débito","Crédito","Dinheiro","Bitcoin"),
    parcelas int,
    valor decimal(15,2),
    dataVenda datetime,
		primary key (idVenda),
        foreign key (idUsuario) references usuario(idUsuario),
        foreign key (idVeiculo) references veiculo(idVeiculo)
	
);
