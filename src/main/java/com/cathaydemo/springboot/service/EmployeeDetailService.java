package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.EmployeeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeDetailService {

    Page<EmployeeDetail> findByIdAndNameAndAgeAndDeptName(Long id, String name, Integer age, String deptName, Pageable pageable);
}
