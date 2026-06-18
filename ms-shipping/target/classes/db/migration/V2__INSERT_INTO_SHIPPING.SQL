
INSERT INTO shipping (estado, mesinicio, mesllegada) 
VALUES ('Pendiente', 'Enero', 'Febrero');

INSERT INTO shipping (estado, mesinicio, mesllegada) 
VALUES ('En tránsito', 'Marzo', 'Abril');

INSERT INTO shipping (estado, mesinicio, mesllegada) 
VALUES ('Completado', 'Mayo', 'Junio');

INSERT INTO shipping (estado, mesinicio, mesllegada) 
VALUES ('Cancelado', 'Julio', 'Agosto');

INSERT INTO shipping (estado, mesinicio, mesllegada) 
VALUES ('Pendiente', 'Septiembre', 'Octubre');


INSERT INTO shipping_order (shipping_id, order_id) VALUES (1, 1);
INSERT INTO shipping_order (shipping_id, order_id) VALUES (2, 2);
INSERT INTO shipping_order (shipping_id, order_id) VALUES (3, 3);
INSERT INTO shipping_order (shipping_id, order_id) VALUES (4, 4);
INSERT INTO shipping_order (shipping_id, order_id) VALUES (5, 5);
