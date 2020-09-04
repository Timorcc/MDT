package com.example.demo.mdt.controller;

import com.example.demo.background.entity.Department;
import com.example.demo.background.service.DepartmentService;

import com.example.demo.mdt.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatRoomController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    ChatRoomService chatRoomService;

    @GetMapping("mdt/chatRoom/add")
    public String addChatRoom(Model model) {
        List<Department> departmentViews = departmentService.findAll();
        model.addAttribute("departmentViews", departmentViews);
        return "mdt_chat_add";
    }

    /*实现添加新的聊天室信息*/
    @RequestMapping(value = "mdt/chatRoom/post/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> toDoctorAdd(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String chatRoomName = request.getParameter("chatRoomName").trim();
        String depart_id = request.getParameter("depart_id").trim().replace("\"", "");;
        Object username = request.getSession().getAttribute("username");
        Object id = request.getSession().getAttribute("id");
        Date date = new Date();
        boolean state = chatRoomService.addChatRoom(chatRoomName,date,Long.valueOf(id.toString()),Long.valueOf(depart_id),true);
        map.put("state", state);
        return map;
    }

}
