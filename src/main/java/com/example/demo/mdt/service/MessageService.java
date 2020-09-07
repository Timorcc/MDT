package com.example.demo.mdt.service;

import com.example.demo.mdt.entity.Message;

import java.util.Date;
import java.util.List;


public interface MessageService {
    List<Message> findMessageByChatRoomId(Long chatRoomId);
    Boolean insertMessage(Long chatRoomId, Long userId, Date date, String content);
}
