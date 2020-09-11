package com.example.demo.mdt.service;

import com.example.demo.mdt.entity.ChatRoom;

import java.util.Date;
import java.util.List;

public interface ChatRoomService {
    List<ChatRoom> findAll();
    Boolean addChatRoom(String name, Date createTime, Long createAdminId, Long departId, boolean state);
    Boolean updateChatRoomConclusion(Long id,Date endDate,String conclusion);
}
