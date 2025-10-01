# 🍎 FruitApp API

A robust and scalable REST API backend for managing fruit inventory and operations, built with Spring Boot and modern Java technologies.

## 📋 Description

FruitApp Backend is a comprehensive RESTful API designed to handle fruit-related operations including inventory management, CRUD operations, and data persistence. The application provides a clean and well-documented API interface with automatic documentation generation through Swagger/OpenAPI integration.

## ✨ Key Features

- 🔄 **Complete CRUD Operations** - Create, read, update, and delete fruit records
- 🗄️ **Database Integration** - PostgreSQL and MySQL support with JPA/Hibernate
- 📚 **API Documentation** - Auto-generated Swagger UI documentation
- 🔧 **Data Mapping** - Efficient object mapping with ModelMapper
- 🚀 **Development Tools** - Hot reload with Spring Boot DevTools
- 📊 **SQL Logging** - Detailed Hibernate SQL query logging for debugging
- 🎯 **Clean Architecture** - Well-structured codebase following Spring Boot best practices

## 🚀 Installation

### Prerequisites
- Java 8 or higher
- Maven 3.6+
- PostgreSQL database

### Setup Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/mauricio-gt23/FruitApp-Backend.git
   cd FruitApp-Backend
   ```

2. **Configure the database**
   - Create a PostgreSQL database named `fruits`
   - Update database credentials in `src/main/resources/application.yml`

3. **Install dependencies**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**
   - API Base URL: `http://localhost:8080`
   - Swagger Documentation: `http://localhost:8080/swagger-ui.html`

## 🛠️ Technologies Used

- **Framework:** Spring Boot 2.7.4
- **Language:** Java 8
- **Database:** PostgreSQL (primary), MySQL (supported)
- **ORM:** Spring Data JPA + Hibernate
- **Documentation:** SpringDoc OpenAPI 3 (Swagger UI)
- **Object Mapping:** ModelMapper
- **Build Tool:** Maven
- **Development:** Spring Boot DevTools, Lombok

## 👨‍💻 Author

**Mauricio Carmen - Full Stack Developer** 
---

⭐ Don't forget to give the project a star if you liked it!