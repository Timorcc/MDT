package com.example.demo.background.controller;


import com.example.demo.background.dto.ChatRoomAndMessage;
import com.example.demo.background.service.ChatRoomAndMessageService;
import com.example.demo.mdt.entity.Message;
import com.example.demo.mdt.service.ChatRoomService;
import com.example.demo.mdt.service.MessageService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ChatRoom {
    @Autowired
    ChatRoomAndMessageService chatRoomAndMessageService;

    @Autowired
    MessageService messageService;

    //查询所有聊天室
    @GetMapping("background/chatRoom")
    public String chatRoomList(HttpServletRequest request, Model model) {
        List<ChatRoomAndMessage> chatRoomViews = chatRoomAndMessageService.findAll();
        //List<com.example.demo.mdt.entity.ChatRoom> chatRoomViews = chatRoomService.findAll();
        model.addAttribute("chatRoomViews", chatRoomViews);
        return "chat_room_list";
    }

    @GetMapping("background/chatRoom/list/chatMessage")
    public String toChatRoomAndMessage(HttpServletRequest request, Model model) {
        String id = request.getParameter("chatRoomId");
        List<Message> messageList = messageService.findMessageByChatRoomId(Long.valueOf(id));
        model.addAttribute("messageList",messageList);
        return "message_list";
    }



}
