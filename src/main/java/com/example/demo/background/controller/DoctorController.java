package com.example.demo.background.controller;

import com.example.demo.background.entity.Department;
import com.example.demo.background.entity.DoctorAndDepart;
import com.example.demo.background.service.DepartmentService;
import com.example.demo.background.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("background/doctorList")
    public String doctorList(Model model) {
        List<DoctorAndDepart> doctorAndDepartViews = doctorService.findAllDoctorAndDepart();
        model.addAttribute("doctorAndDepartViews", doctorAndDepartViews);
        return "doctor_list";
    }

    //跳转到添加医生窗口
    @GetMapping("background/doctor/list/add")
    public String toDoctorListAdd() {
        return "doctor_add";
    }

    //跳转到修改医生窗口
    @GetMapping("background/doctor/list/edit")
    public String toDoctorListEdit(HttpServletRequest request, Model model) {
        List<Department> departmentsList = departmentService.findAll();
        String id = request.getParameter("id");
        DoctorAndDepart doctorAndDepart = doctorService.findDoctorAndDepartById(Long.valueOf(id));
        model.addAttribute("doctorAndDepart", doctorAndDepart);
        model.addAttribute("departmentsList", departmentsList);
        return "doctor_edit";
    }

    /*实现添加医生信息*/
    @RequestMapping(value = "background/doctor/list/post/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> toDoctorAdd(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String doctorName = request.getParameter("doctorName").trim();
        String doctorTelNum = request.getParameter("doctorTelNum").trim();
        String doctorWxNum = request.getParameter("doctorWxNum").trim();
        boolean state = doctorService.AddDoctor(doctorName, doctorTelNum, doctorWxNum);
        map.put("state", state);
        return map;
    }

    /*实现修改医生信息*/
    @RequestMapping(value = "background/doctor/list/post/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> toBigAdd(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String doctorName = request.getParameter("doctorName").trim();
        String doctorTelNum = request.getParameter("doctorTelNum").trim();
        String doctorWxNum = request.getParameter("doctorWxNum").trim();
        //医生的id
        String id = request.getParameter("id");
        boolean state = false;


        map.put("state", state);
        return map;
    }

}
