package tl.mapper;

import org.apache.ibatis.annotations.Param;
import tl.pojo.Student;
import tl.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {

    //查询所有的老师信息
    public List<Teacher> querryAllTeacher();
    //添加老师的信息
    public boolean addTeacher(Teacher teacher);


    //分页查询老师
    public List<Teacher> querryAllTeacherByPage(@Param("begin") int begin, @Param("pageSize") int pageSize);
    //获取老师数据条数
    public int getCount();

    //管理员添加老师数据
    public boolean addTeacherByAdmin(Teacher teacher);
    //管理员删除老师数据
    public boolean deleteTeacher(String id);
    //通过id查询老师信息
    public Teacher querryTeacherById(String id);
    //管理员修改老师数据
    public boolean updateTeacherByAdmin(Teacher teacher);


    //登陆时，通过id，password查询教师是否存在
    public Integer querryTeacherExist(@Param("id")String id,@Param("password")String password);

    //修改老师头像
    public boolean updateImage(@Param("image") byte[] image,@Param("id") String id);
    //修改老师数据
    public boolean updateTeacherByTeacher(Teacher teacher);
}
