package com.example.demo.background.service;

import com.example.demo.background.dto.DoctorAndDepart;
import com.example.demo.background.entity.Doctor;

import java.util.List;


public interface DoctorService {
    List<Doctor> findAll();

    List<DoctorAndDepart> findAllDoctorAndDepart();

    DoctorAndDepart findDoctorAndDepartById(Long id);

    Boolean AddDoctor(String username, String telNum, String wxNum);

    Boolean updateDoctorById(Long id, String username, String tel_num, String wx_num);

    Boolean deleteRDDByDoctorId(Long id);

    Boolean updateRDDByDoctorId(Long id, List<String> docDepartList);

    List<DoctorAndDepart> findDoctorByChatRoomId(Long id);

    Doctor findById(Long id);
}
