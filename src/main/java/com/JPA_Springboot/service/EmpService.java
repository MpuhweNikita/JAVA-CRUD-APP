package com.JPA_Springboot.service;

import com.JPA_Springboot.model.Employee;
import java.util.List;
import org.springframework.data.domain.Page;

public interface EmpService {
    List<Employee> findAllEmployees();
    Employee findEmployeeById(long id);
    void addEmployee();
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(long id, Employee employee);
    void deleteAllData();
    void deleteEmployeeById(long id);
    List<Employee> findEmployeesByName(String name);
    List<Employee> findEmployeesByCity(String city);
    Page<Employee> findAllEmployeesPaginated(int page, int size, String sortBy);
}
