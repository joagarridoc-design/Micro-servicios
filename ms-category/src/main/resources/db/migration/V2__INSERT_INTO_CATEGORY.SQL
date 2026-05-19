
INSERT INTO category (name) VALUES ('Electrónica');
INSERT INTO category (name) VALUES ('Ropa');
INSERT INTO category (name) VALUES ('Hogar');

-- Asociar productos a las categorías
-- categoría 1 (Electrónica)
INSERT INTO categoria_producto_ids (categoria_id, productos_id) VALUES (1, 101);
INSERT INTO categoria_producto_ids (categoria_id, productos_id) VALUES (1, 102);
INSERT INTO categoria_producto_ids (categoria_id, productos_id) VALUES (1, 103);

-- categoría 2 (Ropa)
INSERT INTO categoria_producto_ids (categoria_id, productos_id) VALUES (2, 201);
INSERT INTO categoria_producto_ids (categoria_id, productos_id) VALUES (2, 202);

-- categoría 3 (Hogar)
INSERT INTO categoria_producto_ids (categoria_id, productos_id) VALUES (3, 301);
INSERT INTO categoria_producto_ids (categoria_id, productos_id) VALUES (3, 302);
INSERT INTO categoria_producto_ids (categoria_id, productos_id) VALUES (3, 303);