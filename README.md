# Spring Boot Employee CRUD Project

This project demonstrates a simple CRUD (Create, Read, Update, Delete) application for managing employee records using Spring Boot, Java 8, Spring Data JPA, and H2 in-memory database.

## Project Structure

```
spring-boot-employee-crud/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── springbootemployeecrud/
│   │   │               ├── controller/
│   │   │               │   └── EmployeeController.java
│   │   │               ├── entity/
│   │   │               │   └── Employee.java
│   │   │               ├── repository/
│   │   │               │   └── EmployeeRepository.java
│   │   │               ├── service/
│   │   │               │   └── EmployeeService.java
│   │   │               └── SpringBootEmployeeCrudApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
└── pom.xml
```

## Prerequisites

- Java 8 or later
- Maven 3.2+

## Setup

1. Clone the repository or create a new directory and add the files as described in the project structure.

2. Ensure that your `pom.xml` file contains the necessary dependencies:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

3. Configure the H2 database in `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

## Running the Application

1. Open a terminal and navigate to the project root directory.
2. Run the following command:

```
mvn spring-boot:run
```

The application will start and be available at `http://localhost:8080`.

## API Endpoints

- GET `/api/employees`: Retrieve all employees
- GET `/api/employees/{id}`: Retrieve a specific employee by ID
- POST `/api/employees`: Create a new employee
- PUT `/api/employees/{id}`: Update an existing employee
- DELETE `/api/employees/{id}`: Delete an employee

## Testing the API

You can use tools like Postman or curl to test the API endpoints. Here are some example curl commands:

1. Create an employee:
```
curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe","email":"john@example.com","department":"IT"}' http://localhost:8080/api/employees
```

2. Get all employees:
```
curl http://localhost:8080/api/employees
```

3. Get a specific employee (replace {id} with an actual ID):
```
curl http://localhost:8080/api/employees/{id}
```

4. Update an employee (replace {id} with an actual ID):
```
curl -X PUT -H "Content-Type: application/json" -d '{"name":"John Updated","email":"john@example.com","department":"HR"}' http://localhost:8080/api/employees/{id}
```

5. Delete an employee (replace {id} with an actual ID):
```
curl -X DELETE http://localhost:8080/api/employees/{id}
```

## H2 Console

You can access the H2 database console at `http://localhost:8080/h2-console`. Use the following details to log in:

- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`
- Password: `password`

## Future Enhancements

This project is set up with Java 8 and Spring Boot 3, allowing for future migration to Java 17. Potential enhancements include:

- Upgrading to Java 17 and utilizing new language features
- Adding validation to the Employee entity
- Implementing exception handling and creating custom exceptions
- Adding unit and integration tests
- Implementing pagination for large datasets
- Adding Swagger for API documentation

## Contributing

Feel free to fork this project and submit pull requests with any enhancements.

## License

This project is open-source and available under the MIT License.
