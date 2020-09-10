package com.example.demo.background.service;

import com.example.demo.background.dto.ChatRoomAndMessage;

import java.util.List;

public interface ChatRoomAndMessageService {
    List<ChatRoomAndMessage> findAll();
}
