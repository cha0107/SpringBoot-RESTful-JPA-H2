package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "SELECT dept_id, dept_name FROM Department WHERE dept_id = ?1", nativeQuery = true)
    Department findByDeptId(String deptId);

    @Modifying
    @Query(value = "delete from Department where dept_id = ?1", nativeQuery = true)
    void deleteByDeptId(String deptId);
}
