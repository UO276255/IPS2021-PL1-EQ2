--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
DROP TABLE IF EXISTS Carreras;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Presupuestos;
DROP TABLE IF EXISTS Productos;
DROP TABLE IF EXISTS Almacen;
DROP TABLE IF EXISTS Proveedor;
DROP TABLE IF EXISTS Pedido;
DROP TABLE IF EXISTS Solicitudes
DROP TABLE IF EXISTS Repuesto;
DROP TABLE IF EXISTS Venta;
DROP TABLE IF EXISTS Transportista;

CREATE TABLE IF NOT EXISTS carreras (
	id int primary key not null,
	inicio date not null,
  	fin date not null,
   	fecha date not null,
    descr varchar(32),
    check(inicio<=fin),
    check(fin<fecha));)

CREATE TABLE Cliente (
	Id INT PRIMARY KEY NOT NULL,
	Nombre VARCHAR(32) NOT NULL,
	Apellido VARCHAR(32) NOT NULL,
	Fecha_nac DATETIME);

CREATE TABLE Presupuestos (
	Id_pres INT PRIMARY KEY NOT NULL,
	Nombre_pres VARCHAR(32) NOT NULL,
	Tipo VARCHAR(32) NOT NULL,
	Precio INT NOT NULL,
	Aceptado BIT,
	Fecha_cad DATETIME,
	Id_cliente INT REFERENCES Cliente(Id);)
	
CREATE TABLE Productos (
	Id_prod INT PRIMARY KEY NOT NULL,
	Nombre_prod VARCHAR(32) NOT NULL,
	Precio_prod INT NOT NULL,
	Transporte BIT,
	Montaje BIT,
	Categoria VARCHAR(32) NOT NULL
	Id_almacen INT REFERENCES Almacen(Id_almacen);)

CREATE TABLE Almacen (
	Id_almacen INT PRIMARY KEY NOT NULL;);

CREATE TABLE Proveedor (
	Id_prov INT PRIMARY KEY NOT NULL;)

CREATE TABLE Pedido (
	Id_pedido INT PRIMARY KEY NOT NULL,
	Estaod BIT,
	Prod_solici VARCHAR(32) NOT NULL,
	Numero_prod INT NOT NULL,
	Id_prov INT REFERENCES Proveedor(Id_prov);)

CREATE TABLE Solicitudes (
	Id_solic INT PRIMARY KEY NOT NULL,
	Id_pres INT REFERENCES Presupuestos(Id_pres),
	Id_prod INT REFERENCES Producto(Id_prod);)

CREATE TABLE Repuesto (
	Id_repuesto INT PRIMARY KEY NOT NULL,
	Id_pedido INT REFERENCES Pedido(Id_pedido),
	Id_prod INT REFERENCES Producto(Id_prod);)

CREATE TABLE Venta (
	Id_venta INT PRIMARY KEY NOT NULL,
	Fecha_venta DATETIME,
	Precio INT NOT NULL
	Transporte BIT;)

CREATE TABLE Transportista (
	Nombre_transp VARCHAR(32) PRIMARY KEY NOT NULL,
	Numero_tel INT NOT NULL,
	horario_trab DATETIME;)
