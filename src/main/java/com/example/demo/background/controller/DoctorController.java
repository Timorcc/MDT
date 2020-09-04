package com.example.demo.background.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.example.demo.background.dto.DoctorAndDepart;
import com.example.demo.background.entity.BigSecretary;
import com.example.demo.background.entity.Department;
import com.example.demo.background.service.DepartmentService;
import com.example.demo.background.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
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
        //医生的id
        String id = request.getParameter("id");
        String doctorName = request.getParameter("doctorName").trim();
        String doctorTelNum = request.getParameter("doctorTelNum").trim();
        String doctorWxNum = request.getParameter("doctorWxNum").trim();
        List<String> docDepartList = JSON.parseArray(request.getParameter("docDepartList").trim(), String.class);
        Boolean result1 = doctorService.updateDoctorById(Long.valueOf(id), doctorName, doctorTelNum, doctorWxNum);
        Boolean result2 = doctorService.updateRDDByDoctorId(Long.valueOf(id), docDepartList);
        boolean state = false;
        if ((result1 && result2)){
            state=true;
        }
            map.put("state", state);
        return map;
    }

    //模糊查询
//    @GetMapping(value = "background/fuzzyQueryWithDoc")
//    public String fuzzyQueryWithDoc(HttpServletRequest request, Model model) {
//        String name = request.getParameter("docName");
//        List<BigSecretary> bigSecretaryView = bigSecretaryService.fuzzyFind(name);
//        model.addAttribute("bigSecretaryViews", bigSecretaryView);
//
//        return "big_secretary_list";
//    }


}
