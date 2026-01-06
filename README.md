# 📦 Products Service

<br>

## Overview
**Products Service** is the authoritative product catalog microservice within the system.

 It owns the **Product domain** and is responsible for managing product data while exposing a clean, stable, and read-only interface for other business services.

This service follows a **strict domain ownership model**:

- only this service can create, update, or delete products.
  
- All other services (Sales, Shopping Carts) **consume product data exclusively via API**, never through direct database access.

The design intentionally prioritizes **clarity, separation of responsibilities, and long-term maintainability** over premature optimization.


<br>

## 🧰 Technologies
- Java 17


- Spring Boot 4


- Spring Web MVC


- Spring Data JPA


- MySQL


- Spring Cloud Netflix Eureka Client


- Spring Cloud LoadBalancer


- Resilience4j (ecosystem consistency)


- Lombok


- Swagger / OpenAPI (Springdoc)


- Maven




<br>

## ✨ Key Features
- Centralized product catalog management


- Full CRUD operations for products


- Batch product creation for catalog initialization


- Product lookup by:

    *  Single product code
    
    * Multiple product codes (used by Shopping Cart Service)


- Clean DTO-based API (entities are never exposed)


- Independent database per microservice


- Discoverable via Eureka in a distributed system


- Can run standalone or behind an API Gateway


<br>

---



## 🔄 Core Process (How It Works)
1. Products are created or updated only through this service


2. Data is persisted in the products_service database


3. External services interact with products by:
      
      * Requesting product data by code
      
      * Receiving a **DTO snapshot**, not the entity


4. No external service:

      * Writes to the product database

      * Accesses the database directly

      * Modifies product state outside this service


This guarantees **data ownership, consistency, and controlled evolution** of the Product domain.


<br>

## 🧩 Domain Model

### Product (Entity)

Represents the internal persisted model and is never exposed externally.

Key attributes:

- Auto-generated product code

- Name


- Brand


- Unit price



### ProductDTO

Used for all external communication.

Design decision:

- Prevents leaking persistence concerns


- Allows internal schema evolution without breaking consumers


- Enforces a clean contract between services


This separation is **intentional and non-negotiable**.



<br>

## 🔗 Inter-service Interaction

This service is **purely a data provider**.

Consumed by:

- **Shopping Carts Service**
    
    * Product validation
    
    * Name and unit price enrichment


- **Sales Service**
   
    * Product snapshot creation during sale generation


Important:

- This service **does not consume** any other microservice

- It remains independent and reusable




<br>

## 🚫 What This Service Does NOT Do

To keep the service cohesive and focused, the following responsibilities are **explicitly out of scope**:

- ❌ Stock management


- ❌ Reservations or inventory locking


- ❌ Pricing rules, discounts, or promotions


- ❌ User ownership or authorization


- ❌ Business orchestration


Those concerns belong to other specialized services.


<br>

## 🧠 What I Learned

- Designing a true **domain-owner microservice**


- Enforcing strict API-only communication between services


- Applying DTOs to protect internal models


- Understanding when simplicity is a feature, not a limitation


- Structuring a CRUD service that fits cleanly into a distributed system


- Maintaining architectural consistency across multiple microservices


- Although technically simple, this service is foundational for ensuring **clean boundaries and scalability.**



<br>

## 🚀 Possible Improvements

- Query optimization for multi-code product retrieval


- Pagination and filtering for large catalogs


- Global exception handling strategy


- Authentication and authorization


- Unit and integration testing


- Stock management integration via a dedicated service


- These improvements were intentionally excluded to keep the service focused.


<br>

## ▶️ How to Run the Project

### ✅ Prerequisites

- Java 17


- Maven


- MySQL


<br>


### 🗄️ Required Database

The following database must exist before starting the application:
```products_service```


The service does **not** create databases automatically.


<br>

## ▶️ Run the Application

From the project root directory:
```mvn spring-boot:run```


The service will start on:
```http://localhost:8082```


<br>

## 🌐 API Access

<br>

**Standalone Mode**

```http://localhost:8082/product/...```

<br>

**With API Gateway (Recommended in full setup)**

```http://localhost:8080/products-service/product/...```



<br>

## 📘 API Documentation (Swagger)

Once the service is running, Swagger UI is available at:

```http://localhost:8082/swagger-ui.html```

All endpoints, request/response schemas, and examples are automatically generated using Springdoc OpenAPI.



<br>

## 🔑 Final Positioning

- **Products Service** is a clean, focused, and authoritative catalog service.
-  It demonstrates how even a simple CRUD, when designed correctly, becomes a **reliable building block** in a real microservices architecture.

