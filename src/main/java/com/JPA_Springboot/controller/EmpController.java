package com.JPA_Springboot.controller;

import com.JPA_Springboot.model.Employee;
import com.JPA_Springboot.service.EmpServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/employees")
public class EmpController {
    @Autowired
    EmpServiceImplementation empServiceImpl;

    @GetMapping("/welcome")
    public String welcome() {
        return "<h1>Welcome to Employee Management System</h1>" +
               "<p>The application is running correctly.</p>" +
               "<ul>" +
               "<li>To seed employees: POST to /employees/seed</li>" +
               "<li>To view all employees: GET to /employees</li>" +
               "<li>To find by ID: GET to /employees/{id}</li>" +
               "<li>To add custom: POST to /employees</li>" +
               "<li>To update: PUT to /employees/{id}</li>" +
               "<li>To delete one: DELETE to /employees/{id}</li>" +
               "<li>To delete all: DELETE to /employees</li>" +
               "<li>To search by name (JPQL): GET to /employees/search/name?name=...</li>" +
               "<li>To search by city (Native): GET to /employees/search/city?city=...</li>" +
               "<li>To view paginated & sorted: GET to /employees/page?page=0&size=5&sortBy=name</li>" +
               "</ul>";
    }

    @PostMapping("/seed")
    public void seed() {
        empServiceImpl.addEmployee();
    }

    @PostMapping
    public Employee addCustomEmployee(@RequestBody Employee employee) {
        return empServiceImpl.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return empServiceImpl.findAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return empServiceImpl.findEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return empServiceImpl.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable long id) {
        empServiceImpl.deleteEmployeeById(id);
    }

    @DeleteMapping
    public void deleteAllEmployees() {
        empServiceImpl.deleteAllData();
    }

    @GetMapping("/search/name")
    public List<Employee> getEmployeesByName(@RequestParam String name) {
        return empServiceImpl.findEmployeesByName(name);
    }

    @GetMapping("/search/city")
    public List<Employee> getEmployeesByCity(@RequestParam String city) {
        return empServiceImpl.findEmployeesByCity(city);
    }

    @GetMapping("/page")
    public Page<Employee> getEmployeesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return empServiceImpl.findAllEmployeesPaginated(page, size, sortBy);
    }
}
