delete from Cliente;
delete from Presupuestos; 
delete from venta;
delete from Transportista;
delete from productos;
delete from proveedor;
delete from pedido;
delete from solicitudes;
delete from almacen;

insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (78456156,'Luis','Rodriguez',to_date('1995-03-12','yyyy-mm-dd'),113372889,'luisantonio@ggmail.com');
insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (45215638,'Ana','Sanchez',to_date('2001-07-08','yyyy-mm-dd'),12823783,'Ana123@gmail.com');
insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (14578232,'Jose','Martinez',to_date('1986-01-25','yyyy-mm-dd'),23487373,'joseMar@gmail.com');
insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (65842623,'Andrea','Lopez',to_date('1998-10-11','yyyy-mm-dd'),1234985,'AndreaCosa@gmail.com');
insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (21489632,'Antonio','Soto',to_date('1965-12-05','yyyy-mm-dd'),135849030,'AntonioJose193@gmail.com');

insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values(1,261,0,to_date('2021-10-21','yyyy-mm-dd'),NULL);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (2,42,0,to_date('2021-11-04','yyyy-mm-dd'),NULL);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (3,140,0,to_date('2021-10-28','yyyy-mm-dd'),78456156);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (4,580,0,to_date('2021-11-01','yyyy-mm-dd'),45215638);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (5,1804,1,to_date('2021-11-05','yyyy-mm-dd'),14578232);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (6,84,0,to_date('2021-10-28','yyyy-mm-dd'),65842623);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (7,140,1,to_date('2021-10-25','yyyy-mm-dd'),21489632);

insert into Transportista (id_transp,Nombre,Numero_tel,hora_entrada,hora_salida) values (1,'Juan',647571418,to_date('06:00','HH:MM'),to_date('18:00','HH:MM'));
insert into Transportista (id_transp,Nombre,Numero_tel,hora_entrada,hora_salida) values (2,'Lorena',642518935,to_date('07:00','HH:MM'),to_date('20:00','HH:MM'));
insert into Transportista (id_transp,Nombre,Numero_tel,hora_entrada,hora_salida) values (3,'Luis',639293514,to_date('08:00','HH:MM'),to_date('22:00','HH:MM'));
insert into Transportista (id_transp,Nombre,Numero_tel,hora_entrada,hora_salida) values (4,'Victor',64918327,to_date('09:00','HH:MM'),to_date('23:59','HH:MM'));

insert into venta(id_venta,fecha_venta,precio,transporte,id_pres,id_transp) values (1,to_date('2021-10-10','yyyy-mm-dd'),1840,0,5,null);
insert into venta(id_venta,fecha_venta,precio,transporte,Id_pres,id_transp) values (2,to_date('2021-09-28','yyyy-mm-dd'),140,0,7,null);
insert into venta(id_venta,fecha_venta,precio,transporte,Id_pres,id_transp) values (3,to_date('2021-11-05','yyyy-mm-dd'),140,0,3,null);

insert into almacen(Id_almacen) values (1);
insert into almacen(Id_almacen) values (2);
insert into almacen(Id_almacen) values (3);

insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (123,'silla victoriana',111,'silla',to_date('2021-10-10','yyyy-mm-dd'),1);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (150,'sofá tela negra',700,'sofa',to_date('2021-10-10','yyyy-mm-dd'),2);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (95,'mesa cristal 4 patas',150,'mesa',to_date('2021-10-10','yyyy-mm-dd'),1);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (45,'libreria hoinfrom',540,'estanteria',to_date('2021-10-10','yyyy-mm-dd'),3);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (32,'puf negro',42,'silla',to_date('2021-10-10','yyyy-mm-dd'),1);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (144,'mesa de cocina lisa',150,'mesa',to_date('2021-10-10','yyyy-mm-dd'),1);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (124,'silla bizantina',180,'silla',to_date('2021-10-10','yyyy-mm-dd'),1);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (140,'sofa victoriano',1104,'sofa',to_date('2021-10-10','yyyy-mm-dd'),2);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (111,'estanteria lichetes',256,'estanteria',to_date('2021-10-10','yyyy-mm-dd'),1);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (105,'silla normal blanca',140,'silla',to_date('2021-10-10','yyyy-mm-dd'),2);
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega,id_alm ) values (29,'mesa eclesiástica',580,'mesa',to_date('2021-10-10','yyyy-mm-dd'),3);

insert into proveedor(id_prov) values (1);
insert into proveedor(id_prov) values (2);
insert into proveedor(id_prov) values (3);
insert into proveedor(id_prov) values (4);
insert into proveedor(id_prov) values (5);
insert into proveedor(id_prov) values (6);

insert into pedido (id_pedido,estado,id_prov) values (1,0,1);
insert into pedido (id_pedido,estado,id_prov) values (2,0,1);
insert into pedido (id_pedido,estado,id_prov) values (3,0,1);
insert into pedido (id_pedido,estado,id_prov) values (4,0,2);
insert into pedido (id_pedido,estado,id_prov) values (5,0,2);
insert into pedido (id_pedido,estado,id_prov) values (6,0,2);
insert into pedido (id_pedido,estado,id_prov) values (7,0,3);
insert into pedido (id_pedido,estado,id_prov) values (8,0,3);
insert into pedido (id_pedido,estado,id_prov) values (9,0,4);
insert into pedido (id_pedido,estado,id_prov) values (10,0,4);

insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (1,1,123,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (2,1,95,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (3,2,32,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (4,3,105,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (5,4,29,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (6,5,140,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (7,5,150,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (8,6,32,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (9,6,32,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,transporte,montaje) values (10,7,105,0,0);


insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (1,1,123,2);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (2,1,95,2);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (3,1,144,10);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (4,2,111,2);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (5,2,32,8);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (6,3,150,4);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (7,3,29,3);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (8,4,105,8);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (9,4,45,10);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (10,5,32,4);