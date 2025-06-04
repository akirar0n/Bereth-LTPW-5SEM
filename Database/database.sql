drop database berethdb;
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
    imagem varchar(700),
    preco double,
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

create table pedidos (
	id int primary key auto_increment,
    data_pedido datetime default current_timestamp,
    valor_total double,
    idUsuario int not null,
    foreign key (idUsuario) references usuario(idUsuario)
    ) Engine = InnoDB;
    
create table pedido_veiculo (
	id_pedido int,
    idVeiculo int,
    quantidade int not null,
    subtotal double,
    primary key(id_pedido, idVeiculo),
    foreign key(id_pedido) references pedidos(id),
    foreign key(idVeiculo) references veiculo(idVeiculo)
    ) Engine = InnoDB;
    
create table carrinho(
	idUsuario int not null, 
    idVeiculo int not null,
    quantidade int not null,
    primary key(idUsuario, idVeiculo),
    foreign key(idUsuario) references usuario(idUsuario),
    foreign key(idVeiculo) references veiculo(idVeiculo)
) Engine = InnoDB;



select * from usuario;
select * from pedidos;
select * from pedido_veiculo;
select * from veiculo;
select * from carrinho;

alter table veiculo add column imagem varchar(700);
alter table veiculo add column preco double;

SELECT p.id AS pedido_id, p.data_pedido, p.valor_total,
                   v.modelo AS nome_veiculo, pv.quantidade, pv.subtotal
            FROM pedidos p
            JOIN pedido_veiculo pv ON p.id = pv.id_pedido
            JOIN veiculo v ON pv.idVeiculo = v.idVeiculo
            WHERE p.idUsuario = 1
            ORDER BY p.id DESC
