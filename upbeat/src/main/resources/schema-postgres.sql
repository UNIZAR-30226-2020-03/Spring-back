DROP TABLE IF EXISTS Cliente;
CREATE TABLE Cliente(cod_cliente serial PRIMARY KEY, client_name VARCHAR(255), password VARCHAR(255),nombre VARCHAR(255),apellidos VARCHAR(255),correo VARCHAR(255));