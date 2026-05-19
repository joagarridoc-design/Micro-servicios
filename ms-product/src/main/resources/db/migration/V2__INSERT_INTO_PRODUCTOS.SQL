-- Insertar productos 
INSERT INTO productos (nombre) VALUES ('Celular Samsung Galaxy');
INSERT INTO productos (nombre) VALUES ('Polera Nike');
INSERT INTO productos (nombre) VALUES ('Sofá 3 cuerpos');
INSERT INTO productos (nombre) VALUES ('Notebook Lenovo');
INSERT INTO productos (nombre) VALUES ('Zapatillas Adidas');

-- Asociar categorías 

INSERT INTO producto_categorias_ids (producto_id, categorias_id) VALUES (1, 1); -- Celular -> Electrónica
INSERT INTO producto_categorias_ids (producto_id, categorias_id) VALUES (1, 2); -- Celular -> Tecnología
INSERT INTO producto_categorias_ids (producto_id, categorias_id) VALUES (2, 3); -- Polera -> Ropa
INSERT INTO producto_categorias_ids (producto_id, categorias_id) VALUES (3, 4); -- Sofá -> Hogar
INSERT INTO producto_categorias_ids (producto_id, categorias_id) VALUES (4, 1); -- Notebook -> Electrónica
INSERT INTO producto_categorias_ids (producto_id, categorias_id) VALUES (4, 2); -- Notebook -> Tecnología
INSERT INTO producto_categorias_ids (producto_id, categorias_id) VALUES (5, 3); -- Zapatillas -> Ropa
