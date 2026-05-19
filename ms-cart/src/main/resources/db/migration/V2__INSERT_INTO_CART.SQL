-- Insertar carritos de prueba
INSERT INTO cart (usuario_id, producto_id, cantidad) 
VALUES (101, 1, 2);  -- Usuario 101 compra 2 unidades del producto 1

INSERT INTO cart (usuario_id, producto_id, cantidad) 
VALUES (102, 2, 1);  -- Usuario 102 compra 1 unidad del producto 2

INSERT INTO cart (usuario_id, producto_id, cantidad) 
VALUES (103, 3, 4);  -- Usuario 103 compra 4 unidades del producto 3

INSERT INTO cart (usuario_id, producto_id, cantidad) 
VALUES (104, 4, 1);  -- Usuario 104 compra 1 unidad del producto 4

INSERT INTO cart (usuario_id, producto_id, cantidad) 
VALUES (105, 5, 3);  -- Usuario 105 compra 3 unidades del producto 5

-- Asociar productos a los carritos
INSERT INTO cart_producto_ids (cart_id, producto_id) VALUES (1, 2);
INSERT INTO cart_producto_ids (cart_id, producto_id) VALUES (1, 3);

INSERT INTO cart_producto_ids (cart_id, producto_id) VALUES (2, 4);

INSERT INTO cart_producto_ids (cart_id, producto_id) VALUES (3, 1);
INSERT INTO cart_producto_ids (cart_id, producto_id) VALUES (3, 5);

INSERT INTO cart_producto_ids (cart_id, producto_id) VALUES (4, 2);

INSERT INTO cart_producto_ids (cart_id, producto_id) VALUES (5, 3);
INSERT INTO cart_producto_ids (cart_id, producto_id) VALUES (5, 4);
