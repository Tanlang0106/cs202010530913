package tl.mapper;

import org.apache.ibatis.annotations.Param;
import tl.pojo.Teacher;
import tl.pojo.Time;

import java.util.List;

public interface TimeMapper {
    //查询开始与截止时间
    public Time getTime();
    //删除所有时间
    public boolean deleteTime();
    //添加开始与截止时间
    public boolean addTime(@Param("startTime") String startTime,@Param("endTime")String endTime);
}
