package com.example.demo.background.service.impl;



import com.example.demo.background.entity.Doctor;
import com.example.demo.background.entity.DoctorAndDepart;
import com.example.demo.background.mapper.DoctorMapper;
import com.example.demo.background.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public List<Doctor> findAll() {
        try {
            return doctorMapper.findAll();
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public List<DoctorAndDepart> findAllDoctorAndDepart() {
        try {
            return doctorMapper.findAllDoctorAndDepart();
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
}
