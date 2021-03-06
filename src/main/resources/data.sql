delete from Cliente;
delete from Presupuestos; 
delete from venta;
delete from productos;
delete from proveedor;
delete from pedido;
delete from solicitudes;
delete from almacen;
delete from empleado;
delete from vacaciones;

insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (78456156,'Luis','Rodriguez',to_date('1995-03-12','yyyy-mm-dd'),113372889,'luisantonio@ggmail.com');
insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (45215638,'Ana','Sanchez',to_date('2001-07-08','yyyy-mm-dd'),12823783,'Ana123@gmail.com');
insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (14578232,'Jose','Martinez',to_date('1986-01-25','yyyy-mm-dd'),23487373,'joseMar@gmail.com');
insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (65842623,'Andrea','Lopez',to_date('1998-10-11','yyyy-mm-dd'),1234985,'AndreaCosa@gmail.com');
insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (21489632,'Antonio','Soto',to_date('1965-12-05','yyyy-mm-dd'),135849030,'AntonioJose193@gmail.com');

insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values(1,261,0,to_date('2022-10-21','yyyy-mm-dd'),NULL);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (2,42,0,to_date('2022-12-04','yyyy-mm-dd'),NULL);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (3,140,0,to_date('2021-10-28','yyyy-mm-dd'),78456156);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (4,580,0,to_date('2022-11-01','yyyy-mm-dd'),45215638);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (5,1804,1,to_date('2021-11-05','yyyy-mm-dd'),14578232);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (6,84,0,to_date('2021-10-28','yyyy-mm-dd'),65842623);
insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (7,140,1,to_date('2021-10-25','yyyy-mm-dd'),21489632);

insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (1,'Juan','Fern??ndez','54827144G',647571418,'JuanFer','1234',to_date('06:00','HH:MM'),to_date('18:00','HH:MM'),'t');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (2,'Lorena','Romero','46281747K',642518935,'LoreRome','1234',to_date('07:00','HH:MM'),to_date('20:00','HH:MM'),'t');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (3,'Luis','Torres','56938577Z',639293514,'LuisTor','1234',to_date('08:00','HH:MM'),to_date('22:00','HH:MM'),'t');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (4,'Victoria','S??nchez','42155995M',649183272,'VicSan','202cb962ac59075b964b07152d234b70',to_date('09:00','HH:MM'),to_date('21:00','HH:MM'),'t');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (5,'Alfonso','Gutierrez','28451263B',648182252,'AlfonGuti',1234,to_date('08:00','HH:MM'),to_date('14:00','HH:MM'),'v');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (6,'Julieta','Venegas','14852217C',614178923,'JuliVene',1234,to_date('08:00','HH:MM'),to_date('14:00','HH:MM'),'v');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (7,'Bruno','Alves','35269686F',678495217,'BrunoAlves','202cb962ac59075b964b07152d234b70',to_date('15:00','HH:MM'),to_date('21:00','HH:MM'),'v');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (8,'Ruperta','Santos','35497124H',695839411,'RupeSantos',1234,to_date('15:00','HH:MM'),to_date('21:00','HH:MM'),'v');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (9,'Esteban','Costa','45426393N',645142796,'EsteCosta',1234,to_date('08:00','HH:MM'),to_date('14:00','HH:MM'),'pa');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (10,'Rosa','L??pez','42154484L',695839533,'RosaLopez',1234,to_date('08:00','HH:MM'),to_date('14:00','HH:MM'),'pa');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (11,'Pablo','Duro','33584771P',662415157,'PabloDuro',1234,to_date('15:00','HH:MM'),to_date('21:00','HH:MM'),'pa');
insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contrase??a,hora_entrada,hora_salida,oficio) values (12,'Nuria','??lvarez','59194727O',665424965,'NuriaAlva','202cb962ac59075b964b07152d234b70',to_date('15:00','HH:MM'),to_date('21:00','HH:MM'),'pa');

insert into Vacaciones (Id_vacacion,inicio_vacaciones,fin_vacaciones,Id_empleado) values (1,to_date('2021-11-15','yyyy-mm-dd'),to_date('2021-11-31','yyyy-mm-dd'),2);
insert into Vacaciones (Id_vacacion,inicio_vacaciones,fin_vacaciones,Id_empleado) values (2,to_date('2021-12-20','yyyy-mm-dd'),to_date('2022-01-04','yyyy-mm-dd'),12);

insert into venta(id_venta,fecha_venta,precio,transporte,montaje,id_pres,Id_empleado) values (1,to_date('2021-10-10','yyyy-mm-dd'),1840,0,0,5,5);
insert into venta(id_venta,fecha_venta,precio,transporte,montaje,id_pres,Id_empleado) values (2,to_date('2021-10-15','yyyy-mm-dd'),5100,0,0,5,5);
insert into venta(id_venta,fecha_venta,precio,transporte,montaje,Id_pres,Id_empleado) values (3,to_date('2021-09-28','yyyy-mm-dd'),140,0,0,7,7);
insert into venta(id_venta,fecha_venta,precio,transporte,montaje,Id_pres,Id_empleado) values (4,to_date('2021-11-05','yyyy-mm-dd'),140,0,0,3,7);

insert into almacen(Id_almacen,Nombre) values (1,'Almacen 1');
insert into almacen(Id_almacen,Nombre) values (2,'Almacen 2');
insert into almacen(Id_almacen,Nombre) values (3,'Almacen 3');

insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (123,'silla victoriana',111,'silla',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (150,'sof?? tela negra',700,'sofa',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (95,'mesa cristal 4 patas',150,'mesa',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (45,'libreria hoinfrom',540,'estanteria',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (32,'puf negro',42,'silla',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (144,'mesa de cocina lisa',150,'mesa',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (124,'silla bizantina',180,'silla',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (140,'sofa victoriano',1104,'sofa',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (111,'estanteria lichetes',256,'estanteria',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (105,'silla normal blanca',140,'silla',to_date('2021-10-10','yyyy-mm-dd'));
insert into productos (id_prod,nombre_prod,precio_prod,categoria,fecha_entrega) values (29,'mesa eclesi??stica',580,'mesa',to_date('2021-10-10','yyyy-mm-dd'));

insert into proveedor(id_prov) values (1);
insert into proveedor(id_prov) values (2);
insert into proveedor(id_prov) values (3);
insert into proveedor(id_prov) values (4);

insert into pedido (id_pedido,estado,Fecha_Pedido,id_prov) values (1,0,to_date('2021-11-10','yyyy-mm-dd'),1);
insert into pedido (id_pedido,estado,Fecha_Pedido,id_prov) values (2,0,to_date('2021-10-11','yyyy-mm-dd'),1);
insert into pedido (id_pedido,estado,Fecha_Pedido,id_prov) values (3,0,to_date('2021-12-12','yyyy-mm-dd'),1);
insert into pedido (id_pedido,estado,Fecha_Pedido,id_prov) values (4,0,to_date('2021-11-13','yyyy-mm-dd'),2);
insert into pedido (id_pedido,estado,Fecha_Pedido,id_prov) values (5,0,to_date('2021-10-14','yyyy-mm-dd'),2);
insert into pedido (id_pedido,estado,Fecha_Pedido,id_prov) values (6,0,to_date('2021-11-15','yyyy-mm-dd'),3);
insert into pedido (id_pedido,estado,Fecha_Pedido,id_prov) values (7,0,to_date('2021-112-16','yyyy-mm-dd'),4);

insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (1,1,123,1110,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (2,1,95,150,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (3,2,32,42,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (4,3,105,140,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (5,4,29,580,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (6,5,140,1104,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (7,5,150,700,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (8,6,32,42,0,0);
insert into solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (10,7,105,140,0,0);

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
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (11,5,124,2);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (12,5,140,1);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (13,6,123,4);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (14,6,124,5);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (15,6,105,7);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (16,7,150,2);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (17,7,140,3);
insert into repuesto (id_repuesto,id_pedido,id_prod,cantidad_prod) values (18,7,111,1);

insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (1,123,1,5);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (2,150,1,4);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (3,95,1,7);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (4,45,1,15);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (5,32,2,27);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (6,144,2,10);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (7,124,2,2);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (8,140,3,7);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (9,111,3,9);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (10,105,3,11);
insert into Registrado(Id_reg,Id_prod,Id_almacen,cantidad) values (11,29,1,21);