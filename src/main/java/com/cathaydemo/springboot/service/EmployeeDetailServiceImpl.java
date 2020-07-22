package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.EmployeeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("EmployeeDetailService")
@Transactional
public class EmployeeDetailServiceImpl implements EmployeeDetailService {

    private EmployeeDetailRepository employeeDetailRepository;

    EmployeeDetailServiceImpl(EmployeeDetailRepository employeeDetailRepository) {
        this.employeeDetailRepository = employeeDetailRepository;
    }

    @Override
    public Page<EmployeeDetail> findByIdAndNameAndAgeAndDeptName(Long id, String name, Integer age, String deptName, Pageable pageable) {
        return employeeDetailRepository.findByIdAndNameAndAgeAndDeptName(id, name, age, deptName, pageable);
    }

}
