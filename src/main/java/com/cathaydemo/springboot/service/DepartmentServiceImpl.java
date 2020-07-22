package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.Department;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component("DepartmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(String deptId, Department department) {
        Department departmentFromDb = departmentRepository.findByDeptId(deptId);
        departmentFromDb.setDeptName(department.getDeptName());
        return departmentRepository.save(departmentFromDb);
    }

    @Override
    public Department findByDeptId(String deptId) {
        Department department = departmentRepository.findByDeptId(deptId);
        if (department == null) {
            throw  new EntityNotFoundException("No department found with deptId " + deptId);
        }
        return department;
    }

    @Override
    public void deleteByDeptId(String deptId) {
        departmentRepository.deleteByDeptId(deptId);
    }
}
