package com.cathaydemo.springboot.controller;

import com.cathaydemo.springboot.domain.EmployeeDetail;
import com.cathaydemo.springboot.service.EmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class EmployeeDetailController {

    @Autowired
    private EmployeeDetailService employeeDetailService;

    @RequestMapping(value = "/employeeDetail/{page}", method = RequestMethod.GET, produces = "application/json")
    @Transactional(readOnly = true)
    public ResponseEntity<Page<EmployeeDetail>> findEmployeeDetailByIdAndNameAndAgeAndDeptName(@PathVariable("page") int page,
                                                                                               @RequestBody EmployeeDetail employeeDetail) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.Direction.ASC, "id");
        Page<EmployeeDetail> employeeDetailPage = employeeDetailService.findByIdAndNameAndAgeAndDeptName(employeeDetail.getId(), employeeDetail.getName(), employeeDetail.getAge(), employeeDetail.getDeptName(), pageable);
        return new ResponseEntity<>(employeeDetailPage, HttpStatus.CREATED);
    }

}
