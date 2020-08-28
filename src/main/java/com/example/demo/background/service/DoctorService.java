package com.example.demo.background.service;

import com.example.demo.background.entity.Doctor;
import com.example.demo.background.entity.DoctorAndDepart;

import java.util.List;


public interface DoctorService {
    List<Doctor> findAll();
    List<DoctorAndDepart> findAllDoctorAndDepart();
    DoctorAndDepart findDoctorAndDepartById(Long id);
    Boolean AddDoctor(String username, String telNum, String wxNum);
}
