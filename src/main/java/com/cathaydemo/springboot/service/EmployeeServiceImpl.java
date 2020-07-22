package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.Department;
import com.cathaydemo.springboot.domain.Employee;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component("EmployeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee employeeFromDb = employeeRepository.findById(id).orElse(null);
        employeeFromDb.setName(employee.getName());
        employeeFromDb.setAge(employee.getAge());
        employeeFromDb.setDeptId(employee.getDeptId());
        return employeeRepository.save(employeeFromDb);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findByEmployeeId(Long id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new EntityNotFoundException("No employee found with id " + id);
        }
        return employee;
    }
}
