# 🎮 Tetris 2048 Tournament - Backend API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Authentication-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-010101?style=for-the-badge&logo=socket.io&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)

> A robust RESTful API backend for the Tetris 2048 Tournament platform, featuring JWT authentication, real-time WebSocket notifications, and comprehensive tournament management.

## 📋 Project Overview

This is the backend service for the **Tetris 2048 Tournament** platform - a full-stack web application that combines the classic games Tetris and 2048 with a competitive tournament system. The backend provides:

- **Secure Authentication** with JWT tokens
- **Real-time Notifications** via WebSocket/STOMP
- **Tournament Management** with leaderboards
- **Score Tracking** and ranking systems
- **Role-based Access Control** (USER/ADMIN)

### 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                         Vue.js Frontend                              │
│                    (Tetris2048TournamentFE)                         │
└─────────────────────────────┬───────────────────────────────────────┘
                              │ HTTP / WebSocket
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│                    Spring Boot Backend (This Repo)                   │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌────────────┐ │
│  │    Auth     │  │ Tournament  │  │   Score     │  │   User     │ │
│  │ Controller  │  │ Controller  │  │ Controller  │  │ Controller │ │
│  └──────┬──────┘  └──────┬──────┘  └──────┬──────┘  └─────┬──────┘ │
│         │                │                │                │        │
│  ┌──────┴────────────────┴────────────────┴────────────────┴──────┐ │
│  │                        Service Layer                           │ │
│  │  AuthService │ TournamentService │ ScoreService │ JwtService   │ │
│  └──────────────────────────┬─────────────────────────────────────┘ │
│                             │                                        │
│  ┌──────────────────────────┴─────────────────────────────────────┐ │
│  │                    Security Layer (JWT)                         │ │
│  │           JwtAuthFilter │ SecurityConfig                        │ │
│  └─────────────────────────────────────────────────────────────────┘ │
│                             │                                        │
│  ┌──────────────────────────┴─────────────────────────────────────┐ │
│  │                WebSocket (STOMP) Notifications                  │ │
│  │                    /topic/notifications                         │ │
│  └─────────────────────────────────────────────────────────────────┘ │
└─────────────────────────────┬───────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│                      PostgreSQL Database                             │
│    ┌──────────┐  ┌─────────────┐  ┌────────────────────┐           │
│    │  users   │  │ tournaments │  │ tournament_scores  │           │
│    └──────────┘  └─────────────┘  └────────────────────┘           │
│    ┌──────────┐                                                     │
│    │  scores  │                                                     │
│    └──────────┘                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Programming Language |
| **Spring Boot** | 3.5.3 | Application Framework |
| **Spring Security** | 6.x | Authentication & Authorization |
| **Spring Data JPA** | 3.x | Database ORM |
| **Spring WebSocket** | 6.x | Real-time Communication |
| **PostgreSQL** | 15 | Primary Database |
| **H2 Database** | - | Development/Testing |
| **JWT (jjwt)** | 0.11.5 | Token-based Authentication |
| **Lombok** | - | Boilerplate Reduction |
| **Docker** | - | Containerization |

## 📁 Project Structure

```
Tetris2048TournementBE/
├── 📂 src/main/java/com/example/tetris2048tournementbe/
│   ├── 📄 Tetris2048TournementBeApplication.java   # Main Application
│   │
│   ├── 📂 config/                    # Configuration Classes
│   │   ├── WebConfig.java           # CORS Configuration
│   │   └── WebSocketConfig.java     # WebSocket/STOMP Config
│   │
│   ├── 📂 controller/                # REST Controllers
│   │   ├── AuthController.java      # Login, Register, Validate
│   │   ├── UserController.java      # User Management
│   │   ├── ScoreController.java     # Score CRUD
│   │   ├── TournamentController.java        # Tournament CRUD
│   │   ├── TournamentScoreController.java   # Tournament Scores
│   │   └── NotificationController.java      # Push Notifications
│   │
│   ├── 📂 service/                   # Business Logic
│   │   ├── AuthService.java         # Authentication Logic
│   │   ├── UserService.java         # User Management
│   │   ├── JwtService.java          # JWT Token Operations
│   │   ├── ScoreService.java        # Score Management
│   │   ├── TournamentService.java   # Tournament Logic
│   │   ├── TournamentScoreService.java
│   │   └── NotificationService.java # WebSocket Notifications
│   │
│   ├── 📂 security/                  # Security Layer
│   │   ├── SecurityConfig.java      # Spring Security Config
│   │   └── JwtAuthFilter.java       # JWT Authentication Filter
│   │
│   ├── 📂 model/                     # Entity Classes
│   │   ├── User.java                # User Entity (implements UserDetails)
│   │   ├── Score.java               # Score Entity
│   │   ├── Tournament.java          # Tournament Entity
│   │   ├── TournamentScore.java     # Tournament Score Entity
│   │   └── Notification.java        # Notification Model
│   │
│   ├── 📂 repo/                      # JPA Repositories
│   │   ├── UserRepo.java
│   │   ├── ScoreRepo.java
│   │   ├── TournamentRepo.java
│   │   └── TournamentScoreRepo.java
│   │
│   ├── 📂 dto/                       # Data Transfer Objects
│   │   ├── AuthRequest.java
│   │   ├── ScoreRequest.java
│   │   ├── TournamentRequest.java
│   │   └── TournamentScoreRequest.java
│   │
│   ├── 📂 enums/
│   │   └── RoleEnum.java            # USER, ADMIN roles
│   │
│   ├── 📂 exception/
│   │   └── UserNotFoundException.java
│   │
│   └── 📂 handler/
│       └── WebSocketEventListener.java  # WebSocket Events
│
├── 📄 pom.xml                        # Maven Dependencies
├── 📄 Dockerfile-backend             # Docker Configuration
└── 📄 .env                           # Environment Variables
```

## 🔐 Authentication & Security

### JWT Token Flow

```
1. User Registration
   POST /auth/register
   └─→ Create user with hashed password → Return success

2. User Login
   POST /auth/login
   └─→ Validate credentials → Generate JWT → Return token + username

3. Protected Requests
   GET /scores/** (with Bearer token)
   └─→ JwtAuthFilter validates token → Extract user → Process request

4. Token Validation
   GET /auth/validate
   └─→ Validate token → Return validity status + username
```

### Role-Based Access Control

| Endpoint | Access |
|----------|--------|
| `/auth/**` | Public |
| `/ws/**` | Public (WebSocket) |
| `/notifications/**` | Public |
| `/scores/**` | USER, ADMIN |
| `/tournaments/**` | USER, ADMIN |
| `/tournament-scores/**` | USER, ADMIN |
| `/api/users/**` | Authenticated |

## 📡 API Reference

### Authentication

#### Register User
```http
POST /auth/register
Content-Type: application/json

{
  "username": "player1",
  "password": "securePassword123"
}
```

#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "player1",
  "password": "securePassword123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "player1"
}
```

#### Validate Token
```http
GET /auth/validate
Authorization: Bearer <token>

Response:
{
  "valid": true,
  "username": "player1"
}
```

### Scores

#### Submit Score
```http
POST /scores/create
Authorization: Bearer <token>
Content-Type: application/json

{
  "score": 15000
}
```

#### Get Top 10 Leaderboard
```http
GET /scores/top10
Authorization: Bearer <token>
```

#### Get User Scores
```http
GET /scores/user/{username}
Authorization: Bearer <token>
```

### Tournaments

#### Get All Active Tournaments
```http
GET /tournaments/all
Authorization: Bearer <token>
```

#### Create Tournament
```http
POST /tournaments/create
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Weekly Championship"
}
```

### Tournament Scores

#### Submit Tournament Score
```http
POST /tournament-scores/create
Authorization: Bearer <token>
Content-Type: application/json

{
  "tournamentId": 1,
  "score": 25000
}
```

#### Get Tournament Leaderboard
```http
GET /tournament-scores/tournament/{tournamentId}
Authorization: Bearer <token>
```

## 🔔 Real-time Notifications (WebSocket)

### Connection
```javascript
// Connect to WebSocket endpoint
ws://localhost:8080/ws

// Subscribe to notifications
/topic/notifications
```

### Notification Payload
```json
{
  "id": "uuid-string",
  "message": "New tournament created!",
  "type": "info",
  "timestamp": "2026-02-03T10:30:00"
}
```

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- PostgreSQL 15+ (or use H2 for development)
- Docker (optional)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/H-Saglam/Tetris2048TournementBE.git
   cd Tetris2048TournementBE
   ```

2. **Configure environment variables**
   ```bash
   # Create .env file or set environment variables
   export DB_NAME=tetris2048
   export DB_USERNAME=postgres
   export DB_PASSWORD=your_password
   export JWT_SECRET=your_jwt_secret_key
   ```

3. **Run with Maven**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API**
   - API: `http://localhost:8080`
   - H2 Console (if enabled): `http://localhost:8080/h2-console`

### Docker Deployment

1. **Build the application**
   ```bash
   ./mvnw clean package -DskipTests
   ```

2. **Build Docker image**
   ```bash
   docker build -f Dockerfile-backend -t tetris2048-backend .
   ```

3. **Run with Docker Compose** (from parent project)
   ```bash
   docker-compose up
   ```

## ⚙️ Configuration

### application.properties

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/tetris2048
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
security.jwt.secret-key=${JWT_SECRET}
security.jwt.expiration-time=86400000  # 24 hours

# Server Configuration
server.port=8080
```

## 🗄️ Database Schema

### Entity Relationships

```
┌─────────────┐       ┌─────────────────┐       ┌──────────────────┐
│    User     │       │   Tournament    │       │ TournamentScore  │
├─────────────┤       ├─────────────────┤       ├──────────────────┤
│ id (PK)     │──┐    │ id (PK)         │──┐    │ id (PK)          │
│ username    │  │    │ name            │  │    │ tournament_id(FK)│
│ passwordHash│  │    │ created_by (FK) │──┘    │ user_id (FK)     │
│ role        │  │    │ created_at      │       │ score            │
└─────────────┘  │    └─────────────────┘       │ created_at       │
                 │                               └──────────────────┘
                 │    ┌─────────────┐                    │
                 │    │   Score     │                    │
                 │    ├─────────────┤                    │
                 └───▶│ id (PK)     │◀───────────────────┘
                      │ user_id(FK) │
                      │ score       │
                      │ created_at  │
                      └─────────────┘
```

## 🔗 Related Repositories

This backend is part of the **Tetris 2048 Tournament** full-stack project:

| Repository | Description | Tech Stack |
|------------|-------------|------------|
| [Tetris2048Tournament](https://github.com/H-Saglam/Tetris2048Tournament) | Main project (Docker Compose) | Docker, Git Submodules |
| **Tetris2048TournementBE** (This Repo) | Backend API | Java, Spring Boot |
| [Tetris2048TournamentFE](https://github.com/H-Saglam/Tetris2048TournamentFE) | Frontend Application | Vue.js, TypeScript |

## 🎯 Key Features

- ✅ **Stateless JWT Authentication** - Secure token-based auth
- ✅ **Role-Based Access Control** - USER and ADMIN roles
- ✅ **Real-time WebSocket Notifications** - STOMP over WebSocket
- ✅ **Tournament System** - Create and manage tournaments
- ✅ **Leaderboard System** - Top 10 global and per-tournament rankings
- ✅ **CORS Configuration** - Frontend integration ready
- ✅ **Docker Support** - Easy deployment
- ✅ **Clean Architecture** - Controller → Service → Repository pattern

## 📝 License

This project is developed for educational purposes.

---

<p align="center">
  <b>Tetris 2048 Tournament Backend</b><br>
  Spring Boot | JWT Security | WebSocket | PostgreSQL
</p>
