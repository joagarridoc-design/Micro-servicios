-- 1. Crear la tabla principal de Inventory
CREATE TABLE inventario (
    idinv INT AUTO_INCREMENT PRIMARY KEY,
    nombreinv VARCHAR(255) NOT NULL,
    mesinv VARCHAR(255) NOT NULL
);

-- 2. Crear la tabla secundaria 
CREATE TABLE inventory_products (
    inventory_id INT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (inventory_id) REFERENCES inventory(idinv)
);