package com.cathaydemo.springboot.controller;

import com.cathaydemo.springboot.SpringBootDemoApplication;
import com.cathaydemo.springboot.domain.Employee;
import com.cathaydemo.springboot.domain.EmployeeDetail;
import com.cathaydemo.springboot.service.EmployeeDetailService;
import com.cathaydemo.springboot.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class EmployeeDetailControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDetailService employeeDetailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void listEmployeeDetail() throws Exception {
        String name = "Test EmployeeDetail";

        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(20);
        employee.setDeptId("FE");

        int numberEmployeeDetails = 7;
        for (int i = 1; i <= numberEmployeeDetails; i++) {
            employee.setId(200L + i);
            employeeService.saveEmployee(employee);
        }

        EmployeeDetail employeeDetail = new EmployeeDetail();
        employeeDetail.setName(name);

        mockMvc.perform(get("/rest/employeeDetail/1/")
                .contentType("application/json;charset=UTF-8")
                .content(objectMapper.writeValueAsString(employeeDetail))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalElements").value(7))
                .andDo(print())
                .andReturn();
    }

}
