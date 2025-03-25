# 🛠 Test Case Management Service (Spring Boot + MongoDB)  

## 📌 Project Overview  
This is a **Test Case Management Service** built using **Spring Boot** and **MongoDB**. It allows users to:  
- **Create, Read, Update, and Delete (CRUD) test cases**  
- **Filter test cases by priority and status**  
- **Use pagination for efficient data retrieval**  
- **View API documentation via Swagger UI**  
- **Perform unit and integration tests**  

---

## ⚡ Tech Stack Used  
- **Backend:** Java 17+, Spring Boot  
- **Database:** MongoDB  
- **API Documentation:** Swagger (SpringDoc OpenAPI)  
- **Testing:** JUnit 5, Mockito, MockMvc  
- **Build Tool:** Maven  
- **Version Control:** Git & GitHub  

---

## 📌 Setup Instructions  

### **1️⃣ Clone the Repository**
```sh
git clone <your-github-repo-url>
cd test-case-management
```

### **2️⃣ Configure MongoDB Connection**
If using **local MongoDB**, update application.properties:
```sh
spring.data.mongodb.uri=mongodb://localhost:27017/testcase_db
```
If using **MongoDB Atlas (cloud)**, replace the URI with your connection string.

### **3️⃣ Install Dependencies & Build the Project**
```sh
mvn clean install
```

### **4️⃣ Run the Spring Boot Application**
```sh
mvn spring-boot:run
```
**By default, the application runs on http://localhost:8080**

## 📌 API Endpoints  

| Method   | Endpoint                  | Description                                |
|----------|---------------------------|--------------------------------------------|
| `GET`    | `/api/testcases`          | Get all test cases (pagination & filtering) |
| `GET`    | `/api/testcases/{id}`     | Get a test case by ID                     |
| `POST`   | `/api/testcases`          | Create a new test case                    |
| `PUT`    | `/api/testcases/{id}`     | Update an existing test case              |
| `DELETE` | `/api/testcases/{id}`     | Delete a test case                        |

### **✅ Example API Request (Create Test Case)** 
---
```sh
curl -X POST "http://localhost:8080/api/testcases" \
     -H "Content-Type: application/json" \
     -d '{
           "title": "Login Functionality Test",
           "description": "Test login with valid and invalid credentials",
           "status": "PENDING",
           "priority": "HIGH"
         }'
```

## 📌 Swagger API Documentation  
After starting the application, open **Swagger UI**:  
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

To view the **OpenAPI JSON**, visit:  
👉 [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

---

## 📌 Running Tests  

### ✅ Run Unit & Integration Tests
```sh
mvn test
```
---

