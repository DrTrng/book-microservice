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