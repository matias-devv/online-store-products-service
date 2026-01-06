# 📦 Products Service

<br>

## Overview
**Products Service** es el microservicio **autoritativo del catálogo de productos** dentro del sistema. Es el propietario del **dominio Product** y es responsable de gestionar los datos de productos, exponiendo una interfaz **limpia, estable y de solo lectura** para otros servicios de negocio.

Este servicio sigue un **modelo estricto de ownership de dominio**: solo este servicio puede crear, actualizar o eliminar productos. Todos los demás servicios (Sales, Shopping Carts) consumen los datos de productos **exclusivamente vía API**, nunca mediante acceso directo a la base de datos.

El diseño prioriza de forma intencional la **claridad, la separación de responsabilidades y la mantenibilidad a largo plazo**, evitando la optimización prematura.


<br>

## 🧰 Technologies
- Java 17


- Spring Boot 4


- Spring Web MVC


- Spring Data JPA


- MySQL


- Spring Cloud Netflix Eureka Client


- Spring Cloud LoadBalancer


- Resilience4j (consistencia del ecosistema)


- Lombok


- Swagger / OpenAPI (Springdoc)


- Maven




<br>

## ✨ Key Features
- Gestión centralizada del catálogo de productos


- Operaciones CRUD completas sobre productos


- Creación batch de productos para inicialización del catálogo


- Búsqueda de productos por:

    *  Código de producto individual
    
    * Múltiples códigos de producto (utilizado por ```shopping-cart-service```)


- API basada en DTOs (las entidades nunca se exponen)


- Base de datos independiente por microservicio


- Descubrible vía Eureka en un sistema distribuido


- Puede ejecutarse de forma standalone o detrás de un API Gateway


<br>

---



## 🔄 Core Process (How It Works)
1. Los productos se crean o actualizan **únicamente** a través de este servicio


2. Los datos se persisten en la base de datos ```products_service```

3. Los servicios externos interactúan con los productos mediante:
      
      * Solicitudes por código de producto
      
      * Recepción de un **snapshot en DTO**, no de la entidad


4. Ningún servicio externo:

      * Escribe en la base de datos de productos

      * Accede directamente a la base de datos

      * Modifica el estado de los productos fuera de este servicio


Esto garantiza **ownership de datos, consistencia y evolución controlada** del dominio Product.


<br>

## 🧩 Domain Model

### Product (Entity)

Representa el modelo persistido interno y **nunca se expone externamente.**

Atributos clave:

- Código de producto auto-generado

- Nombre


- Marca


- Precio unitario



### ProductDTO

Utilizado para toda la comunicación externa.

Decisión de diseño:

- Evita filtrar detalles de persistencia

- Permite evolucionar el esquema interno sin romper consumidores


- Fuerza un contrato limpio entre servicios

Esta separación es **intencional y no negociable**.



<br>

## 🔗 Inter-service Interaction

Este servicio es **puramente un proveedor de datos**.

Consumido por:

- **Shopping Carts Service**
    
    * Validación de productos
    
    * Enriquecimiento con nombre y precio unitario

- **Sales Service**
   
    * Creación de snapshots de productos durante la generación de ventas


Importante:

- Este servicio **no consume** ningún otro microservicio

- Permanece independiente y reutilizable




<br>

## 🚫 What This Service Does NOT Do

Para mantener el servicio cohesivo y enfocado, las siguientes responsabilidades están **explícitamente fuera de alcance**:

- ❌ Gestión de stock


- ❌ Reservas o bloqueo de inventario


- ❌ Reglas de precios, descuentos o promociones


- ❌ Ownership de usuarios o autorización


- ❌ Orquestación de negocio



Estas responsabilidades pertenecen a otros servicios especializados.


<br>

## 🧠 What I Learned

- Diseñar un microservicio verdadero owner de dominio


- Forzar comunicación estrictamente vía API entre servicios


- Aplicar DTOs para proteger los modelos internos


- Entender cuándo la simplicidad es una virtud, no una limitación


- Estructurar un CRUD que encaje limpiamente en un sistema distribuido


- Mantener consistencia arquitectónica entre múltiples microservicios



Aunque técnicamente simple, este servicio es **fundacional** para asegurar límites claros y escalabilidad.



<br>

## 🚀 Possible Improvements

- Optimización de consultas para búsqueda por múltiples códigos


- Paginación y filtros para catálogos grandes


- Estrategia global de manejo de excepciones


- Autenticación y autorización


- Tests unitarios e integraciones


- Integración de stock mediante un servicio dedicado

Estas mejoras se excluyeron de forma intencional para mantener el foco del servicio.


<br>

## ▶️ How to Run the Project

### ✅ Prerequisites

- Java 17


- Maven


- MySQL


<br>


### 🗄️ Required Database

La siguiente base de datos debe existir antes de iniciar la aplicación:
- ```products_service```


El servicio **no crea bases de datos automáticamente**.



<br>

## ▶️ Run the Application

<br>

Desde la raíz del proyecto:


```mvn spring-boot:run```


<br>

El servicio se iniciará en:


```http://localhost:8082```



<br>

## 🌐 API Access

<br>

**Standalone Mode**

```http://localhost:8082/product/...```

<br>

**Con API Gateway (recomendado en un setup completo)**

```http://localhost:8080/products-service/product/...```



<br>

## 📘 API Documentation (Swagger)

Una vez iniciado el servicio, Swagger UI estará disponible en:

```http://localhost:8082/swagger-ui.html```

Todos los endpoints, esquemas de request/response y ejemplos se generan automáticamente con Springdoc OpenAPI.



<br>

## 🔑 Final Positioning

- **Products Service** es un servicio de catálogo limpio, enfocado y autoritativo.
-  Demuestra cómo incluso un CRUD simple, cuando está bien diseñado, se convierte en un **bloque confiable** dentro de una arquitectura real de microservicios


