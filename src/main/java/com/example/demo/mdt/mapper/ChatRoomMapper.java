package com.example.demo.mdt.mapper;

import com.example.demo.mdt.entity.ChatRoom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface ChatRoomMapper {
    @Select("select * from chat_room")
    List<ChatRoom> findAll();

    @Insert("insert into chat_room(name,create_date,create_admin_id,depart_id,state) values (#{name},#{createTime},#{createAdminId},#{departId},#{state})" )
    Boolean addChatRoom(String name, Date createTime, Long createAdminId, Long departId, boolean state);

    @Update("update chat_room set end_date=#{endDate},conclusion=#{conclusion}, state=false where id=#{id}")
    Boolean updateChatRoomConclusion(Long id,Date endDate,String conclusion);


}
