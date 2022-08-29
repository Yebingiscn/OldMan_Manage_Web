package com.example.mapper;

import com.example.entity.GetNotificationInfo;
import com.example.entity.OldManData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface OldManMapper {
    @Select("select * from oldmaninfo")
    List<OldManData> selectAllOldManData();

    @Insert("insert into clientinfo (id, name, status, location, processing, time) " +
            "VALUE (#{id},#{name},'异常',#{location},#{process},#{time})")
    void addOldManAlbertInfo(@Param("id") int id, @Param("name") String name,
                             @Param("location") String location,
                             @Param("process") String process, @Param("time") Date date);

    @Select("select id from oldmaninfo where name = #{name} and contact = #{contact}")
    int findIdByNameAndContact(@Param("name") String name, @Param("contact") String contact);

    @Select("SELECT oldmaninfo.id, oldmaninfo.name, sex, contact, clientinfo.status, " +
            "location, processing, time FROM oldmaninfo LEFT JOIN clientinfo ON oldmaninfo.id")
    List<GetNotificationInfo> selectAllNotice();
}
