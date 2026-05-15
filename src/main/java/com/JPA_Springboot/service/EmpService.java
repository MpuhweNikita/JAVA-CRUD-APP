package com.JPA_Springboot.service;

import com.JPA_Springboot.model.Employee;
import java.util.ArrayList;

public interface EmpService {
    ArrayList<Employee> findAllEmployees();
    Employee findEmployeeById(long id);
    void addEmployee();
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(long id, Employee employee);
    void deleteAllData();
}
