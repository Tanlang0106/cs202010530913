package tl.mapper;

import org.apache.ibatis.annotations.Param;
import tl.pojo.Student;
import tl.pojo.Teacher;

import java.util.List;

public interface StudentMapper {
    //查询所有的学生数据
    public List<Student> querryAllStudent();
//    添加学生数据
    public boolean addStudnet(Student studnet);

    public int getCount();
    //分页查询学生
    public List<Student> querryAllStudentByPage(@Param("begin") int begin, @Param("pageSize") int pageSize);

    //管理员删除学生数据
    public boolean deleteStudent(String id);
    //管理员添加学生数据
    public boolean addStudentByAdmin(Student student);
    //管理员修改学生数据
    public boolean updateStudentByAdmin(Student student);

    //通过id查询学生信息
    public Student querryStudentById(String id);





    //登陆时，通过id，密码查询学生是否存在
    public Integer querryStudentExist(@Param("id")String id,@Param("password")String password);

    //修改学生头像
    public boolean updateImage(@Param("image") byte[] image,@Param("id") String id);
    //修改学生数据
    public boolean updateStudentByStudent(Student student);

    //学生查询所有老师数量
    public int querryTeacherCountByStudent();







}
