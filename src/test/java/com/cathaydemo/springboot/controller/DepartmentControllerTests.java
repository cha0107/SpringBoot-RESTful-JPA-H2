package com.cathaydemo.springboot.controller;

import com.cathaydemo.springboot.SpringBootDemoApplication;
import com.cathaydemo.springboot.domain.Department;
import com.cathaydemo.springboot.service.DepartmentService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class DepartmentControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addDepartment() throws Exception {
        Department department = new Department();
        department.setDeptId("TD");
        department.setDeptName("Test Department");

        mockMvc.perform(post("/rest/department")
                .contentType("application/json;charset=UTF-8")
                .content(objectMapper.writeValueAsString(department))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.deptId").isNotEmpty())
                .andExpect(jsonPath("$.deptId").value(department.getDeptId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deptName").isNotEmpty())
                .andExpect(jsonPath("$.deptName").value(department.getDeptName()))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateDepartment() throws Exception {
        String deptId = "UTD";
        String deptName = "Update Test Department";
        Department department = new Department();
        department.setDeptId(deptId);
        department.setDeptName(deptName);
        department = departmentService.saveDepartment(department);

        assertNotNull(department.getDeptId());
        String originalDeptId = department.getDeptId();

        Assert.assertEquals(department.getDeptName(), deptName);

        String newDeptName = "Changed Update Test Department";
        department.setDeptName(newDeptName);

        mockMvc.perform(put("/rest/department/" + deptId)
                .contentType("application/json;charset=UTF-8")
                .content(objectMapper.writeValueAsString(department))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deptId").value(originalDeptId))
                .andExpect(jsonPath("$.deptName").value(newDeptName))
                .andDo(print())
                .andReturn();
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteDepartment() throws Exception {
        String deptId = "DTD";
        String deptName = "Delete Test Department";
        Department department = new Department();
        department.setDeptId(deptId);
        department.setDeptName(deptName);
        department = departmentService.saveDepartment(department);

        mockMvc.perform(delete("/rest/department/" + department.getDeptId()))
                .andExpect(status().isOk())
                .andDo(print());

        departmentService.findByDeptId(deptId);
    }

}
