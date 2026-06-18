-- Insertar pagos 
INSERT INTO payment (tipo, monto) VALUES ('Tarjeta', '15000');
INSERT INTO payment (tipo, monto) VALUES ('Transferencia', '25000');
INSERT INTO payment (tipo, monto) VALUES ('Efectivo', '10000');
INSERT INTO payment (tipo, monto) VALUES ('Tarjeta', '5000');
INSERT INTO payment (tipo, monto) VALUES ('Cheque', '30000');

-- Asociar pagos con órdenes
INSERT INTO payment_user (payment_id, order_id) VALUES (1, 1);
INSERT INTO payment_user (payment_id, order_id) VALUES (2, 2);
INSERT INTO payment_user (payment_id, order_id) VALUES (3, 3);
INSERT INTO payment_user (payment_id, order_id) VALUES (4, 4);
INSERT INTO payment_user (payment_id, order_id) VALUES (5, 5);