package com.example.demo.background.service.impl;

import com.example.demo.background.entity.Admin;
import com.example.demo.background.mapper.AdminMapper;
import com.example.demo.background.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Map<String, Object> login(String username, String password, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            Admin bgdAdmin = adminMapper.findByUsername(username);
            log.info("login user is  ---==》" + bgdAdmin);

            if (bgdAdmin == null) {
                map.put("status", 1);
                map.put("msg", "用户名不存在");
                return map;
            }
            if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(bgdAdmin.getPassword())) {
                map.put("status", 1);
                map.put("msg", "密码错误");
                return map;
            }
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("id", bgdAdmin.getId());
            map.put("status", 0);
            map.put("msg", "登录成功");
            return map;
        } catch (Exception e) {
            log.error(e.toString());
            map.put("status", 1);
            map.put("msg", "用户名或密码错误");
            return map;
        }

    }
}
