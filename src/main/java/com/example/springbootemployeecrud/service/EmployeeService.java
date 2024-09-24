package com.example.springbootemployeecrud.service;

import com.example.springbootemployeecrud.entity.Employee;
import com.example.springbootemployeecrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.json.JSONObject;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        // JSONObject jsonObject = new JSONObject();
        // jsonObject.put("id", "1");
        // jsonObject.put("name", "john");
        // jsonObject.put("email", "john.d@gmail.com");
        // jsonObject.put("department", "Sales");
        // employee = jsontoEmployee(jsonObject);
        return employeeRepository.save(employee);
    }
    // private Employee jsontoEmployee(JSONObject  jsonObject){
    //     Long id = jsonObject.getLong("id");
    //     String name = jsonObject.getString("name");
    //     String email = jsonObject.getString("email");
    //     String department = jsonObject.getString("department");
    //     return new Employee(id,name,email,department);
    // }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee existingEmployee = employee.get();
            existingEmployee.setName(employeeDetails.getName());
            existingEmployee.setEmail(employeeDetails.getEmail());
            existingEmployee.setDepartment(employeeDetails.getDepartment());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}