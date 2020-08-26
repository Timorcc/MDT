package com.example.demo.background.controller;

import com.example.demo.background.entity.Department;
import com.example.demo.background.entity.Doctor;
import com.example.demo.background.entity.DoctorAndDepart;
import com.example.demo.background.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("background/doctorList")
    public String doctorList(Model model) {
        List<DoctorAndDepart> doctorAndDepartViews = doctorService.findAllDoctorAndDepart();
        model.addAttribute("doctorAndDepartViews",doctorAndDepartViews);
        return "doctor_list";
    }

    //跳转到添加||修改科室窗口
    @GetMapping("background/doctor/list/{type}")
    public String toDepartmentList(HttpServletRequest request, Model model, @PathVariable("type") String type) {
//        if (type.equals("edit")) {
//            String id = request.getParameter("id");
//            Department department = departmentService.findById(Long.valueOf(id));
//            model.addAttribute("department", department);
//        }
        model.addAttribute("type", type);
        return "doctor_add_edit";
    }




}
