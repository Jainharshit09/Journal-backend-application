# Journal-backend-application

A backend application designed for journaling, user management, and sentiment analysis, built using Spring Boot and MongoDB.

---

## Features

- **User Management**: 
  - Register users with encrypted passwords and roles (User, Admin).
  - Find, update, and delete users by username or ID.
  - Custom user details for authentication.

- **Journal Entry Management**:
  - Create, update, delete, and fetch journal entries.
  - Link journal entries to user accounts.
  - Retrieve all entries or a specific entry by ID.

- **Sentiment Analysis**:
  - Integrated sentiment analysis for journal entries.
  - Scheduler to process and email weekly sentiment summaries to users.

- **Authentication and Security**:
  - Spring Security configuration with role-based access.
  - Passwords stored securely with BCrypt.
  - Endpoints for user, journal, and admin actions with access control.

- **Redis Caching**:
  - Redis integration for caching application data.
  - Methods to set and get cached values with TTL.

- **Configuration and Caching**:
  - App-wide configuration stored in MongoDB and loaded at startup.
  - Application cache for quick config access.

- **Scheduling**:
  - Automated tasks using Spring's scheduler (e.g., sending emails, clearing cache).

- **Testing**:
  - Unit tests for repository and service logic.

---

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data MongoDB
- Spring Security
- Redis
- Lombok
- JUnit 5

---

## Project Structure

- `service/` - Business logic for users, journal entries, and caching.
- `repository/` - MongoDB repositories and custom repository implementations.
- `entity/` - Data models for Users, Journal Entries, and Config.
- `config/` - Security and application configuration.
- `cache/` - Application-wide configuration caching.
- `scheduler/` - Scheduled background jobs.
- `test/` - Unit tests.

---

## Main Classes & Responsibilities

- **UserService**: Handles user CRUD, password encoding, and admin roles.
- **JournalEntryService**: Manages journal entries and links to users.
- **CustomUserDetails**: Implements Spring Security's user details service.
- **RedisService**: Caches data in Redis for fast access.
- **AppCache**: Loads and caches app-wide config from MongoDB.
- **SpringSecurity**: Configures role-based security and endpoint permissions.
- **UserScheduler**: Periodic jobs for emailing and cache management.

---


## How to Run

1. Make sure you have Java and MongoDB installed and running.
2. Clone the repository:
   ```bash
   git clone https://github.com/Jainharshit09/Journal-backend-application.git
   ```
3. Set up any required environment variables, especially for MongoDB and Redis.
4. Build and run:
   ```bash
   ./mvnw spring-boot:run
   ```
5. The app will start on the default Spring Boot port (8080).

---

## Contributions

Feel free to submit issues and pull requests!

---


