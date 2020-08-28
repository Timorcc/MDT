package com.example.demo.background.mapper;

import com.example.demo.background.entity.Doctor;
import com.example.demo.background.entity.DoctorAndDepart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DoctorMapper {

    @Select("select * from doctor")
    List<Doctor> findAll();

    @Insert("insert into doctor(username,tel_num,wx_num) values(#{username},#{telNum},#{wxNum})")
    Boolean AddDoctor(String username, String telNum, String wxNum);

    @Select("select * from doctor where id = #{id}")
    Doctor findById(Long id);

//    @Select("select rdd.doc_id,do.username,do.tel_num,do.wx_num, GROUP_CONCAT(de.depart_name SEPARATOR ',') departsName from relevance_doc_depart rdd left join doctor do on rdd.doc_id=do.id left join department de on rdd.depart_id=de.id GROUP BY rdd.doc_id")
//    List<DoctorAndDepart> findAllDoctorAndDepart();
//    @Select("select rdd.doc_id,do.username,do.tel_num,do.wx_num, GROUP_CONCAT(de.depart_name SEPARATOR ',') departsName from relevance_doc_depart rdd left join doctor do on rdd.doc_id=do.id left join department de on rdd.depart_id=de.id where rdd.doc_id = #{id}")
//    DoctorAndDepart findDoctorAndDepartById(Long id);
    @Select("SELECT doc.id, doc.username,doc.tel_num,doc.wx_num,GROUP_CONCAT(dep.depart_name SEPARATOR ',') departsName from doctor doc LEFT JOIN relevance_doc_depart rdd on doc.id = rdd.doc_id LEFT JOIN department dep on rdd.depart_id = dep.id GROUP BY doc.id")
    List<DoctorAndDepart> findAllDoctorAndDepart();
    @Select("SELECT doc.id, doc.username,doc.tel_num,doc.wx_num,GROUP_CONCAT(dep.depart_name SEPARATOR ',') departName from doctor doc LEFT JOIN relevance_doc_depart rdd on doc.id = rdd.doc_id LEFT JOIN department dep on rdd.depart_id = dep.id where doc.id =#{id}")
    DoctorAndDepart findDoctorAndDepartById(Long id);
}