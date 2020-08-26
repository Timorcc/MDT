package com.example.demo.background.service.impl;

import com.example.demo.background.entity.Department;
import com.example.demo.background.mapper.DepartmentMapper;
import com.example.demo.background.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAll() {
        try {
            return departmentMapper.findAll();
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public Boolean AddDepartment(String departmentName) {
        try {
            return departmentMapper.AddDepartment(departmentName);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public Department findById(Long id) {
        try {
            return departmentMapper.findById(id);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public Boolean updateById(Long id, String name) {
        try {
            return departmentMapper.updateById(id, name);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }
}
