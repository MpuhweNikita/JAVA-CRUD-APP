package com.JPA_Springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.JPA_Springboot.model.Employee;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE employee RESTART IDENTITY CASCADE", nativeQuery = true)
    void truncateTable();

    // JPQL Query
    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findByNameJPQL(String name);

    // Native Query
    @Query(value = "SELECT * FROM employee WHERE city = :city", nativeQuery = true)
    List<Employee> findByCityNative(String city);
}
