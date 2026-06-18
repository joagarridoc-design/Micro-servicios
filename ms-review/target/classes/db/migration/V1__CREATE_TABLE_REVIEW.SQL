
CREATE TABLE review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comentario VARCHAR(1000),
    puntuacion INT NOT NULL,
    usuario_id INT NOT NULL,
    producto_id INT NOT NULL
);