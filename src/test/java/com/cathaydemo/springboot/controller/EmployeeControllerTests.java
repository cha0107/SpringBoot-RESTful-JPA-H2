package com.cathaydemo.springboot.controller;

import com.cathaydemo.springboot.SpringBootDemoApplication;
import com.cathaydemo.springboot.domain.Employee;
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
public class EmployeeControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(100L);
        employee.setName("Test Employee");
        employee.setAge(50);
        employee.setDeptId("BE");

        mockMvc.perform(post("/rest/employee")
                .contentType("application/json;charset=UTF-8")
                .content(objectMapper.writeValueAsString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").isNotEmpty())
                .andExpect(jsonPath("$.age").value(employee.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deptId").isNotEmpty())
                .andExpect(jsonPath("$.deptId").value(employee.getDeptId()))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateEmployee() throws Exception {
        Long id = 101L;
        String name = "Update Test Employee";
        int age = 22;
        String deptId = "QA";

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        employee.setDeptId(deptId);

        employee = employeeService.saveEmployee(employee);

        assertNotNull(employee.getId());
        Long originalId = employee.getId();

        Assert.assertEquals(employee.getName(), name);
        Assert.assertEquals(employee.getAge(), age);
        Assert.assertEquals(employee.getDeptId(), deptId);

        String newName = "Changed Update Test Employee";
        employee.setName(newName);

        mockMvc.perform(put("/rest/employee/" + id)
                .contentType("application/json;charset=UTF-8")
                .content(objectMapper.writeValueAsString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(originalId))
                .andExpect(jsonPath("$.name").value(newName))
                .andDo(print())
                .andReturn();
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteEmployee() throws Exception {
        Long id = 102L;
        String name = "Delete Test Employee";
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee = employeeService.saveEmployee(employee);

        mockMvc.perform(delete("/rest/employee/" + employee.getId()))
                .andExpect(status().isOk())
                .andDo(print());

        employeeService.findByEmployeeId(id);
    }

}
