package com.example.demo.background.controller;


import com.example.demo.background.dto.ChatRoomAndMessage;
import com.example.demo.background.service.ChatRoomAndMessageService;
import com.example.demo.mdt.service.ChatRoomService;
import com.example.demo.mdt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ChatRoom {
    @Autowired
    ChatRoomAndMessageService chatRoomAndMessageService;

    //查询所有大秘书列表
    @GetMapping("background/chatRoom")
    public String bigSecretaryList(HttpServletRequest request, Model model) {
        List<ChatRoomAndMessage> chatRoomViews = chatRoomAndMessageService.findAll();
        //List<com.example.demo.mdt.entity.ChatRoom> chatRoomViews = chatRoomService.findAll();
        model.addAttribute("chatRoomViews", chatRoomViews);
        return "chat_room_list";
    }
}
