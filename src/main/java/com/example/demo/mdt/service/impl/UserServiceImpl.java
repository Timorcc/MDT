package com.example.demo.mdt.service.impl;

import com.example.demo.background.entity.Doctor;
import com.example.demo.background.entity.SmallSecretary;
import com.example.demo.background.mapper.DoctorMapper;
import com.example.demo.background.mapper.SmallSecretaryMapper;
import com.example.demo.mdt.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    SmallSecretaryMapper smallSecretaryMapper;

    @Override
    public Map<String, Object> login(String telNum, String wxNum, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            Doctor doctor = doctorMapper.findByTelNum(telNum);
            SmallSecretary smallSecretary = smallSecretaryMapper.findByTelNum(telNum);
            log.info("login doctor is  ---==》" + doctor);
            log.info("login smallSecretary is  ---==》" + smallSecretary);

            if (doctor == null && smallSecretary == null) {
                map.put("status", 1);
                map.put("msg", "用户不存在");
                return map;
            }
            //小秘书
            if (doctor == null) {
                if (smallSecretary.getWx_num().equals(wxNum)) {
                    request.getSession().setAttribute("telNum", telNum);
                    request.getSession().setAttribute("id", smallSecretary.getId());
                    request.getSession().setAttribute("wxNum", wxNum);
                    request.getSession().setAttribute("username", smallSecretary.getUsername());

                    map.put("status", 0);
                    map.put("msg", "登录成功");
                    return map;
                }
                map.put("status", 1);
                map.put("msg", "用户登录信息错误");
                return map;
            }
            //医生
            if (smallSecretary == null) {
                if (doctor.getWx_num().equals(wxNum)) {
                    request.getSession().setAttribute("telNum", telNum);
                    request.getSession().setAttribute("id", doctor.getId());
                    request.getSession().setAttribute("wxNum", wxNum);
                    request.getSession().setAttribute("username", doctor.getUsername());

                    map.put("status", 0);
                    map.put("msg", "登录成功");
                    return map;
                }
                map.put("status", 1);
                map.put("msg", "用户登录信息错误");
                return map;
            }
        } catch (Exception e) {
            log.error(e.toString());
            map.put("status", 1);
            map.put("msg", "登录错误");
            return map;
        }
        return map;
    }
}