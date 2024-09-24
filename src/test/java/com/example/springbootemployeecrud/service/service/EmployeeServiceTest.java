package com.example.springbootemployeecrud.service.service;

import com.example.springbootemployeecrud.entity.Employee;
import com.example.springbootemployeecrud.repository.EmployeeRepository;
import com.example.springbootemployeecrud.service.EmployeeService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() {
        // Arrange
        Employee employee1 = new Employee("John Doe", "john.doe@example.com", "IT");
        Employee employee2 = new Employee("Jane Doe", "jane.doe@example.com", "HR");
        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));

        // Act
        List<Employee> employees = employeeService.getAllEmployees();

        // Assert
        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }

    private void assertNotNull(List<Employee> employees) {
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        Employee employee = new Employee("John Doe", "john.doe@example.com", "IT");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        // Act
        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);

        // Assert
        assertTrue(foundEmployee.isPresent());
        assertEquals(employee, foundEmployee.get());
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        Employee employee = new Employee("John Doe", "john.doe@example.com", "IT");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        Employee createdEmployee = employeeService.createEmployee(employee);

        // Assert
        Assertions.assertNotNull(createdEmployee);
        assertEquals(employee, createdEmployee);
    }

    @Test
    void testUpdateEmployee() {
        // Arrange
        Employee existingEmployee = new Employee("John Doe", "john.doe@example.com", "IT");
        Employee updatedEmployeeDetails = new Employee("John Smith", "john.smith@example.com", "Finance");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(existingEmployee);

        // Act
        Employee updatedEmployee = employeeService.updateEmployee(1L, updatedEmployeeDetails);

        // Assert
        Assertions.assertNotNull(updatedEmployee);
        assertEquals("John Smith", updatedEmployee.getName());
        assertEquals("john.smith@example.com", updatedEmployee.getEmail());
        assertEquals("Finance", updatedEmployee.getDepartment());
    }

    @Test
    void testUpdateEmployeeNotFound() {
        // Arrange
        Employee updatedEmployeeDetails = new Employee(null, "John Smith", "john.smith@example.com");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Employee updatedEmployee = employeeService.updateEmployee(1L, updatedEmployeeDetails);

        // Assert
        assertNull(updatedEmployee);
    }

    @Test
    void testDeleteEmployee() {
        // Act
        employeeService.deleteEmployee(1L);

        // Assert
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}

