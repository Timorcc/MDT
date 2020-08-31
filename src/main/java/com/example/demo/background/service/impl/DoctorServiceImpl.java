package com.example.demo.background.service.impl;


import com.example.demo.background.dto.DoctorAndDepart;
import com.example.demo.background.entity.Doctor;
import com.example.demo.background.mapper.DoctorMapper;
import com.example.demo.background.service.DoctorService;
import com.sun.org.apache.bcel.internal.generic.RET;
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

    @Override
    public DoctorAndDepart findDoctorAndDepartById(Long id) {
        try {
            return doctorMapper.findDoctorAndDepartById(id);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public Boolean AddDoctor(String username, String telNum, String wxNum) {
        try {
            return doctorMapper.AddDoctor(username, telNum, wxNum);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public Boolean updateDoctorById(Long id, String username, String tel_num, String wx_num) {
        try {
            return doctorMapper.updateDoctorById(id, username, tel_num, wx_num);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public Boolean deleteRDDByDoctorId(Long id) {
        try {
            return doctorMapper.deleteRDDByDoctorId(id);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public Boolean updateRDDByDoctorId(Long id, List<String> docDepartList) {
        try {
            doctorMapper.deleteRDDByDoctorId(id);
            for (String departId : docDepartList
            ) {
                doctorMapper.addRDD(id, Long.valueOf(departId));
            }
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }
}
