package com.example.demo.background.controller;

import com.example.demo.background.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @RequestMapping(value = "background/main")
    public String mainHtml(HttpServletRequest request, Model model) {
        return "main";
    }
    //输入账号秘密请求登录,登录校验
    @PostMapping(value = "background/login/post")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request,
                                     @RequestParam(name = "username") String username,
                                     @RequestParam(name = "password") String password) {
        Map<String, Object> map = new HashMap<>();
        map = adminService .login(username, password, request);
        return map;
    }
    //注销
    @RequestMapping(value = "background/logout")
    public String loginout(HttpServletRequest request) {
        //false代表：不创建session对象，只是从request中获取。
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("username");
        }
        return "redirect:login";
    }

}
