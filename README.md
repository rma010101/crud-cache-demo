# Spring Boot CRUD Application

A simple CRUD (Create, Read, Update, Delete) REST API built with Spring Boot and MySQL for managing employee data.

## Features

- ✅ Create new employees
- ✅ Retrieve all employees
- ✅ Retrieve employee by ID
- ✅ Update existing employees
- ✅ Delete employees
- ✅ MySQL database integration
- ✅ RESTful API endpoints

## Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL Database**
- **Maven**
- **Postman** (for testing)

## Project Structure

```
mysecondcrud002/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/crudproj/mysecondcrud002/
│       │       ├── controller/
│       │       │   └── Employee_controller.java
│       │       ├── model/
│       │       │   └── Employee.java
│       │       ├── repository/
│       │       │   └── Employee_repo.java
│       │       └── Mysecondcrud002Application.java
│       └── resources/
│           └── application.properties
├── pom.xml
├── .gitignore
└── README.md
```

## Setup and Installation

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+
- Git

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/mysecondcrud002.git
cd mysecondcrud002
```

### 2. Setup MySQL Database
```sql
CREATE DATABASE crudexample002;
```

### 3. Configure Database Connection
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crudexample002
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| POST | `/api/employees` | Create new employee |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |

## API Usage Examples

### Create Employee
```bash
POST http://localhost:8080/api/employees
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

### Get All Employees
```bash
GET http://localhost:8080/api/employees
```

### Get Employee by ID
```bash
GET http://localhost:8080/api/employees/1
```

### Update Employee
```bash
PUT http://localhost:8080/api/employees/1
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@example.com"
}
```

### Delete Employee
```bash
DELETE http://localhost:8080/api/employees/1
```

## Database Schema

### Employee Table
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key (auto-increment) |
| firstName | VARCHAR(255) | Employee first name |
| lastName | VARCHAR(255) | Employee last name |
| email | VARCHAR(255) | Employee email address |

## Testing with Postman

1. Import the API endpoints into Postman
2. Set the base URL to `http://localhost:8080`
3. Test each CRUD operation
4. Verify data persistence in MySQL database

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

**Your Name**
- GitHub: [@YOUR_USERNAME](https://github.com/rma010101)
- Email: your.email@example.com

## Future Enhancements

- [ ] Add input validation
- [ ] Implement caching
- [ ] Add unit tests
- [ ] Add frontend interface
- [ ] Add authentication and authorization
- [ ] Deploy to cloud platform