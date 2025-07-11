# Spring Boot CRUD Application with Caching

A high-performance CRUD (Create, Read, Update, Delete) REST API built with Spring Boot, MySQL, and Spring Cache for managing employee data with intelligent caching mechanisms.

## 🚀 Features

- ✅ **Full CRUD Operations** - Create, Read, Update, Delete employees
- ✅ **Spring Cache Integration** - High-performance caching for improved response times
- ✅ **MySQL Database** - Robust data persistence
- ✅ **RESTful API** - Clean and intuitive API endpoints
- ✅ **Auto Cache Management** - Intelligent cache eviction on data modifications
- ✅ **Production Ready** - Optimized for performance and scalability

## 🛠️ Technologies Used

- **Java 24**
- **Spring Boot 3.5.3**
- **Spring Data JPA** - Database abstraction layer
- **Spring Cache** - Caching abstraction
- **MySQL Database** - Primary data store
- **Maven** - Dependency management and build tool
- **Postman** - API testing

## 📁 Project Structure

```
mysecondcrud002-cached/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/crudproj/mysecondcrud002/
│       │       ├── config/
│       │       │   └── CacheConfig.java          # Cache configuration
│       │       ├── controller/
│       │       │   └── Employee_controller.java  # REST API endpoints
│       │       ├── model/
│       │       │   └── Employee.java             # Employee entity
│       │       ├── repository/
│       │       │   └── Employee_repo.java        # Data access layer
│       │       └── Mysecondcrud002Application.java # Main application
│       └── resources/
│           └── application.properties             # Database config
├── pom.xml                                        # Maven dependencies
└── README.md
```

## ⚡ Cache Implementation

This application implements **Spring Cache** for optimal performance:

## 🔧 Implementation Guide

### Step-by-Step Cache Implementation

To transform a basic CRUD application into a cached version, follow these exact changes:

---

### 1️⃣ **POM.XML Changes**

**File:** `pom.xml`

**Add this dependency** to your dependencies section:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

**Complete dependencies section should look like:**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- ✅ ADD THIS FOR CACHING -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

### 2️⃣ **Main Application Class Changes**

**File:** `src/main/java/com/crudproj/mysecondcrud002/Mysecondcrud002Application.java`

**BEFORE:**
```java
package com.crudproj.mysecondcrud002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Mysecondcrud002Application {
    public static void main(String[] args) {
        SpringApplication.run(Mysecondcrud002Application.class, args);
    }
}
```

**AFTER (Add these changes):**
```java
package com.crudproj.mysecondcrud002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;  // ✅ ADD THIS IMPORT

@SpringBootApplication
@EnableCaching  // ✅ ADD THIS ANNOTATION
public class Mysecondcrud002Application {
    public static void main(String[] args) {
        SpringApplication.run(Mysecondcrud002Application.class, args);
    }
}
```

---

### 3️⃣ **Controller Class Changes**

**File:** `src/main/java/com/crudproj/mysecondcrud002/controller/Employee_controller.java`

**Add these imports:**
```java
// ✅ ADD THESE CACHE IMPORTS
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
```

**Modify each method as follows:**

**GET All Employees (Add @Cacheable):**
```java
@GetMapping
@Cacheable("employees")  // ✅ ADD THIS ANNOTATION
public List<Employee> getAllEmployees() {
    return employeeRepo.findAll();
}
```

**GET Employee by ID (Add @Cacheable with key):**
```java
@GetMapping("/{id}")
@Cacheable(value = "employee", key = "#id")  // ✅ ADD THIS ANNOTATION
public Optional<Employee> getEmployeeById(@PathVariable Long id) {
    return employeeRepo.findById(id);
}
```

**POST Create Employee (Add @CacheEvict):**
```java
@PostMapping
@CacheEvict(value = "employees", allEntries = true)  // ✅ ADD THIS ANNOTATION
public Employee createEmployee(@RequestBody Employee employee) {
    return employeeRepo.save(employee);
}
```

**PUT Update Employee (Add @CacheEvict):**
```java
@PutMapping("/{id}")
@CacheEvict(value = {"employee", "employees"}, allEntries = true)  // ✅ ADD THIS ANNOTATION
public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
    Employee employee = employeeRepo.findById(id).orElseThrow();
    employee.setFirstName(employeeDetails.getFirstName());
    employee.setLastName(employeeDetails.getLastName());
    employee.setEmail(employeeDetails.getEmail());
    return employeeRepo.save(employee);
}
```

**DELETE Employee (Add @CacheEvict):**
```java
@DeleteMapping("/{id}")
@CacheEvict(value = {"employee", "employees"}, allEntries = true)  // ✅ ADD THIS ANNOTATION
public void deleteEmployee(@PathVariable Long id) {
    employeeRepo.deleteById(id);
}
```

---

### 4️⃣ **Cache Configuration Class (NEW FILE)**

**Create new file:** `src/main/java/com/crudproj/mysecondcrud002/config/CacheConfig.java`

```java
package com.crudproj.mysecondcrud002.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("employees", "employee");
    }
}
```

---

## 🎯 **Summary of Cache Annotations**

| Annotation | Purpose | When to Use |
|------------|---------|-------------|
| `@EnableCaching` | Enables Spring Cache | Main application class |
| `@Cacheable` | Stores result in cache | GET operations (read) |
| `@CacheEvict` | Removes entries from cache | POST/PUT/DELETE operations |

## 🔄 **Cache Flow Explained**

```
1. First GET request → Database → Cache → Response
2. Subsequent GET requests → Cache → Response (faster!)
3. POST/PUT/DELETE → Clear Cache → Database → Response
4. Next GET request → Database → Cache → Response (fresh data)
```

## 🔧 Setup and Installation

### Prerequisites
- **Java 24** or higher
- **MySQL 8.0** or higher
- **Maven 3.6+**
- Maven 3.6+
- Git

### 1. Clone the Repository
```bash
git clone https://github.com/rma010101/crud-cache-demo.git
cd crud-cache-demo
```

### 2. Setup MySQL Database
```sql
CREATE DATABASE crudexample_cached;
```

### 3. Configure Database Connection
Update `src/main/resources/application.properties`:
```properties
spring.application.name=crudexample_cached
spring.datasource.url=jdbc:mysql://localhost:3306/crudexample_cached
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

### 4. Build and Run the Application
```bash
# Using Maven wrapper
./mvnw clean install
./mvnw spring-boot:run

# Or using Maven directly
mvn clean install
mvn spring-boot:run
```

🎉 **Application will start on `http://localhost:8080`**

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/employees
```

### Endpoints Overview

| Method | Endpoint | Description | Cache Behavior |
|--------|----------|-------------|----------------|
| GET | `/api/employees` | Get all employees | ✅ Cached |
| GET | `/api/employees/{id}` | Get employee by ID | ✅ Cached |
| POST | `/api/employees` | Create new employee | 🗑️ Clears cache |
| PUT | `/api/employees/{id}` | Update employee | 🗑️ Clears cache |
| DELETE | `/api/employees/{id}` | Delete employee | 🗑️ Clears cache |

## 🧪 API Usage Examples

### 📝 Create Employee
```http
POST http://localhost:8080/api/employees
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```
**Response:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

### 📋 Get All Employees
```http
GET http://localhost:8080/api/employees
```
**Response:**
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }
]
```

### 🔍 Get Employee by ID
```http
GET http://localhost:8080/api/employees/1
```

### ✏️ Update Employee
```http
PUT http://localhost:8080/api/employees/1
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@example.com"
}
```

### 🗑️ Delete Employee
```http
DELETE http://localhost:8080/api/employees/1
```

## 🗃️ Database Schema

### Employee Table
| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique employee identifier |
| first_name | VARCHAR(255) | NOT NULL | Employee first name |
| last_name | VARCHAR(255) | NOT NULL | Employee last name |
| email | VARCHAR(255) | NOT NULL | Employee email address |

## 🧪 Testing with Postman

### Setup Steps:
1. **Import Collection**: Import the API endpoints into Postman
2. **Set Base URL**: `http://localhost:8080`
3. **Test Cache Performance**:
   - Make a GET request (first time - hits database)
   - Make the same GET request again (served from cache - faster)
   - Create/Update/Delete an employee (clears cache)
   - Make GET request again (hits database, then caches)

### Performance Testing:
- Compare response times before and after caching
- Monitor database queries with `spring.jpa.show-sql=true`

## 📈 Performance Benefits

- **Faster Response Times**: Cached GET requests respond in milliseconds
- **Reduced Database Load**: Frequent queries served from memory
- **Scalability**: Better performance under high load
- **Automatic Cache Management**: No manual cache invalidation needed

## 🚀 Production Deployment

### Docker Support (Optional)
```dockerfile
FROM openjdk:24-jdk-slim
COPY target/mysecondcrud002-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Environment Variables
```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/crudexample_cached
export SPRING_DATASOURCE_USERNAME=your-username
export SPRING_DATASOURCE_PASSWORD=your-password
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/awesome-feature`)
3. Commit your changes (`git commit -m 'Add awesome feature'`)
4. Push to the branch (`git push origin feature/awesome-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**rma010101** - [GitHub Profile](https://github.com/rma010101)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MySQL for reliable database management
- Spring Cache for powerful caching abstractions

---

**Happy Coding! 🚀**

This project is open source and available under the [MIT License](LICENSE).

## Author

**rma010101**
- GitHub: [@rma010101](https://github.com/rma010101)
- Email: your.email@example.com

## Future Enhancements

- [ ] Add input validation
- [ ] Implement caching
- [ ] Add unit tests
- [ ] Add frontend interface
- [ ] Add authentication and authorization
- [ ] Deploy to cloud platform