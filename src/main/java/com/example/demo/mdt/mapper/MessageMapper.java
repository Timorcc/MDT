package com.example.demo.mdt.mapper;

import com.example.demo.mdt.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper

public interface MessageMapper {
    @Select("select  c.id,c.chatroom_id,c.sender,s.username,c.send_date,c.content  from  chat_message c LEFT JOIN small_secretary s  on  s.id = c.sender where chatroom_id = #{chatRoomId} order by send_date ")
    List<Message> findMessageByChatRoomId(Long chatRoomId);

    @Insert("insert into chat_message(chatroom_id,sender,send_date,content) values (#{chatRoomId},#{userId},#{date},#{content})")
    Boolean insertMessage(@Param("chatRoomId") Long chatRoomId,@Param("userId") Long userId,
                          @Param("date") Date date,@Param("content") String content);
}
