package com.cathaydemo.springboot.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EmployeeDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String name;

    private Integer age;

    private String deptId;

    private String deptName;


    public EmployeeDetail() {
    }

    public EmployeeDetail(Long id, String name, Integer age, String deptId, String deptName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "EmployeeDetail{" +
                "id=" + id +
                ", name='" + name +
                ", age='" + age +
                ", deptId='" + deptId +
                ", deptName='" + deptName +
                '}';
    }
}
