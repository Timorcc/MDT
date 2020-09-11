package com.example.demo.mdt.controller;

import com.example.demo.background.dto.DoctorAndDepart;
import com.example.demo.background.entity.Department;
import com.example.demo.background.entity.SmallSecretary;
import com.example.demo.background.service.DepartmentService;

import com.example.demo.background.service.DoctorService;
import com.example.demo.background.service.SmallSecretaryService;
import com.example.demo.mdt.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    DoctorService doctorService;

    @Autowired
    SmallSecretaryService smallSecretaryService;

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
        String depart_id = request.getParameter("depart_id").trim().replace("\"", "");
        Object username = request.getSession().getAttribute("username");
        Object id = request.getSession().getAttribute("id");
        Date date = new Date();
        boolean state = chatRoomService.addChatRoom(chatRoomName, date, Long.valueOf(id.toString()), Long.valueOf(depart_id), true);
        map.put("state", state);
        return map;
    }

    /*根据roomId&userId跳转到聊天室页面*/
    @GetMapping("/mdt/chat/{roomId}/{userId}")
    public String toChatRoom(Model model, @PathVariable("roomId") String roomId, @PathVariable("userId") String userId) {
//        System.out.println("接收到的roomid是");
//        System.out.println(roomId);
        List<DoctorAndDepart> doctorAndDepartViews = doctorService.findDoctorByChatRoomId(Long.valueOf(roomId));
        List<SmallSecretary> smallSecretariesViews = smallSecretaryService.getSmallSecretaryList();
        model.addAttribute("doctorAndDepartViews", doctorAndDepartViews);
        model.addAttribute("smallSecretariesViews", smallSecretariesViews);
        model.addAttribute("roomId", roomId);
        model.addAttribute("userId", userId);
//        System.out.println("======================");
//        System.out.println(doctorAndDepartViews);
//        System.out.println(smallSecretariesViews);
//        System.out.println("======================");
        return "mdt_chatRoom";
    }

    /*跳转到关闭聊天室弹窗*/
    @GetMapping("/mdt/chatRoom/closeChatRoom")
    public String toCloseChatRoom(HttpServletRequest request, Model model) {
        String chatRoomId = request.getParameter("id");
        model.addAttribute("chatRoomId", chatRoomId);
        return "mdt_chat_close";
    }
    /*实现关闭聊天室功能*/
    @PostMapping("/mdt/chatRoom/closeChatRoom/post")
    @ResponseBody
    public Map<String, Object> closeChatRoom(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String conclusion = request.getParameter("conclusion").trim();
        String chatRoomId = request.getParameter("chatRoomId").trim();
        Date date = new Date();
        boolean re = chatRoomService.updateChatRoomConclusion(Long.valueOf(chatRoomId),date,conclusion);
        map.put("state", re);
        return map;
    }
}
