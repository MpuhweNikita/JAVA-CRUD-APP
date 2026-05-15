package com.JPA_Springboot.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.JPA_Springboot.model.Employee;
import com.JPA_Springboot.repository.EmployeeRepository;

@Service
public class EmpServiceImplementation implements EmpService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ArrayList<Employee> findAllEmployees() {
        return (ArrayList<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(long id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            return null;
    }

    @Override
    public void addEmployee() {
        ArrayList<Employee> emp = new ArrayList<Employee>();
        emp.add(new Employee("Olo", "Muhanga"));
        emp.add(new Employee("Daisy", "Nyagatare"));
        emp.add(new Employee("Tona", "New York"));
        emp.add(new Employee("Angel", "Beijing"));
        for (Employee employee : emp) {
            employeeRepository.save(employee);
        }
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(long id, Employee employeeDetails) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            Employee existingEmployee = opt.get();
            existingEmployee.setName(employeeDetails.getName());
            existingEmployee.setCity(employeeDetails.getCity());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    @Override
    public void deleteAllData() {
        employeeRepository.truncateTable();
    }
}
