package com.JPA_Springboot.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.JPA_Springboot.model.Employee;
import com.JPA_Springboot.repository.EmployeeRepository;

@Service
public class EmpServiceImplementation implements EmpService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
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
        List<Employee> emp = new ArrayList<>();
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

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeesByName(String name) {
        return employeeRepository.findByNameJPQL(name);
    }

    @Override
    public List<Employee> findEmployeesByCity(String city) {
        return employeeRepository.findByCityNative(city);
    }

    @Override
    public Page<Employee> findAllEmployeesPaginated(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }
}
