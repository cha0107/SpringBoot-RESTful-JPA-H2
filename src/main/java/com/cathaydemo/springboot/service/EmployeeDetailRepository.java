package com.cathaydemo.springboot.service;

import com.cathaydemo.springboot.domain.EmployeeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long>, JpaSpecificationExecutor<EmployeeDetail> {

    @Query(value = "SELECT e.id, e.name, e.age, d.dept_id, d.dept_name " +
            "FROM Employee e inner join Department d on e.dept_id = d.dept_id " +
            "WHERE (:id is null or e.id = :id)  AND (:name is null or e.name = :name) " +
            "AND (:age is null or e.age = :age) AND (:deptName is null or d.dept_name = :deptName)",
            countQuery = "SELECT COUNT(*) " +
                    "FROM Employee e inner join Department d on e.dept_id = d.dept_id " +
                    "WHERE (:id is null or e.id = :id)  AND (:name is null or e.name = :name) " +
                    "AND (:age is null or e.age = :age) AND (:deptName is null or d.dept_name = :deptName)",
            nativeQuery = true)
    Page<EmployeeDetail> findByIdAndNameAndAgeAndDeptName(@Param("id") Long id,
                                                          @Param("name") String name,
                                                          @Param("age") Integer age,
                                                          @Param("deptName") String deptName, Pageable pageable);


}
