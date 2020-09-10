package com.example.demo.background.service.impl;

import com.example.demo.background.dto.ChatRoomAndMessage;
import com.example.demo.background.mapper.ChatRoomAndMessageMapper;
import com.example.demo.background.service.ChatRoomAndMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChatRoomAndMessageServiceImpl implements ChatRoomAndMessageService {

    @Autowired
    ChatRoomAndMessageMapper chatRoomAndMessageMapper;

    @Override
    public List<ChatRoomAndMessage> findAll() {
        try {
            return chatRoomAndMessageMapper.findAll();
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }

    }
}
