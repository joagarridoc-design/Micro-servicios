-- 1. Crear la tabla principal de órdenes
CREATE TABLE orders (
    idorden INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(255) NOT NULL,
    total VARCHAR(255) NOT NULL
);

-- 2. Crear la tabla secundaria para la lista de UserIds (ElementCollection)
CREATE TABLE user_order (
    user_id INT NOT NULL,
    Users_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES orders(idorden) ON DELETE CASCADE
);