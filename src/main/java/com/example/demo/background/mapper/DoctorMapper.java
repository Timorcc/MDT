package com.example.demo.background.mapper;

import com.example.demo.background.entity.Doctor;
import com.example.demo.background.dto.DoctorAndDepart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Mapper
public interface DoctorMapper {
    //医生表中查全部医生
    @Select("select * from doctor")
    List<Doctor> findAll();

    //医生表中插入
    @Insert("insert into doctor(username,tel_num,wx_num) values(#{username},#{telNum},#{wxNum})")
    Boolean AddDoctor(String username, String telNum, String wxNum);

    //医生表中根据id查具体医生
    @Select("select * from doctor where id = #{id}")
    Doctor findById(Long id);
    //医生表中根据姓名查询医生
    @Select("select * from doctor where tel_num = #{telNum}")
    Doctor findByTelNum(String telNum);

    //医生表中根据id更新某一医生的信息
    @Update("update doctor set username=#{username},tel_num = #{tel_num},wx_num=#{wx_num} where id = #{id}")
    Boolean updateDoctorById(Long id, String username, String tel_num, String wx_num);

    //关联查询 查询所有医生 && 所属的科室
    @Select("SELECT doc.id, doc.username,doc.tel_num,doc.wx_num,GROUP_CONCAT(dep.depart_name SEPARATOR ',') departsName from doctor doc LEFT JOIN relevance_doc_depart rdd on doc.id = rdd.doc_id LEFT JOIN department dep on rdd.depart_id = dep.id GROUP BY doc.id")
    List<DoctorAndDepart> findAllDoctorAndDepart();

    //关联查询 根据医生的id查询医生 && 所属科室
    @Select("SELECT doc.id, doc.username,doc.tel_num,doc.wx_num,GROUP_CONCAT(dep.depart_name SEPARATOR ',') departName from doctor doc LEFT JOIN relevance_doc_depart rdd on doc.id = rdd.doc_id LEFT JOIN department dep on rdd.depart_id = dep.id where doc.id =#{id}")
    DoctorAndDepart findDoctorAndDepartById(Long id);

    //删除关联表中 医生id=？的所有数据
    @Delete("DELETE from relevance_doc_depart where doc_id = #{id}")
    Boolean deleteRDDByDoctorId(Long id);

    //在关联表中添加医生id 科室id
    @Insert("insert into relevance_doc_depart(doc_id,depart_id) values (#{doc_id},#{depart_id})")
    Boolean addRDD(Long doc_id, Long depart_id);

    //根据科室id 查 医生
    @Select("SELECT doc.id, doc.tel_num,doc.wx_num,doc.username,dep.id as departId,dep.depart_name as departsName FROM doctor doc LEFT JOIN relevance_doc_depart rdd on rdd.doc_id = doc.id LEFT JOIN department dep on dep.id = rdd.depart_id where depart_id = #{id}")
    List<DoctorAndDepart> findDoctorByDepartId(Long id);

    //根据聊天室的id查询其中的医生及其所属的科室
    @Select("SELECT doc.id,doc.tel_num,doc.wx_num,doc.username,dep.id as departId,dep.depart_name as departsName FROM doctor doc LEFT JOIN relevance_doc_depart rdd on rdd.doc_id = doc.id LEFT JOIN department dep on dep.id = rdd.depart_id where depart_id= (SELECT c.depart_id FROM chat_room c WHERE id=#{id})")
    List<DoctorAndDepart> findDoctorByChatRoomId(Long id);
}

