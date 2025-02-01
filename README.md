# (gseCart.us) Customer Identity Service 🔍

[![SpringBoot](https://img.shields.io/badge/SpringBoot-3.1.0-brightgreen)](https://spring.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-24.0%2B-cyan)](https://www.docker.com/)

A Spring Boot application designed to manage and resolve customer identities across multiple purchases by linking contact details (email and phone number) under a primary contact.

## Overview

This service consolidates customer contact records based on matching emails or phone numbers. It ensures the oldest record remains primary and links subsequent secondary records. The API provides endpoints to resolve contacts and retrieve consolidated information.

## Features ✨
- Auto-link via email/phone matches
- Primary contact resolution (oldest first)
- Secondary record creation for new details
- Consolidates seperate primary records.
- BFS-based relationship traversal
- Input validation

## Installation Setup 🚀
1. **Clone the Repository**
```bash
git clone https://github.com/Faisal-hn/Gse-Assignment.git
```

2. **Build And Run Docker Container**
```bash
docker-compose up --build 
```

3. **Stop Docker Container**
```bash
docker compose down
```

## API Usage 🚀

### Endpoint: `POST /resolve-contact`
```bash
curl -X POST http://localhost:8080/resolve-contact \
  -H "Content-Type: application/json" \
  -d '{"email": "a@mail.com", "phone": 1001}'
```

#### Success Response (200 OK)
##### Structure
```bash
{
  "contactIds": [ ... ],  //  list of contact IDs (primary first)
  "emails": [ ... ],       // Unique emails (primary first, chronological order)
  "phones": [ ... ] // Unique phones (primary first, chronological order)
}
```
#####  Example
```bash
{
  "contactIds": [1, 3],
  "emails": ["a@mail.com", "ac@mail.com"],
  "phones": [1001, 1002]
}
```
#### Error Response (400 Bad Request)
#####  Message 1
```bash
{
  "error": "Email cannot be empty or null."
}
```
#####  Message 2
```bash
{
  "error": "Invalid email format."
}
```
#####  Message 3
```bash
{
  "error": "Phone cannot be null."
}
```
#####  Message 4
```bash
{
  "error": "Phone number must be numeric."
}
```

## Tech Stack 🛠️

| Component               | Version | Purpose                              | Badge |
|-------------------------|---------|--------------------------------------|-------|
| **Java**                | 17      | Core application logic               | ![Java](https://img.shields.io/badge/Java-17-007396) |
| **Spring Boot**         | 3.1.0   | REST API framework & dependency mgmt | ![SpringBoot](https://img.shields.io/badge/Spring%20Boot-3.1.0-6DB33F) |
| **MySQL**               | 8.0     | Relational database storage          | ![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1) |
| **Hibernate ORM**       | 6.2     | Object-relational mapping            | ![Hibernate](https://img.shields.io/badge/Hibernate-6.2-59666C) |
| **Docker**              | 24.0+   | Containerization & deployment        | ![Docker](https://img.shields.io/badge/Docker-24.0%2B-2496ED) |

**Key Tools**:
- Maven 3.9+ (Build automation)
- Docker Compose 2.20+ (Multi-container orchestration)
- MySQL Workbench (Database management)

[![Docker Image](https://img.shields.io/badge/Available%20on-Docker%20Hub-2496ED)](https://hub.docker.com/r/faisalhn/gse-cart-contact-app)

## Pre-built Docker Image 🐳

[![Docker Version](https://img.shields.io/docker/v/faisalhn/gse-cart-contact-app/latest?color=blue&label=Docker%20Image)](https://hub.docker.com/r/faisalhn/gse-cart-contact-app)
[![Docker Pulls](https://img.shields.io/docker/pulls/faisalhn/gse-cart-contact-app)](https://hub.docker.com/r/faisalhn/gse-cart-contact-app)

