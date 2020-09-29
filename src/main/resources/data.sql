insert into productos(nombre,descripcion_corta,descripcion_larga,precio_unitario)values('Muzza chica','Pizza muzzarella 8 porciones', 'Contiene: salsa de tomates, queso muzzarella, aceitunas verdes', 325);
insert into productos(nombre,descripcion_corta,descripcion_larga,precio_unitario)values('Especial chica','Pizza especial 8 porciones', 'Contiene: salsa de tomates, queso muzzarella, jamón cocido, aceitunas verdes', 360);
insert into productos(nombre,descripcion_corta,descripcion_larga,precio_unitario)values('Napo chica','Pizza napolitana 8 porciones', 'Contiene: salsa de tomates, queso muzzarella, tomate en rodajas', 350.5);
insert into pedidos_cabecera(direccion,mail,telefono,fecha_alta,horario,monto_total,aplico_descuento,estado)values('Igualdad 759','sebastianfrh@gmail.com','3424210770','2020-09-29','12:05:00',300,false,'PENDIENTE');
insert into pedidos_detalle(producto_id,cabecera_id,cantidad,precio_unitario)values(1,1,1,325);
insert into pedidos_detalle(producto_id,cabecera_id,cantidad,precio_unitario)values(2,1,1,360);
