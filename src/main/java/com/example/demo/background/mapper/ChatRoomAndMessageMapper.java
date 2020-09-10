package com.example.demo.background.mapper;

import com.example.demo.background.dto.ChatRoomAndMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatRoomAndMessageMapper {
    @Select("SELECT c.id,c.name,c.create_date,c.create_admin_id,s.username,c.state,c.end_date,c.depart_id,d.depart_name,c.conclusion FROM chat_room c LEFT JOIN small_secretary s on  c.create_admin_id = s.id LEFT JOIN department d on d.id= c.depart_id")
    List<ChatRoomAndMessage> findAll();
}
