package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee) ;

    void deleteEmployeeById(Long id);

    Employee findByEmployeeId(Long id);
}
