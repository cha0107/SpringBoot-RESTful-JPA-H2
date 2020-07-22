package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query(value = "SELECT id, name, age, dept_id FROM Employee WHERE id = ?1", nativeQuery = true)
    Employee findByEmployeeId(Long id);
}
