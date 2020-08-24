package com.example.demo.background.controller;

import com.example.demo.background.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    //请求登录主页面
    @RequestMapping(value = "background/login")
    public String loginHtml() {
        return "login";
    }

    @RequestMapping(value = "background/index")
    public String index(HttpServletRequest request) {
        Object username = request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:login";
        }
        return "index";
    }

    //输入账号秘密请求登录,登录校验
    @PostMapping(value = "background/login/post")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request,
                                     @RequestParam(name = "username") String username,
                                     @RequestParam(name = "password") String password) {

        Map<String, Object> map = new HashMap<>();
        map = adminService.login(username, password, request);
        return map;
    }

}
