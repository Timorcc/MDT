package com.example.demo.mdt.mapper;

import com.example.demo.mdt.entity.ChatRoom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface ChatRoomMapper {
    @Select("select * from chat_room")
    List<ChatRoom> findAll();

    @Insert("insert into chat_room(name,create_date,create_admin_id,depart_id,state) values (#{name},#{createTime},#{createAdminId},#{departId},#{state})" )
    Boolean addChatRoom(String name, Date createTime, Long createAdminId, Long departId, boolean state);


}
