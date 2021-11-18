--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo añadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)


CREATE TABLE Cliente (
	Id INT PRIMARY KEY NOT NULL,
	Nombre VARCHAR(32) NOT NULL,
	Apellido VARCHAR(32) NOT NULL,
	fecha_nac DATETIME,
	dni INT UNIQUE,
	email VARCHAR(32));
	
CREATE TABLE Presupuestos (
	Id_pres INT PRIMARY KEY NOT NULL,
	Precio INT NOT NULL,
	Aceptado BIT,
	Fecha_cad DATETIME,
	Id_cliente INT REFERENCES Cliente(Id));

CREATE TABLE Almacen(
	Id_almacen int PRIMARY KEY NOT NULL,
	Nombre VARCHAR(32));
	
CREATE TABLE PersonalAlmacen(
	Id_perAlmacen int PRIMARY KEY NOT NULL,
	Nombre VARCHAR(32),
	Apellido VARCHAR(32),
	DNI VARCHAR(32) UNIQUE,
	Telefono INT,
	Usuario VARCHAR(32),
	Contraseña VARCHAR(32),
	hora_entrada TIME,
	hora_salida TIME,
	inicio_vacaciones DATETIME,
	fin_vacaciones DATETIME,
	oficio VARCHAR(32)
);


CREATE TABLE GestionaAlmacen(
	Id_Gestion int PRIMARY KEY NOT NULL,
	Id_almacen INT REFERENCES Almacen(Id_almacen),
	Id_perAlmacen INT REFERENCES PersonalAlmacen(Id_perAlmacen)
);

CREATE TABLE Productos(
	Id_prod INT PRIMARY KEY NOT NULL,
	Nombre_prod VARCHAR(32) NOT NULL,
	Precio_prod INT NOT NULL,
	Categoria VARCHAR(32) NOT NULL,	
	fecha_entrega DATETIME);

CREATE TABLE Proveedor (
	Id_prov INT PRIMARY KEY NOT NULL);

CREATE TABLE Pedido (
	Id_pedido INT PRIMARY KEY NOT NULL,
	Estado BIT,
	Id_prov INT REFERENCES Proveedor(Id_prov));

CREATE TABLE Solicitudes (
	Id_solic INT PRIMARY KEY NOT NULL,
	Id_pres INT REFERENCES Presupuestos(Id_pres),
	Id_prod INT REFERENCES Productos(Id_prod),
	Transporte BIT,
	Montaje BIT);

CREATE TABLE Transportista(
	id_transp int PRIMARY KEY NOT NULL,
	Nombre VARCHAR(32) NOT NULL,
	Apellido VARCHAR(32) NOT NULL,
	DNI VARCHAR(32) UNIQUE,
	Numero_tel INT NOT NULL,
	USUARIO VARCHAR(32),
	CONTRASEÑA VARCHAR(32),
	hora_entrada TIME,
	hora_salida TIME,
	inicio_vacaciones DATETIME,
	fin_vacaciones DATETIME,
	oficio VARCHAR(32)
);

CREATE TABLE Venta (
	Id_venta INT PRIMARY KEY NOT NULL,
	Fecha_venta DATETIME,
	Precio INT NOT NULL,
	Transporte BIT,
	Id_pres INT REFERENCES Presupuestos(Id_pres),
	Id_transp INT REFERENCES Transportista(Id_transp));
	
CREATE TABLE Vendedor(
	Id_vendedor int PRIMARY KEY NOT NULL,
	Nombre VARCHAR(32),
	Apellido VARCHAR(32),
	DNI VARCHAR(32) UNIQUE,
	Telefono INT,
	Usuario VARCHAR(32),
	Contraseña VARCHAR(32),	
	hora_entrada TIME,
	hora_salida TIME,
	inicio_vacaciones DATETIME,
	fin_vacaciones DATETIME,
	oficio VARCHAR(32)
);

CREATE TABLE Vender(
	Id_Vender int PRIMARY KEY NOT NULL,
	Id_venta INT REFERENCES Venta(Id_venta),
	Id_perAlmacen INT REFERENCES Vendedor(Id_vendedor)
);
	
CREATE TABLE Repuesto (
	Id_repuesto INT PRIMARY KEY NOT NULL,
	Id_pedido INT REFERENCES Pedido(Id_pedido),
	Id_prod INT REFERENCES Productos(Id_prod),
	cantidad_prod INT);
	
CREATE TABLE Registrado(
	Id_reg INT PRIMARY KEY NOT NULL,
	Id_prod INT REFERENCES Productos(Id_prod),
	Id_almacen INT REFERENCES Almacen(Id_almacen),
	cantidad INT);
