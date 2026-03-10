# 🚀 Microservices Development Environment (Docker Compose)

This repository provides a **local development environment** for a microservices-based system using **Docker Compose**.  
It allows developers to spin up all required infrastructure services with a single command.

---

## 🧱 Architecture Overview

The environment includes the following infrastructure components:

| Service | Purpose |
|------|--------|
| PostgreSQL | Primary relational database |
| RabbitMQ | Message broker for asynchronous communication |
| Kafka | Event streaming platform |
| Elasticsearch | Search engine & log storage |
| Kibana | Visualization and log analysis dashboard |

This setup is intended **for development only**.

---

## 📦 Prerequisites

Make sure the following are installed on your machine:

- Docker **20.x+**
- Docker Compose **v2+**
- Recommended RAM: **8 GB or more**

## Service Ports
| Service             | URL                 |
|---------------------| ------------------- |
| Auth                | `http://localhost:8080` |
| Order               | `http://localhost:8081` |
| Payment             | `http://localhost:8082` |
| Product             | `http://localhost:8083` |
| User                | `http://localhost:8084` |
| Insert service name | `http://localhost:` |

## Error Code Convention
Error codes follow the pattern: `[Service Number][HTTP Status][Sequence]`

For example, the code `140901` means:
```
1: Auth Service
 
409: HTTP 409 Conflict
  
01: Error message index
```

### Service Numbers
| Service  | Number |
|----------|--------|
| Auth     | 1      |
| Order    | 2      |
| Payment  | 3      |
| Product  | 4      |
| User     | 5      |

### Examples
| Code  | Service | HTTP Status | Meaning                  |
|-------|---------|-------------|--------------------------|
| 14091 | Auth    | 409         | Duplicate email          |
| 14011 | Auth    | 401         | Invalid credentials      |
| 14012 | Auth    | 401         | Token expired            |
| 54091 | User    | 409         | Duplicate email          |
| 54041 | User    | 404         | User not found           |
| 24041 | Order   | 404         | Order not found          |

### In `messages.properties`

```properties
# User Service (5xxxx)
54091=Email already exists: {0}
54041=User not found with id: {0}
```

Verify installation:
```bash
docker -v
docker compose version

Connect DB
email: admin@admin.com
password: admin
// information db login 
Host name/address: postgres
Port: 5434
Maintenance DB: postgres
Username: dev
Password: dev

//Swagger Config : 
http://localhost:8084/swagger-ui/index.html

# Build project : 
mvn clean install -U -DskipTests