-- 1. Crear la tabla principal de PAYMENT
CREATE TABLE payment (
    idpago INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL,
    monto VARCHAR(255) NOT NULL
);

-- 2. Crear la tabla secundaria para la lista de orderIds
CREATE TABLE payment_user (
    payment_id INT NOT NULL,
    order_id INT,
    FOREIGN KEY (payment_id) REFERENCES payment(idpago)
);