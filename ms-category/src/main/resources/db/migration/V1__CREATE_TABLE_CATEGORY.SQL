-- Tabla principal category
CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tabla secundaria para la colección de productosIds
CREATE TABLE categoria_producto_ids (
    categoria_id INT NOT NULL,
    productos_id INT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES category(id) ON DELETE CASCADE
);