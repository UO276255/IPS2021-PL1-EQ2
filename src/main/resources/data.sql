delete from Cliente;
delete from Presupuestos; 
delete from almacen;
delete from productos;
delete from proveedor;
delete from pedido;
delete from solicitudes;
delete from repuesto;
delete from venta;
delete from transportista;


insert into Cliente (id,Nombre,Apellido,Fecha_nac) values (78456156,'Luis','Rodriguez',to_date('1995-03-12','yyyy-mm-dd'));
insert into Cliente (id,Nombre,Apellido,Fecha_nac) values (45215638,'Ana','Sanchez',to_date('2001-07-08','yyyy-mm-dd'));
insert into Cliente (id,Nombre,Apellido,Fecha_nac) values (14578232,'Jose','Martinez',to_date('1986-01-25','yyyy-mm-dd'));
insert into Cliente (id,Nombre,Apellido,Fecha_nac) values (65842623,'Andrea','Lopez',to_date('1998-10-11','yyyy-mm-dd'));
insert into Cliente (id,Nombre,Apellido,Fecha_nac) values (21489632,'Antonio','Soto',to_date('1965-12-05','yyyy-mm-dd'));

insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values(1,261,0,to_date('2021-10-21','yyyy-mm-dd'),null);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (2,42,0,to_date('2021-11-04','yyyy-mm-dd'),null);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (3,140,1,to_date('2021-10-19','yyyy-mm-dd'),78456156);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (4,580,0,to_date('2021-11-01','yyyy-mm-dd'),45215638);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (5,1804,1,to_date('2021-11-05','yyyy-mm-dd'),14578232);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (6,84,0,to_date('2021-10-28','yyyy-mm-dd'),65842623);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (7,140,1,to_date('2021-10-25','yyyy-mm-dd'),21489632);


insert into almacen (id_almacen) values (100);
insert into almacen (id_almacen) values (101);
insert into almacen (id_almacen) values (102);
insert into almacen (id_almacen) values (103);

insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (123,'silla victoriana',111,0,1,'silla',100);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (150,'sofá tela negra',700,1,1,'sofa',100);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (95,'mesa cristal 4 patas',150,1,1,'mesa',100);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (45,'libreria hoinfrom',540,1,1,'estanteria',101);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (32,'puf negro',42,0,0,'silla',102);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (144,'mesa de cocina lisa',150,0,1,'mesa',102);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (124,'silla bizantina',180,0,0,'silla',103);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (140,'sofa victoriano',1104,1,1,'sofa',103);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (111,'estanteria lichetes',256,1,1,'estanteria',102);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (105,'silla normal blanca',140,0,0,'silla',101);
insert into productos (id_prod,nombre_prod,precio_prod,transporte,montaje,categoria,id_almacen) values (29,'mesa eclesiástica',580,1,1,'mesa',100);

insert into proveedor(id_prov) values (1);
insert into proveedor(id_prov) values (2);
insert into proveedor(id_prov) values (3);
insert into proveedor(id_prov) values (4);
insert into proveedor(id_prov) values (5);
insert into proveedor(id_prov) values (6);

insert into pedido (id_pedido,estado,numero_prod,id_prov) values (1,0,4,1);
insert into pedido (id_pedido,estado,numero_prod,id_prov) values (2,1,10,1);
insert into pedido (id_pedido,estado,numero_prod,id_prov) values (3,0,2,2);
insert into pedido (id_pedido,estado,numero_prod,id_prov) values (4,1,15,3);
insert into pedido (id_pedido,estado,numero_prod,id_prov) values (5,1,8,4);
insert into pedido (id_pedido,estado,numero_prod,id_prov) values (6,0,14,5);

insert into solicitudes (id_solic,id_pres,id_prod) values (1,1,123);
insert into solicitudes (id_solic,id_pres,id_prod) values (2,1,95);
insert into solicitudes (id_solic,id_pres,id_prod) values (3,2,32);
insert into solicitudes (id_solic,id_pres,id_prod) values (4,3,105);
insert into solicitudes (id_solic,id_pres,id_prod) values (5,4,29);
insert into solicitudes (id_solic,id_pres,id_prod) values (6,5,140);
insert into solicitudes (id_solic,id_pres,id_prod) values (7,5,150);
insert into solicitudes (id_solic,id_pres,id_prod) values (8,6,32);
insert into solicitudes (id_solic,id_pres,id_prod) values (9,6,32);
insert into solicitudes (id_solic,id_pres,id_prod) values (10,7,105);


insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (1,1,123,2);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (2,1,95,2);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (3,2,144,10);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (4,3,111,2);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (5,4,32,8);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (6,4,150,4);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (7,4,29,3);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (8,5,105,8);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (9,6,45,10);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (10,6,32,4);


insert into venta(id_venta,fecha_venta,precio,transporte) values (1,to_date('2021-10-10','yyyy-mm-dd'),450,0);
insert into venta(id_venta,fecha_venta,precio,transporte) values (2,to_date('2021-09-28','yyyy-mm-dd'),1500,1);
insert into venta(id_venta,fecha_venta,precio,transporte) values (3,to_date('2021-11-05','yyyy-mm-dd'),85,0);
insert into venta(id_venta,fecha_venta,precio,transporte) values (4,to_date('2021-10-25','yyyy-mm-dd'),70,1);
insert into venta(id_venta,fecha_venta,precio,transporte) values (5,to_date('2021-09-30','yyyy-mm-dd'),200,1);


insert into transportista (id_transp,numero_tel,hora_entrada,hora_salida) values (1,458265845,to_date('14:00:00','hh:mm:ss'),to_date('20:30:00','hh:mm:ss'));
insert into transportista (id_transp,numero_tel,hora_entrada,hora_salida) values (2,658451236,to_date('09:30:00','hh:mm:ss'),to_date('19:30:00','hh:mm:ss'));
insert into transportista (id_transp,numero_tel,hora_entrada,hora_salida) values (3,987450125,to_date('10:00:00','hh:mm:ss'),to_date('18:30:00','hh:mm:ss'));
insert into transportista (id_transp,numero_tel,hora_entrada,hora_salida) values (4,647253154,to_date('22:00:00','hh:mm:ss'),to_date('04:30:00','hh:mm:ss'));
