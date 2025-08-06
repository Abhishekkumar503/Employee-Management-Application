# Employee Management System - Microservices Architecture

A comprehensive employee management system built with Spring Boot microservices architecture, featuring service discovery, API gateway, centralized configuration, and distributed tracing.

## 🏗️ Architecture Overview

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   API Gateway   │────│ Service Registry│────│  Config Server  │
│     :9191       │    │     :8761       │    │     :8888       │
└─────────┬───────┘    └─────────────────┘    └─────────────────┘
          │
          ├── Department Service (:8080)
          ├── Employee Service (:8081)
          ├── Organization Service (:8083)
          └── React Frontend (:3000)
```

## 🚀 Services

### 1. Service Registry (Eureka Server)
- **Port**: 8761
- **Purpose**: Service discovery and registration
- **Technology**: Netflix Eureka Server

### 2. API Gateway
- **Port**: 9191
- **Purpose**: Single entry point, routing, and load balancing
- **Technology**: Spring Cloud Gateway
- **Features**: Distributed tracing, monitoring

### 3. Config Server
- **Port**: 8888
- **Purpose**: Centralized configuration management
- **Technology**: Spring Cloud Config Server
- **Features**: Git-based configuration, refresh capabilities

### 4. Department Service
- **Port**: 8080 (Instance: 8082)
- **Purpose**: Department management operations
- **Database**: MySQL
- **Features**: CRUD operations, distributed tracing, circuit breaker

### 5. Employee Service
- **Port**: 8081
- **Purpose**: Employee management operations
- **Database**: MySQL
- **Features**: CRUD operations, inter-service communication, circuit breaker
- **External Calls**: Department Service, Organization Service

### 6. Organization Service
- **Port**: 8083
- **Purpose**: Organization management operations
- **Database**: MySQL
- **Features**: CRUD operations, distributed tracing

### 7. React Frontend
- **Port**: 3000
- **Purpose**: User interface for the employee management system
- **Technology**: React.js
- **Features**: Responsive UI, API integration

### 8. Zipkin Server
- **Port**: 9411 (Default)
- **Purpose**: Distributed tracing and monitoring
- **Technology**: Zipkin

## 🛠️ Technology Stack

### Core Technologies
- **Java**: 17
- **Spring Boot**: 3.5.4 (Note: This version needs correction - should be 3.2.x)
- **Spring Cloud**: 2025.0.0 (Note: This version needs correction)
- **Maven**: Build tool

### Microservices Components
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Configuration**: Spring Cloud Config
- **Inter-service Communication**: OpenFeign
- **Circuit Breaker**: Resilience4j
- **Message Bus**: Spring Cloud Bus with RabbitMQ

### Database & Persistence
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Connection Pool**: HikariCP (default)

### Monitoring & Observability
- **Distributed Tracing**: Zipkin + Brave
- **Metrics**: Micrometer
- **Health Monitoring**: Spring Boot Actuator
- **API Documentation**: SpringDoc OpenAPI 3

### Development Tools
- **Reactive Programming**: Spring WebFlux (Employee Service)
- **AOP**: Spring AOP
- **Code Reduction**: Lombok
- **Object Mapping**: ModelMapper

### Frontend Technologies
- **React.js**: 18.x
- **Axios**: HTTP client for API calls
- **React Router**: Client-side routing
- **Bootstrap/Material-UI**: UI components
- **Redux/Context API**: State management

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Node.js & npm** (for React frontend)
- **MySQL 8.0+**
- **RabbitMQ** (for configuration refresh)
- **Zipkin Server** (for distributed tracing)

## 📡 API Documentation

### Access Points
- **API Gateway**: http://localhost:9191
- **Service Registry Dashboard**: http://localhost:8761
- **Department Service Swagger**: http://localhost:8080/swagger-ui.html
- **Employee Service Swagger**: http://localhost:8081/swagger-ui.html
- **Organization Service Swagger**: http://localhost:8083/swagger-ui.html
- **React Frontend**: http://localhost:3000
- **Zipkin Dashboard**: http://localhost:9411

### Sample API Endpoints

#### Department Service
```bash
# Create Department
POST http://localhost:9191/api/departments
Content-Type: application/json
{
  "departmentName": "IT Department",
  "departmentDescription": "Information Technology Department",
  "departmentCode": "IT"
}

# Get Department
GET http://localhost:9191/api/departments/1

# Get All Departments
GET http://localhost:9191/api/departments
```

#### Employee Service
```bash
# Create Employee
POST http://localhost:9191/api/employees
Content-Type: application/json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@company.com",
  "departmentId": 1,
  "organizationId": 1
}

# Get Employee with Department and Organization
GET http://localhost:9191/api/employees/1
```

#### Organization Service
```bash
# Create Organization
POST http://localhost:9191/api/organizations
Content-Type: application/json
{
  "organizationName": "Tech Corp",
  "organizationDescription": "Technology Corporation",
  "organizationCode": "TECH"
}

# Get Organization
GET http://localhost:9191/api/organizations/1
```

## 🔍 Monitoring and Observability

### Health Checks
- **Service Registry**: http://localhost:8761/actuator/health
- **API Gateway**: http://localhost:9191/actuator/health
- **Department Service**: http://localhost:8080/actuator/health
- **Employee Service**: http://localhost:8081/actuator/health
- **Organization Service**: http://localhost:8083/actuator/health

### Distributed Tracing
- **Zipkin Dashboard**: http://localhost:9411

### Metrics
Access metrics for each service at: 
- **Service Registry**: http://localhost:8761/actuator/metrics
- **API Gateway**: http://localhost:9191/actuator/metrics
- **Department Service**: http://localhost:8080/actuator/metrics
- **Employee Service**: http://localhost:8081/actuator/metrics
- **Organization Service**: http://localhost:8083/actuator/metrics

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
For support and questions:
- Create an issue in the repository
- Email: support@yourcompany.com

---
