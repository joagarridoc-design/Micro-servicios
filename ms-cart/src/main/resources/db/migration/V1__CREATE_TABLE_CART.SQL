
CREATE TABLE cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL
);


CREATE TABLE cart_producto_ids (
    cart_id INT NOT NULL,
    producto_id INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE
);
