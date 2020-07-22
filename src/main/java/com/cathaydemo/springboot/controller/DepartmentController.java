package com.cathaydemo.springboot.controller;

import com.cathaydemo.springboot.domain.Department;
import com.cathaydemo.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/department", method = RequestMethod.POST, produces = "application/json")
    @Transactional
    public ResponseEntity<Department> saveEmployee(@RequestBody Department department) {
        Department saveDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/department/{deptId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Department> updateDepartment(@PathVariable("deptId") String deptId, @RequestBody Department department) {
        Department updateDepartment = departmentService.updateDepartment(deptId, department);
        return new ResponseEntity<>(updateDepartment, HttpStatus.OK);
    }

    @RequestMapping(value = "/department/{deptId}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<String> deleteByDeptId(@PathVariable("deptId") String deptId) {
        departmentService.deleteByDeptId(deptId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
