DROP TABLE IF EXISTS `usuario_producto_ids`;
DROP TABLE IF EXISTS `usuario`;


CREATE TABLE `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Crear tabla  para los IDs de los productos
CREATE TABLE `usuario_producto_ids` (
  `usuario_id` INT NOT NULL,
  `producto_id` INT DEFAULT NULL,
  CONSTRAINT `fk_usuarioproducto_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;