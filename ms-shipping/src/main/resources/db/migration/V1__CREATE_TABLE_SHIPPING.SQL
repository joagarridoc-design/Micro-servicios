-- Crear la tabla principal de Shipping/envios
CREATE TABLE shipping (
    idship INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(255) NOT NULL,
    mesinicio VARCHAR(255) NOT NULL,
    mesllegada VARCHAR(255) NOT NULL
);

--Crear la tabla secundaria para la lista de orderIds 
CREATE TABLE shipping_order (
    shipping_id INT NOT NULL,
    order_id INT,
    FOREIGN KEY (shipping_id) REFERENCES shipping(idship)
);