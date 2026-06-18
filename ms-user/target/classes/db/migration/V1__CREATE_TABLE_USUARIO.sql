DROP TABLE IF EXISTS `usuario_producto_ids`;
DROP TABLE IF EXISTS `perfil`;
DROP TABLE IF EXISTS `usuario`;

-- 1. Crear tabla usuario
CREATE TABLE `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Crear tabla perfil (Con su llave foránea hacia usuario)
CREATE TABLE `perfil` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activo` TINYINT(1) NOT NULL, -- TINYINT(1) equivale a Boolean en MySQL
  `usuario_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_perfil_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Crear tabla intermedia para los IDs de los productos
CREATE TABLE `usuario_producto_ids` (
  `usuario_id` INT NOT NULL,
  `producto_id` INT DEFAULT NULL,
  CONSTRAINT `fk_usuarioproducto_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;