package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.Department;

public interface DepartmentService {

    Department saveDepartment(Department department);

    Department updateDepartment(String deptId, Department department) ;

    Department findByDeptId(String deptId);

    void deleteByDeptId(String deptId);
}
