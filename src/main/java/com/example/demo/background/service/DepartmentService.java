package com.example.demo.background.service;

import com.example.demo.background.entity.Department;
import com.example.demo.background.entity.Doctor;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Boolean AddDepartment(String departmentName);
    Department findById(Long id);
    Boolean updateById(Long id, String name);


}
