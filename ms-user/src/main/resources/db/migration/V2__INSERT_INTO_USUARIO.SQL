-- Insertar usuarios
INSERT INTO usuario (nombre) VALUES ('Carlos');
INSERT INTO usuario (nombre) VALUES ('María');
INSERT INTO usuario (nombre) VALUES ('José');
INSERT INTO usuario (nombre) VALUES ('Ana');
INSERT INTO usuario (nombre) VALUES ('Pedro');

-- Insertar perfiles asociados a los usuarios
-- Nota: usuario_id corresponde al id generado en la tabla usuario
INSERT INTO perfil (activo, usuario_id) VALUES (1, 1); -- Carlos activo
INSERT INTO perfil (activo, usuario_id) VALUES (0, 2); -- María inactiva
INSERT INTO perfil (activo, usuario_id) VALUES (1, 3); -- José activo
INSERT INTO perfil (activo, usuario_id) VALUES (1, 4); -- Ana activa
INSERT INTO perfil (activo, usuario_id) VALUES (0, 5); -- Pedro inactivo

-- Asociar productos a los usuarios
INSERT INTO usuario_producto_ids (usuario_id, producto_id) VALUES (1, 101);
INSERT INTO usuario_producto_ids (usuario_id, producto_id) VALUES (1, 102);
INSERT INTO usuario_producto_ids (usuario_id, producto_id) VALUES (2, 201);
INSERT INTO usuario_producto_ids (usuario_id, producto_id) VALUES (3, 301);
INSERT INTO usuario_producto_ids (usuario_id, producto_id) VALUES (3, 302);
INSERT INTO usuario_producto_ids (usuario_id, producto_id) VALUES (4, 401);
INSERT INTO usuario_producto_ids (usuario_id, producto_id) VALUES (5, 501);
