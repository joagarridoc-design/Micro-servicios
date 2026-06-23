

**Evaluación Parcial 2 — DSY1103 Desarrollo FullStack I**  
Proyecto: Sistema de E-Commerce con arquitectura de microservicios  


---

## Integrantes

 Joaquin Garrido 
 Sahit Vega 

---

## Descripción del Proyecto

Este proyecto implementa una arquitectura de microservicios para un sistema de E-Commerce completo. Cada microservicio es completamente independiente, posee su propia base de datos MySQL y se comunica con otros servicios mediante Feign Client (HTTP REST).

El dominio modela todas las operaciones de una tienda online: desde el catálogo de productos hasta el envío de pedidos, incluyendo usuarios, carrito, pagos, reseñas, inventario y sucursales.

---

## Microservicios Implementados

| 1 | `eureka` | 8761 | — | Service Discovery (registro de servicios) |
| 2 | `ms-user` | 8081 | `db_user` | Gestión de usuarios y perfiles |
| 3 | `ms-product` | 8082 | `db_productos` | Catálogo de productos |
| 4 | `ms-inventario` | 8084 | `db_inventario` | Control de inventario |
| 5 | `ms-order` | 8085 | `db_order` | Gestión de órdenes/pedidos |
| 6 | `ms-category` | 8086 | `db_category` | Categorías de productos |
| 7 | `ms-cart` | 8087 | `db_cart` | Carrito de compras |
| 8 | `ms-shipping` | 8088 | `db_shipping` | Gestión de envíos |
| 9 | `ms-sucursal` | 8089 | `db_sucursal` | Gestión de sucursales |
| 10 | `ms-review` | 8090 | `db_reviews` | Reseñas y puntuaciones |
| 11 | `ms-payment` | 8091 | `db_payment` | Procesamiento de pagos |

---

##  Rutas Principales por Microservicio

### ms-user — `http://localhost:8081`
| POST | `/api/v1/usuarios` | Crear usuario + perfil |
| GET | `/api/v1/usuarios` | Listar todos los usuarios |
| GET | `/api/v1/usuarios/{id}` | Obtener usuario con productos remotos |
| GET | `/api/v1/usuarios/nombre/{nombre}` | Buscar por nombre |
| PUT | `/api/v1/usuarios/{id}` | Actualizar usuario |
| DELETE | `/api/v1/usuarios/{id}` | Eliminar usuario |

### ms-product — `http://localhost:8082`
| POST | `/api/v1/productos` | Crear producto |
| GET | `/api/v1/productos` | Listar productos |
| GET | `/api/v1/productos/{id}` | Buscar por ID |
| PUT | `/api/v1/productos/{id}` | Actualizar producto |
| DELETE | `/api/v1/productos/{id}` | Eliminar producto |

### ms-category — `http://localhost:8086`
| POST | `/api/v1/category` | Crear categoría |
| GET | `/api/v1/category` | Listar categorías |
| PUT | `/api/v1/category/{id}` | Actualizar categoría |
| DELETE | `/api/v1/category/{id}` | Eliminar categoría |

### ms-cart — `http://localhost:8087`
| GET | `/api/v1/cart` | Listar carrito |
| GET | `/api/v1/cart/{id}` | Obtener ítem por ID |
| GET | `/api/v1/cart/usuario/{usuarioId}` | Carrito por usuario |
| PUT | `/api/v1/cart/{id}` | Actualizar carrito |
| DELETE | `/api/v1/cart/{id}` | Eliminar ítem del carrito |

### ms-order — `http://localhost:8085`
| POST | `/api/v1/ordenes` | Crear orden |
| GET | `/api/v1/ordenes` | Listar órdenes |
| GET | `/api/v1/ordenes/{id}` | Obtener orden por ID |
| GET | `/api/v1/ordenes/estado/{estado}` | Filtrar por estado |
| PUT | `/api/v1/ordenes/{id}` | Actualizar orden |
| DELETE | `/api/v1/ordenes/{id}` | Eliminar orden |

### ms-payment — `http://localhost:8091`
| POST | `/api/v1/pagos` | Registrar pago |
| GET | `/api/v1/pagos` | Listar pagos |
| GET | `/api/v1/pagos/{id}` | Obtener pago por ID |
| GET | `/api/v1/pagos/tipo/{tipo}` | Filtrar por tipo de pago |
| PUT | `/api/v1/pagos/{id}` | Actualizar pago |
| DELETE | `/api/v1/pagos/{id}` | Eliminar pago |

### ms-shipping — `http://localhost:8088`
| POST | `/api/v1/envios` | Crear envío |
| GET | `/api/v1/envios` | Listar envíos |
| GET | `/api/v1/envios/{id}` | Obtener envío por ID |
| GET | `/api/v1/envios/mesinicio/{mesinicio}` | Filtrar por mes de inicio |
| GET | `/api/v1/envios/mesllegada/{mesllegada}` | Filtrar por mes de llegada |
| PUT | `/api/v1/envios/{id}` | Actualizar envío |
| DELETE | `/api/v1/envios/{id}` | Eliminar envío |

### ms-inventario — `http://localhost:8084`
| POST | `/api/v1/inventarios` | Crear inventario |
| GET | `/api/v1/inventarios` | Listar inventarios |
| GET | `/api/v1/inventarios/{id}` | Obtener inventario por ID |
| PUT | `/api/v1/inventarios/{id}` | Actualizar inventario |
| DELETE | `/api/v1/inventarios/{id}` | Eliminar inventario |

### ms-sucursal — `http://localhost:8089`
| POST | `api/v1/sucursales` | Crear sucursal |
| GET | `api/v1/sucursales` | Listar sucursales |
| GET | `api/v1/sucursales/{id}` | Obtener sucursal por ID |
| GET | `api/v1/sucursales/region/{region}` | Filtrar por región |
| GET | `api/v1/sucursales/comuna/{comuna}` | Filtrar por comuna |
| PUT | `api/v1/sucursales/{id}` | Actualizar sucursal |
| DELETE | `api/v1/sucursales/{id}` | Eliminar sucursal |

### ms-review — `http://localhost:8090`
| POST | `/api/v1/reviews` | Crear reseña  |
| GET | `/api/v1/reviews` | Listar reseñas |
| GET | `/api/v1/reviews/producto/{productoId}` | Reseñas por producto |
| GET | `/api/v1/reviews/usuario/{usuarioId}` | Reseñas por usuario |
| PUT | `/api/v1/reviews/{id}` | Actualizar reseña |
| DELETE | `/api/v1/reviews/{id}` | Eliminar reseña |

---

## Documentación Swagger 

Cada microservicio expone su documentación Swagger en la ruta `/doc/swagger-ui.html`. Una vez levantados localmente:

| ms-user | http://localhost:8081/doc/swagger-ui.html |
| ms-product | http://localhost:8082/doc/swagger-ui.html |
| ms-inventario | http://localhost:8084/doc/swagger-ui.html |
| ms-order | http://localhost:8085/doc/swagger-ui.html |
| ms-category | http://localhost:8086/doc/swagger-ui.html |
| ms-cart | http://localhost:8087/doc/swagger-ui.html |
| ms-shipping | http://localhost:8088/doc/swagger-ui.html |
| ms-sucursal | http://localhost:8089/doc/swagger-ui.html |
| ms-review | http://localhost:8090/doc/swagger-ui.html |
| ms-payment | http://localhost:8091/doc/swagger-ui.html |
| Eureka Dashboard | http://localhost:8761 |

---

### Orden de inicio 

Respetar este orden para evitar errores de Feign al iniciar:

# 1. Levantar Eureka primero
# 2. Servicios base (sin dependencias Feign)
 ms-product     # puerto 8082
 ms-user        # puerto 8081
 ms-inventario  # puerto 8084
 ms-category    # puerto 8086

# 3. Servicios que dependen de los anteriores
cd ms-order        # puerto 8085
cd ms-cart         # puerto 8087
cd ms-payment      # puerto 8091
cd ms-shipping     # puerto 8088
cd ms-sucursal     # puerto 8089
cd ms-review       # puerto 8090


### Verificar que los servicios están registrados

Abre el Eureka Dashboard en `http://localhost:8761` y confirma que todos los servicios aparecen como `UP`.

### Configuración de base de datos

Si el Laragon tiene contraseña, edita el archivo `application.properties` de cada microservicio antes de iniciar.

---

## Comunicaciones Feign entre Servicios

| Servicio que llama | → | Servicio destino | Datos obtenidos |
|--------------------|---|-----------------|-----------------|
| ms-user | → | ms-product | Productos favoritos del usuario |
| ms-product | → | ms-category | Categoría del producto |
| ms-cart | → | ms-product | Detalle de productos en el carrito |
| ms-order | → | ms-user | Datos del usuario que hizo la orden |
| ms-payment | → | ms-user | Usuario asociado al pago |
| ms-shipping | → | ms-order | Datos de la orden a enviar |
| ms-sucursal | → | ms-inventario | Inventario de la sucursal |
| ms-category | → | ms-product | Productos de la categoría |
| ms-inventario | → | ms-product | Productos del inventario |
| ms-review | → | ms-product | Validación de existencia del producto |
