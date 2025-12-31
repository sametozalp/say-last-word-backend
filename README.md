# Say Last Word (Microservice)

**Say Last Word** is a web application built with a **microservices architecture** that allows users to share their last words and view other users' words. The application enables users to share their last words anonymously or as registered users, view the most recently shared word, and track the leaderboard.

## 🏗️ Architecture

This project is a **microservices architecture** example developed using **Spring Boot** and **Spring Cloud** technologies. The system consists of independent services with different responsibilities:

### Services

1. **API Gateway** (`api-gateway`)
   - Single entry point for all requests
   - JWT-based authorization
   - CORS configuration
   - Uses Spring Cloud Gateway WebFlux

2. **Auth Service** (`auth-service`)
   - User registration and login
   - JWT token generation and management
   - Refresh token mechanism
   - Automatic admin user creation
   - Redis cache support

3. **Last Word Service** (`last-word-service`)
   - Save last word (for anonymous and registered users)
   - View last word
   - Word list and leaderboard
   - Word banning feature
   - Timeout management

4. **User Profile Service** (`user-profile-service`)
   - User profile management
   - Profile creation and update
   - View profile information

5. **Config Server** (`config-server`)
   - Centralized configuration management
   - Spring Cloud Config Server
   - Manages all service configurations centrally

6. **Kong API Gateway** (`kong-setup`)
   - Additional API Gateway layer
   - Token validation operations
   - Inter-service routing

## 🛠️ Technologies

### Backend
- **Java 21**
- **Spring Boot 3.5.x**
- **Spring Cloud 2025.0.1**
- **Spring Security** - JWT-based authentication
- **Spring Data JPA** - Database operations
- **PostgreSQL** - Relational database
- **Redis** - Cache and session management
- **MapStruct** - DTO mapping
- **OpenFeign** - Inter-service communication
- **Lombok** - Boilerplate code reduction
- **SpringDoc OpenAPI** - API documentation

### API Gateway
- **Kong 3.4** - API Gateway and routing

### Build Tool
- **Maven 3.x**

## 📋 Features

### Authentication and Authorization
- ✅ User registration (Register)
- ✅ Email and password login (Login)
- ✅ JWT-based token system
- ✅ Refresh token support
- ✅ Token validation
- ✅ Role-based access control (ADMIN, USER)
- ✅ Automatic admin user creation

### Last Word Operations
- ✅ Share last word for registered users
- ✅ Share last word for anonymous users
- ✅ View the most recently shared word
- ✅ View word list
- ✅ Leaderboard
- ✅ Timeout management (automatic expiration of old words)
- ✅ Word banning - Admin feature
- ✅ Multi-language support (Locale)

### User Profile
- ✅ Profile creation
- ✅ Profile update
- ✅ View profile

### API Gateway
- ✅ Centralized routing
- ✅ Token validation
- ✅ CORS configuration
- ✅ Rate limiting (with Kong)

## 🚀 Installation

### Requirements

- Java 21
- Maven 3.x
- PostgreSQL 12+
- Redis 6+
- Docker and Docker Compose (for Kong)

### Database Setup

You need to create separate PostgreSQL databases for each service:

```sql
-- For Auth Service
CREATE DATABASE auth_db;

-- For Last Word Service
CREATE DATABASE lastword_db;

-- For User Profile Service
CREATE DATABASE userprofile_db;
```

### Redis Setup

Make sure Redis is running:

```bash
redis-server
```

or with Docker:

```bash
docker run -d -p 6379:6379 redis:latest
```

### Kong API Gateway Setup

Start Kong with Docker Compose:

```bash
cd kong-setup
docker-compose up -d
```

### Config Server Configuration

You need to configure the Config Server's configuration files. Add the necessary configurations for each service to the Config Server.

### Starting Services

Start the services in order:

1. **Config Server** (Must be started before other services)
   ```bash
   cd config-server
   mvn spring-boot:run
   ```

2. **Auth Service**
   ```bash
   cd auth-service
   mvn spring-boot:run
   ```

3. **User Profile Service**
   ```bash
   cd user-profile-service
   mvn spring-boot:run
   ```

4. **Last Word Service**
   ```bash
   cd last-word-service
   mvn spring-boot:run
   ```

5. **API Gateway**
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```

### Default Ports

- Config Server: `8888`
- Auth Service: `8081`
- User Profile Service: `8083`
- Last Word Service: `8082`
- API Gateway: `8080`
- Kong Gateway: `8000` (Proxy), `8001` (Admin)

## 📝 API Endpoints

### Auth Service (`/api/auth/v1`)

- `POST /register` - User registration
- `POST /login` - User login
- `POST /refresh-token/{refreshToken}` - Refresh token
- `POST /validate/{accessToken}` - Token validation

### Last Word Service (`/api/last-word/v1`)

- `POST /save` - Save last word for registered user
- `POST /save-anon` - Save last word for anonymous user
- `GET /get-last-word` - Get the latest word
- `GET /get-last-word-list` - Get word list
- `GET /get-leader-board` - Get leaderboard
- `POST /mark-as-banned/{wordId}` - Ban word (Admin)

### User Profile Service (`/api/user-profile/v1`)

- `POST /save` - Create profile
- `POST /update` - Update profile
- `POST /get-profile/{profileId}` - Get profile

## 🔧 Configuration

Each service defines its basic configuration in the `application.yml` file and receives additional configurations from the Config Server. Required environment variables:

- `config-server`: Config Server URL
- Database connection information
- Redis connection information
- JWT secret key
- Admin user information (email, username, password)

## 📦 Project Structure

```
say-last-word/
├── api-gateway/          # API Gateway service
├── auth-service/         # Authentication service
├── last-word-service/    # Last word operations service
├── user-profile-service/ # User profile service
├── config-server/        # Configuration server
├── kong-setup/           # Kong API Gateway setup files
└── pom.xml               # Main Maven POM file
```

## 🔐 Security

- JWT-based authentication
- Password hashing (BCrypt)
- CORS configuration
- Role-based access control
- Token validation
- Refresh token mechanism
