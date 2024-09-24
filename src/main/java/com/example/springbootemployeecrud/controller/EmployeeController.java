package com.example.springbootemployeecrud.controller;

import com.example.springbootemployeecrud.entity.Employee;
import com.example.springbootemployeecrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Autowired

    @GetMapping(value = "/data")
    public ResponseEntity<String> getData() throws IOException {
        ClassPathResource resource = new ClassPathResource("data.json");
        Path path = Paths.get(resource.getURI());
        String jsonData = new String(Files.readAllBytes(path));
        return ResponseEntity.ok(jsonData);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/redis/save")
    public String saveToRedis(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Data saved to Redis!";
    }
    @GetMapping("/redis/get")
    public String getFromRedis(@RequestParam String key) {
        String value = redisTemplate.opsForValue().get(key);
        return value != null ? value : "Key not found in Redis!";
    }
    @DeleteMapping("/redis/delete")
    public String deleteFromRedis(@RequestParam String key) {
        redisTemplate.delete(key);
        return "Data deleted from Redis!";
    }
}