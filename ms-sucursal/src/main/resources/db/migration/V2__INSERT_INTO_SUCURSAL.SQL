-- Insertar sucursales 
INSERT INTO sucursal (region, comuna) VALUES ('Metropolitana', 'La Florida');
INSERT INTO sucursal (region, comuna) VALUES ('Metropolitana', 'Providencia');
INSERT INTO sucursal (region, comuna) VALUES ('Valparaíso', 'Viña del Mar');
INSERT INTO sucursal (region, comuna) VALUES ('Biobío', 'Concepción');
INSERT INTO sucursal (region, comuna) VALUES ('Antofagasta', 'Calama');

-- Asociar inventarios a las sucursales

INSERT INTO sucursal_inventory (sucursal_id, inventory_id) VALUES (1, 1001);
INSERT INTO sucursal_inventory (sucursal_id, inventory_id) VALUES (1, 1002);
INSERT INTO sucursal_inventory (sucursal_id, inventory_id) VALUES (2, 2001);
INSERT INTO sucursal_inventory (sucursal_id, inventory_id) VALUES (3, 3001);
INSERT INTO sucursal_inventory (sucursal_id, inventory_id) VALUES (3, 3002);
INSERT INTO sucursal_inventory (sucursal_id, inventory_id) VALUES (4, 4001);
INSERT INTO sucursal_inventory (sucursal_id, inventory_id) VALUES (5, 5001);
