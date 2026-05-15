package com.JPA_Springboot.controller;

import com.JPA_Springboot.model.Employee;
import com.JPA_Springboot.service.EmpServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class EmpController {
    @Autowired
    EmpServiceImplementation empServiceImpl;

    @GetMapping("/")
    public String welcome() {
        return "<h1>Welcome to Employee management system</h1>" +
               "<p>The application is running correctly.</p>" +
               "<ul>" +
               "<li>To add employees: POST to /</li>" +
               "<li>To view all employees: <a href='/findall'>/findall</a></li>" +
               "<li>To delete all: DELETE to /delete</li>" +
               "</ul>";
    }

    @PostMapping("/")
    public void add() {
        empServiceImpl.addEmployee();
    }

    @PostMapping("/add")
    public Employee addCustomEmployee(@RequestBody Employee employee) {
        return empServiceImpl.saveEmployee(employee);
    }

    @GetMapping("/findall")
    public ArrayList<Employee> getAllEmployee() {
        return empServiceImpl.findAllEmployees();
    }

    @GetMapping("/findbyid/{id}")
    public Employee getEmployeeUsingId(@PathVariable long id) {
        return empServiceImpl.findEmployeeById(id);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return empServiceImpl.updateEmployee(id, employee);
    }

    @DeleteMapping("/delete")
    public void delete() {
        empServiceImpl.deleteAllData();
    }
}
