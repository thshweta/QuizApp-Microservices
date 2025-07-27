# QuizApp-Microservices

A modular Microservices-based Quiz Application built using Spring Boot, Spring Cloud, Eureka Discovery, API Gateway, and OpenFeign.

---

## 📌 Architecture Overview

This project follows the Microservices architecture and includes the following components:

### 🧩 Services

1. **Quiz Service**
   - Handles creation and retrieval of quizzes.
   - Communicates with **Question Service** to fetch relevant questions.
   - Exposes endpoints via API Gateway to the client.
   - Uses **OpenFeign** to consume Question Service APIs.
   - Port: `8090`

2. **Question Service**
   - Manages questions stored in the MySQL database.
   - Provides endpoints to get all questions or filter by category/difficulty.
   - Multiple instances running on:
     - Port: `8080`
     - Port: `8081`
   - Registered with Eureka for load-balanced service discovery.

3. **API Gateway**
   - Centralized entry point for all client requests.
   - Forwards requests to appropriate microservices based on route configuration.
   - Enables load balancing, logging, and routing features.
   - Port: `8765`

4. **Eureka Discovery Server**
   - Service Registry for all microservices.
   - Enables service-to-service communication via service names.
   - Port: `8761`

---

## ⚙️ Technologies Used

- Java 21
- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- MySQL (questiondb & quizdb)
- Maven
- IntelliJ IDEA / VS Code
- **Postman** for API testing and request/response verification

---

## 🔄 Microservices Interaction Flow

### 💬 How Services Talk to Each Other

- **Quiz Service ←→ Question Service**
  - Quiz Service fetches questions from Question Service during quiz creation.
  - Communication happens **via OpenFeign** client with **service discovery** through Eureka.

- **Client ←→ Quiz Service & Question Service**
  - All external requests go through **API Gateway**, which forwards them to the appropriate microservice.
  - Example: A client requesting a quiz would go through:
    ```
    Client → API Gateway → Quiz Service → Question Service (via Feign)
    ```

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or 21
- Maven
- MySQL
- Git

### Clone the Repository

```bash
git clone https://github.com/thshweta/QuizApp-Microservices.git
cd QuizApp-Microservices
