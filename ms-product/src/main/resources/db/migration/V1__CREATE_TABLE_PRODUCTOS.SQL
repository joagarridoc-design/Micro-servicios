-- 1. Crear la tabla principal de Productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- 2. Crear la tabla secundaria para la lista de categoriasIds (@ElementCollection)
CREATE TABLE producto_categorias_ids (
    producto_id INT NOT NULL,
    categorias_id INT NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE CASCADE
);
