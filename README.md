# 📚 MyBookStore - Microservices for Selling IT Books

Welcome to **MyBookStore**, a microservices-based application designed for managing an e-commerce platform dedicated to selling IT books. This project is built using **Spring Boot** for the backend, **Angular** for the frontend, and **Docker** for containerization.

## 🛠️ Technologies Used

- **Backend:** Spring Boot, Spring Security, Spring Cloud Gateway, JPA, SQL Server
- **Frontend:** Angular, Nginx (for serving static files)
- **Database:** Microsoft SQL Server 2022
- **Containerization:** Docker, Docker Compose
- **Security:** JWT (JSON Web Tokens) for authentication

---

## 🌐 Project Architecture

The application is composed of the following microservices:

1. **User-Service:** Manages user registration and authentication, including role management (Admin/User).
2. **API-Gateway:** Central entry point for all frontend requests, routing them to the correct microservices.
3. **Frontend:** User interface developed in Angular.
4. **SQL Server:** Database for storing user and book data.

---

## 🔧 Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your system.
- [Docker Compose](https://docs.docker.com/compose/install/).
- [Node.js and npm](https://nodejs.org/) for local frontend development (optional if not using Docker for the frontend).

---

## 🔄 Cloning the Project

```bash
git clone https://github.com/your-username/mybookstore.git
cd mybookstore
```

---

## 🚀 Running the Application with Docker Compose

Make sure Docker and Docker Compose are installed. To start the entire application:

```bash
docker-compose up --build
```

This command:
- Builds the Docker images for **user-service**, **api-gateway**, and **frontend**.
- Starts **SQL Server** with a local volume for data persistence.

### 📂 Accessing the Services:
- **Frontend Angular:** [http://localhost:4200](http://localhost:4200)
- **API Gateway:** [http://localhost:8080](http://localhost:8080)
- **User Service:** [http://localhost:8081](http://localhost:8081)
- **SQL Server:** Accessible on port `1433`

---

## 🔧 Important Configurations

### 📂 Database (SQL Server)

- **Host:** `sqlserver_mbk`
- **Port:** `1433`
- **User:** `sa`
- **Password:** `a123456789A!`

The database is configured to automatically create tables on startup using `spring.jpa.hibernate.ddl-auto=update`.

### 🌐 API Gateway

The gateway routes all requests to the appropriate microservices. For example:

- **User Registration:** `POST /api/user/register`
- **User Login:** `POST /api/user/login`

### 💡 Frontend Angular

The frontend is configured to communicate with the API-Gateway via the `API_URL` environment variable:

```typescript
export const environment = {
  production: true,
  apiUrl: 'http://api-gateway:8080/api'
};
```

---

## 📃 Useful Commands

### 💡 Manual Build of Microservices

If you want to manually build the microservices:

```bash
cd user-service
./mvnw clean package -DskipTests

cd ../api-gateway
./mvnw clean package -DskipTests

cd ../frontend
npm install
npm run build --prod
```

### 🔄 Managing Containers

- **Stop containers:**
  ```bash
  docker-compose down
  ```

- **Rebuild images:**
  ```bash
  docker-compose build
  ```

- **View logs:**
  ```bash
  docker-compose logs -f
  ```

---

## 💡 Contributing to the Project

1. Fork the project.
2. Create a new branch:
   ```bash
   git checkout -b feature/new-feature
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add new feature"
   ```
4. Push the changes:
   ```bash
   git push origin feature/new-feature
   ```
5. Create a pull request.

---

## 🔒 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

## 📧 Contact

For questions or suggestions, contact me at [mbazie89@gmail.com](mailto:mbazie89@gmail.com) or open an issue on [GitHub](https://github.com/your-username/mybookstore/issues).

---

Happy coding! 🚀

