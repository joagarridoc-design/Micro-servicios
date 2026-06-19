-- Insertar inventarios 
INSERT INTO inventory (nombreinv, mesinv) VALUES ('Inventario Electrónica', 'Enero');
INSERT INTO inventory (nombreinv, mesinv) VALUES ('Inventario Ropa', 'Febrero');
INSERT INTO inventory (nombreinv, mesinv) VALUES ('Inventario Hogar', 'Marzo');
INSERT INTO inventory (nombreinv, mesinv) VALUES ('Inventario Deportes', 'Abril');
INSERT INTO inventory (nombreinv, mesinv) VALUES ('Inventario Oficina', 'Mayo');

-- Asociar productos a los inventarios
INSERT INTO inventory_products (inventory_id, product_id) VALUES (1, 101);
INSERT INTO inventory_products (inventory_id, product_id) VALUES (1, 102);
INSERT INTO inventory_products (inventory_id, product_id) VALUES (2, 201);
INSERT INTO inventory_products (inventory_id, product_id) VALUES (2, 202);
INSERT INTO inventory_products (inventory_id, product_id) VALUES (3, 301);
INSERT INTO inventory_products (inventory_id, product_id) VALUES (4, 401);
INSERT INTO inventory_products (inventory_id, product_id) VALUES (4, 402);
INSERT INTO inventory_products (inventory_id, product_id) VALUES (5, 501);
