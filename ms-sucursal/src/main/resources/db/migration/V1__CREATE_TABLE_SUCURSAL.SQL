-- 1. Crear la tabla principal de Sucursal
CREATE TABLE sucursal (
    idsuc INT AUTO_INCREMENT PRIMARY KEY,
    region VARCHAR(255) NOT NULL,
    comuna VARCHAR(255) NOT NULL
);

-- 2. Crear la tabla secundaria para la lista de inventoryIds (@ElementCollection)
CREATE TABLE sucursal_inventory (
    sucursal_id INT NOT NULL,
    inventory_id INT,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(idsuc)
);