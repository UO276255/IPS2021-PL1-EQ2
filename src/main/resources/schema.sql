--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo añadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
DROP TABLE Cliente;
DROP TABLE Presupuestos;
DROP TABLE Productos;
DROP TABLE Proveedor;
DROP TABLE Pedido;
DROP TABLE Solicitudes;
DROP TABLE Venta;
DROP TABLE Transportista;
DROP TABLE Almacen;

CREATE TABLE Cliente (
	Id INT PRIMARY KEY NOT NULL,
	Nombre VARCHAR(32) NOT NULL,
	Apellido VARCHAR(32) NOT NULL,
	fecha_nac DATETIME);

CREATE TABLE Presupuestos (
	Id_pres INT PRIMARY KEY NOT NULL,
	Precio INT NOT NULL,
	Aceptado BIT,
	Fecha_cad DATETIME,
	Id_cliente INT REFERENCES Cliente(Id));

CREATE TABLE Almacen(
	Id_almacen int PRIMARY KEY NOT NULL);
	
CREATE TABLE Productos (
	Id_prod INT PRIMARY KEY NOT NULL,
	Nombre_prod VARCHAR(32) NOT NULL,
	Precio_prod INT NOT NULL,
	Transporte BIT,
	Montaje BIT,
	Categoria VARCHAR(32) NOT NULL,	
	fecha_entrega DATETIME,
	Id_alm INT REFERENCES Almacen(Id_almacen ));

CREATE TABLE Proveedor (
	Id_prov INT PRIMARY KEY NOT NULL);

CREATE TABLE Pedido (
	Id_pedido INT PRIMARY KEY NOT NULL,
	Estado BIT,
	Numero_prod INT NOT NULL,
	Id_prov INT REFERENCES Proveedor(Id_prov),
	Id_prod INT REFERENCES Productos(Id_prod));

CREATE TABLE Solicitudes (
	Id_solic INT PRIMARY KEY NOT NULL,
	Id_pres INT REFERENCES Presupuestos(Id_pres),
	Id_prod INT REFERENCES Productos(Id_prod));

CREATE TABLE Transportista (
	id_transp int PRIMARY KEY NOT NULL,
	Nombre VARCHAR(32) NOT NULL,
	Numero_tel INT NOT NULL,
	hora_entrada DATETIME,
	hora_salida DATETIME);

CREATE TABLE Venta (
	Id_venta INT PRIMARY KEY NOT NULL,
	Fecha_venta DATETIME,
	Precio INT NOT NULL,
	Transporte BIT,
	Id_pres INT REFERENCES Presupuestos(Id_pres),
	Id_transp INT REFERENCES Transportista(Id_transp));