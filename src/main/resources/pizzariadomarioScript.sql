USE master;

IF exists ( SELECT * FROM SYS.databases WHERE name = 'pizzariamariodb')
   DROP DATABASE pizzariamariodb
GO

CREATE DATABASE pizzariamariodb
GO

USE pizzariamariodb
GO

CREATE TABLE Usuario (

id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
nome VARCHAR(100) NULL,
cpf VARCHAR (20) NULL,
email VARCHAR (45) NOT NULL,
data_nascimento DATE NULL,
password VARCHAR(250) NOT NULL,
sexo VARCHAR(2) NULL,
logradouro VARCHAR(100) NULL,
cep VARCHAR(9) NULL,
bairro VARCHAR(45) NULL,
cidade VARCHAR(45) NULL,
uf CHAR(2) NULL,
tipo_usuario VARCHAR (30) NULL,
numero_pontos VARCHAR(10) NULL,
cnh VARCHAR(20) NULL,
nivel_acesso VARCHAR(20) NULL,
cod_status BIT NOT NULL

)

CREATE TABLE Pedido (
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
numero_pedido VARCHAR(10) NOT NULL,
valor_pedido DECIMAL(5,2) NOT NULL,
data_hora_pedido DATETIME NOT NULL,
data_hora_entrega DATETIME NULL,
status VARCHAR(20) NOT NULL,
cod_status BIT NOT NULL,
cliente_id BIGINT NOT NULL,
CONSTRAINT fk_pedido_usuario_id FOREIGN KEY (cliente_id) REFERENCES usuario(id)
)

CREATE TABLE Categoria (
  id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  descricao VARCHAR(255),
  cod_status BIT NOT NULL
)

CREATE TABLE Produto (
  id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  descricao VARCHAR(255) NULL,
  valor_venda DECIMAL(5,2) NULL,
  valor_compra DECIMAL(5,2) NULL,
  tipo VARCHAR(20) NULL,
  quantidade_estoque int NULL,
  cod_status BIT NOT NULL,
  categoria_id BIGINT NULL,
  CONSTRAINT fk_produto_categoria_id FOREIGN KEY(categoria_id) REFERENCES Categoria (id)
)

CREATE TABLE Telefone (
  id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  numero VARCHAR(12) NOT NULL,
  ddd CHAR(3) NULL,
  tipo VARCHAR(15),
  cod_status BIT NOT NULL,
  usuario_id BIGINT NULL,
  CONSTRAINT fk_telefone_usuario_id FOREIGN KEY(usuario_id) REFERENCES Usuario (id)
)

CREATE TABLE ItemPedido (
  id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  quantidade_item INT NOT NULL,
  valor_unitario DECIMAL(5,2) NOT NULL,
  cod_status BIT NOT NULL,
  produto_id BIGINT NOT NULL,
  pedido_id BIGINT NOT NULL,
  CONSTRAINT fk_itemPedido_produto_id FOREIGN KEY(produto_id) REFERENCES Produto (id),
  CONSTRAINT fk_itemPedido_pedido_id FOREIGN KEY(pedido_id) REFERENCES Pedido (id)
)


CREATE TABLE tokens (
  id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  token VARCHAR(255) NULL,
  revoked BIT NOT NULL,
  expired BIT NOT NULL,
  token_type VARCHAR(255) NULL,
  usuario_id BIGINT NULL,
  CONSTRAINT fk_tokens_usuario_id FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
)


