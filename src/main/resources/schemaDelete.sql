--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo añadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
DROP TABLE IF EXISTS Empleado;
DROP TABLE IF EXISTS Vacaciones;
DROP TABLE IF EXISTS Venta;
DROP TABLE IF EXISTS Productos;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Presupuestos;
DROP TABLE IF EXISTS Solicitudes;
DROP TABLE IF EXISTS Pedido;
DROP TABLE IF EXISTS Repuesto;
DROP TABLE IF EXISTS Proveedor;
DROP TABLE IF EXISTS Registrado;
DROP TABLE IF EXISTS Vender;
DROP TABLE IF EXISTS Almacen;
DROP TABLE IF EXISTS GestionaAlmacen;

DROP TABLE IF EXISTS PersonalAlmacen;
DROP TABLE IF EXISTS Vendedor;
DROP TABLE IF EXISTS Transportista;


