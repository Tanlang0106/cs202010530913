package tl.mapper;

import org.apache.ibatis.annotations.Param;
import tl.pojo.Time;
import tl.pojo.Volunteer;

import java.util.List;

public interface VolunteerMapper {
    //添加志愿
    public boolean addVolunteer(Volunteer volunteer);
    //删除所有志愿
    public boolean deleteVolunteer();
    //通过老师id和志愿顺序查询出符合要求的学生志愿信息
    public List<Volunteer> querryAllStudentByTeacherIdAndOrder(@Param("teacherId") String teacherId, @Param("order")Integer order);
    //通过学生id删除该学生的所有志愿
    public boolean deleteAllVolunteerByStudent(String studentId);
    //查询出volunteer表中是否有数据
    public Integer querryCount();
    //通过学生id查询出该学生的所有志愿信息
    public List<Volunteer> querryVolunteerById(String studentId);

}
