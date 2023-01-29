import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tl.mapper.*;
import tl.pojo.*;


import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {
    @Autowired
    public StudentMapper studentMapper;
    @Autowired
    public TeacherMapper teacherMapper;
    @Autowired
    public AdminMapper adminMapper;
    @Autowired
    public TimeMapper timeMapper;
    @Autowired
    public VolunteerMapper volunteerMapper;
    @Autowired
    public VolunteerResultMapper volunteerResultMapper;

    @org.junit.Test
    public void test(){
        List<Student> students = studentMapper.querryAllStudent();
        System.out.println(students);
    }

    @org.junit.Test
    public void test2(){
        Student stu=new Student();
        stu.setId("202010531020");
        stu.setName("陈万龙");
        stu.setSex("男");
        stu.setClazz("2020级9班");
        stu.setMajor("计算机科学与技术");
        stu.setIntro("不爱敲代码");
        if(studentMapper.addStudnet(stu)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }

    }
    @org.junit.Test
    public void test3(){
        List<Teacher> teachers = teacherMapper.querryAllTeacher();
        System.out.println(teachers);
    }

    @org.junit.Test
    public void test4(){
        Teacher teacher=new Teacher();
        teacher.setId("2");
        teacher.setName("唐静");

        teacher.setPassword("123");
        teacher.setIntro("爱敲代码");
        if(teacherMapper.addTeacher(teacher)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }

    }
    @org.junit.Test
    public void test5(){
        int count = studentMapper.getCount();
        System.out.println(count);
    }
    @org.junit.Test
    public void test6(){
        List<Student> students = studentMapper.querryAllStudentByPage(0, 3);
        System.out.println(students);
    }
    @org.junit.Test
    public void test7(){
        Integer root = adminMapper.querryAdminExist("root", "123");
        System.out.println(root);
        Integer integer = studentMapper.querryStudentExist("202010530913", "123");
        System.out.println(integer);
        Integer integer1 = teacherMapper.querryTeacherExist("1", "123");
        System.out.println(integer1);
    }
    @org.junit.Test
    public void test8(){
        if (timeMapper.deleteTime()){
            System.out.println("删除成功");
        }
    }
    @org.junit.Test
    public void test9(){
        Date date=new Date();
        Volunteer volunteer=new Volunteer();
        volunteer.setStudentId("202010530913");
        volunteer.setTeacherId("1");
        volunteer.setOrder(1);
//        volunteer.setTime(date.toString());
        volunteer.setTime("2019-7-27 15:18:09");

        if (volunteerMapper.addVolunteer(volunteer)){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
    @org.junit.Test
    public void test10(){
        List<Volunteer> volunteers = volunteerMapper.querryAllStudentByTeacherIdAndOrder("1", 1);
        System.out.println(volunteers);
    }
    @org.junit.Test
    public void test11(){
        Volunteer volunteer=new Volunteer();
        volunteer.setStudentId("202010530913");
        volunteer.setTeacherId("1");
        if (volunteerResultMapper.addVolunteerResult(volunteer)){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
    @org.junit.Test
    public void test12(){
        VolunteerResult volunteerResult = volunteerResultMapper.querryMessage("202010531020");
        if (volunteerResult==null){
            System.out.println(volunteerResult);
            System.out.println("1");
        }else {
            System.out.println(volunteerResult);
            System.out.println("2");
        }
    }
    @org.junit.Test
    public void test13(){
        volunteerMapper.deleteVolunteer();
        System.out.println("2222");
    }
    @org.junit.Test
    public void test14(){
        List<Volunteer> volunteers = volunteerMapper.querryVolunteerById("202010530913");
        if (volunteers.size()==0){
            System.out.println("list为空");
        }else {
            System.out.println("list有值");
        }
    }
    @org.junit.Test
    public void test15(){
        if(studentMapper.deleteStudent("111")){
            System.out.println("111");
        }else {
            System.out.println("222");
        }
    }

}
