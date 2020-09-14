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
    @Select("select * from  chat_message  where chatroom_id = #{chatRoomId} order by send_date ")
    List<Message> findMessageByChatRoomId(Long chatRoomId);

    @Insert("insert into chat_message(chatroom_id,sender,send_date,content,username) values (#{chatRoomId},#{userId},#{date},#{content},#{username})")
    Boolean insertMessage(@Param("chatRoomId") Long chatRoomId,@Param("userId") Long userId,
                          @Param("date") Date date,@Param("content") String content,String username);
}
