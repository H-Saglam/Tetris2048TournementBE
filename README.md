# 🎮 Tetris2048 Tournament - Backend API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-010101?style=for-the-badge&logo=socket.io&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

> A robust Spring Boot backend service for the Tetris2048 Tournament platform, featuring JWT authentication, real-time WebSocket notifications, and comprehensive tournament management APIs.

## ��� Project Overview

This is the **Backend API** component of the Tetris2048 Tournament system - a competitive gaming platform that combines Tetris and 2048 game mechanics. The backend provides:

- **Secure Authentication** with JWT tokens
- **Real-time Notifications** via WebSocket/STOMP
- **Tournament Management** with scoring and leaderboards
- **RESTful API** for all game operations

### 🎯 Key Features

| Feature | Description |
|---------|-------------|
| **JWT Authentication** | Secure stateless authentication with token-based authorization |
| **Role-Based Access Control** | USER and ADMIN roles with endpoint-level security |
| **Real-time Notifications** | WebSocket (STOMP) for live tournament updates |
| **Tournament System** | Create and manage competitive tournaments |
| **Leaderboard** | Global and tournament-specific score rankings |
| **Score Tracking** | Persistent score history per user |

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                      Frontend (Vue.js Client)                       │
└─────────────────────────────┬───────────────────────────────────────┘
                              │
              ┌───────────────┼───────────────┐
              │ REST API      │ WebSocket     │
              │ (Port 8080)   │ (/ws)         │
              ▼               ▼               │
┌─────────────────────────────────────────────────────────────────────┐
│                     Spring Boot Application                          │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ ┌────────────┐ │
│  │ Controllers  │ │  Services    │ │  Security    │ │ WebSocket  │ │
│  │              │ │              │ │  (JWT)       │ │  Handler   │ │
│  └──────────────┘ └──────────────┘ └──────────────┘ └────────────┘ │
│                              │                                       │
│                    ┌─────────┴─────────┐                            │
│                    │   JPA Repository  │                            │
│                    └─────────┬─────────┘                            │
└──────────────────────────────┼──────────────────────────────────────┘
                               ▼
                    ┌──────────────────────┐
                    │   PostgreSQL / H2    │
                    │      Database        │
                    └──────────────────────┘
```

## 🛠️ Tech Stack

### Core Framework
- **Java 21** - Latest LTS version
- **Spring Boot 3.5.3** - Application framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Data persistence
- **Spring WebSocket** - Real-time communication

### Security
- **JWT (jjwt 0.11.5)** - Token-based authentication
- **BCrypt** - Password hashing
- **Stateless Sessions** - RESTful security

### Database
- **PostgreSQL** - Production database
- **H2** - Development/testing database
- **Hibernate** - ORM framework

### Build & DevOps
- **Maven** - Dependency management
- **Docker** - Containerization
- **Lombok** - Boilerplate reduction

## 📁 Project Structure

```
Tetris2048TournementBE/
├── src/main/java/com/example/tetris2048tournementbe/
│   ├── 📄 Tetris2048TournementBeApplication.java  # Main entry point
│   │
│   ├── 📂 config/                    # Configuration classes
│   │   ├── WebConfig.java            # CORS configuration
│   │   └── WebSocketConfig.java      # WebSocket/STOMP setup
│   │
│   ├── 📂 controller/                # REST API endpoints
│   │   ├── AuthController.java       # Authentication endpoints
│   │   ├── UserController.java       # User management
│   │   ├── TournamentController.java # Tournament CRUD
│   │   ├── ScoreController.java      # Score submission
│   │   ├── TournamentScoreController.java  # Tournament scores
│   │   └── NotificationController.java     # Push notifications
│   │
│   ├── 📂 security/                  # Security components
│   │   ├── SecurityConfig.java       # Security filter chain
│   │   └── JwtAuthFilter.java        # JWT authentication filter
│   │
│   ├── 📂 service/                   # Business logic
│   │   ├── AuthService.java          # Authentication logic
│   │   ├── JwtService.java           # JWT generation/validation
│   │   ├── UserService.java          # User operations
│   │   ├── TournamentService.java    # Tournament logic
│   │   ├── ScoreService.java         # Score management
│   │   ├── TournamentScoreService.java
│   │   └── NotificationService.java  # WebSocket notifications
│   │
│   ├── 📂 model/                     # JPA Entities
│   │   ├── User.java                 # User entity
│   │   ├── Tournament.java           # Tournament entity
│   │   ├── Score.java                # Score entity
│   │   ├── TournamentScore.java      # Tournament-specific scores
│   │   └── Notification.java         # Notification model
│   │
│   ├── 📂 repo/                      # JPA Repositories
│   │   ├── UserRepo.java
│   │   ├── TournamentRepo.java
│   │   ├── ScoreRepo.java
│   │   └── TournamentScoreRepo.java
│   │
│   ├── 📂 dto/                       # Data Transfer Objects
│   │   ├── AuthRequest.java
│   │   ├── TournamentRequest.java
│   │   ├── ScoreRequest.java
│   │   └── TournamentScoreRequest.java
│   │
│   ├── 📂 enums/                     # Enumerations
│   │   └── RoleEnum.java             # USER, ADMIN roles
│   │
│   ├── 📂 exception/                 # Custom exceptions
│   │   └── UserNotFoundException.java
│   │
│   └── 📂 handler/                   # Event handlers
│       └── WebSocketEventListener.java
│
├── 📄 Dockerfile-backend             # Docker configuration
├── 📄 pom.xml                        # Maven dependencies
└── 📄 .env                           # Environment variables
```

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- PostgreSQL (or H2 for development)
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
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=tetris2048
   DB_USER=your_username
   DB_PASSWORD=your_password
   JWT_SECRET=your-256-bit-secret-key
   JWT_EXPIRATION=86400000
   ```

3. **Run with Maven**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Or build and run JAR**
   ```bash
   ./mvnw clean package
   java -jar target/Tetris2048TournementBE-0.0.1-SNAPSHOT.jar
   ```

### Docker Deployment

```bash
# Build the image
docker build -f Dockerfile-backend -t tetris2048-backend .

# Run the container
docker run -p 8080:8080 \
  -e DB_HOST=your-db-host \
  -e DB_PASSWORD=your-password \
  tetris2048-backend
```

## 📡 API Reference

### Authentication Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/auth/register` | Register new user | ❌ |
| `POST` | `/auth/login` | Login & get JWT token | ❌ |
| `GET` | `/auth/validate` | Validate JWT token | ❌ |

#### Register User
```bash
POST /auth/register
Content-Type: application/json

{
  "username": "player1",
  "password": "securepassword"
}
```

#### Login
```bash
POST /auth/login
Content-Type: application/json

{
  "username": "player1",
  "password": "securepassword"
}

# Response
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "username": "player1"
}
```

### Tournament Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/tournaments/all` | Get active tournaments | ✅ USER |
| `POST` | `/tournaments/create` | Create new tournament | ✅ USER |

### Score Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/scores/create` | Submit a score | ✅ USER |
| `GET` | `/scores/user/{username}` | Get user's scores | ✅ USER |
| `GET` | `/scores/top10` | Get leaderboard | ✅ USER |

### Tournament Score Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/tournament-scores/create` | Submit tournament score | ✅ USER |
| `GET` | `/tournament-scores/tournament/{id}` | Get tournament rankings | ✅ USER |

### Notification Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/notifications/send` | Broadcast notification | ❌ |
| `POST` | `/notifications/tournament-created` | Tournament alert | ❌ |

## 🔌 WebSocket Integration

### Connection Setup

```javascript
// Connect to WebSocket
const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
  // Subscribe to notifications
  stompClient.subscribe('/topic/notifications', (message) => {
    const notification = JSON.parse(message.body);
    console.log('Received:', notification);
  });
});
```

### Notification Payload

```json
{
  "id": "uuid",
  "message": "New tournament created!",
  "type": "info",
  "timestamp": "2026-02-03T10:30:00"
}
```

## 🔐 Security Configuration

### Role-Based Access Control

| Endpoint Pattern | Required Role |
|-----------------|---------------|
| `/auth/**` | Public |
| `/ws/**` | Public |
| `/notifications/**` | Public |
| `/scores/**` | USER, ADMIN |
| `/tournaments/**` | USER, ADMIN |
| `/tournament-scores/**` | USER, ADMIN |
| `/api/users/**` | Authenticated |

### JWT Token Structure

```json
{
  "sub": "username",
  "iat": 1738567890,
  "exp": 1738654290
}
```

## 🗄️ Database Schema

### Entity Relationships

```
┌─────────────┐       ┌─────────────────┐       ┌─────────────┐
│    User     │───────│   Tournament    │       │    Score    │
├─────────────┤  1:N  ├─────────────────┤       ├─────────────┤
│ id          │       │ id              │       │ id          │
│ username    │       │ name            │       │ score       │
│ passwordHash│       │ created_by (FK) │       │ user_id (FK)│
│ role        │       │ created_at      │       │ created_at  │
└─────────────┘       └─────────────────┘       └─────────────┘
      │                       │
      │                       │ 1:N
      │                       ▼
      │               ┌──────────────────┐
      └──────────────►│ TournamentScore  │
           1:N        ├──────────────────┤
                      │ id               │
                      │ tournament_id(FK)│
                      │ user_id (FK)     │
                      │ score            │
                      │ created_at       │
                      └──────────────────┘
```

## ⚙️ Configuration

### Application Properties

| Property | Description | Default |
|----------|-------------|---------|
| `server.port` | Server port | `8080` |
| `security.jwt.secret-key` | JWT signing key | - |
| `security.jwt.expiration-time` | Token validity (ms) | `86400000` |
| `spring.datasource.url` | Database URL | - |

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report
```

## 🔗 Related Repositories

This backend is part of the Tetris2048 Tournament ecosystem:

| Repository | Description |
|------------|-------------|
| [Tetris2048TournamentFE](https://github.com/H-Saglam/Tetris2048TournamentFE) | Vue.js Frontend |
| [Tetris2048Tournament](https://github.com/H-Saglam/Tetris2048Tournament) | Python Game Engine |

## 👤 Author

**H-Saglam**

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<p align="center">
  <b>Tetris2048 Tournament Backend</b><br>
  Spring Boot | JWT Security | WebSocket | REST API
</p>
