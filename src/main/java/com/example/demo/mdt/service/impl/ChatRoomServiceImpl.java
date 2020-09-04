package com.example.demo.mdt.service.impl;

import com.example.demo.mdt.entity.ChatRoom;
import com.example.demo.mdt.mapper.ChatRoomMapper;
import com.example.demo.mdt.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    ChatRoomMapper chatRoomMapper;

    @Override
    public List<ChatRoom> findAll() {
        try {
            return chatRoomMapper.findAll();
        }catch (Exception e){
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public Boolean addChatRoom(String name, Date createTime, String createAdminId, Long departId, boolean state) {
        return null;
    }
}
