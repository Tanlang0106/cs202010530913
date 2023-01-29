package tl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HrefController {

    @RequestMapping("image")
    public String image(){
        return "image";
    }


    @RequestMapping("list")
    public String list(){
        return "list";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }
//    学生
    @RequestMapping("student-list-admin")
    public String listByAdmin(){
        return "admin/list-student";
    }

    @RequestMapping("student-add-admin")
    public String addStudent(){
        return "admin/add-student";
    }

    @RequestMapping("student-update-admin")
    public String updateStudent(String studentID,HttpServletRequest request){
        System.out.println(studentID);
        request.getSession().setAttribute("studentID",studentID);
        return "admin/update-student";
    }
    @RequestMapping("student-detail-admin")
    public String detailStudent(String studentID,HttpServletRequest request){
        System.out.println(studentID);
        request.getSession().setAttribute("studentID",studentID);
        return "admin/detail-student";
    }


//    老师
    @RequestMapping("teacher-list-admin")
    public String listTeacherByAdmin(){
        return "admin/list-teacher";
    }
    @RequestMapping("teacher-add-admin")
    public String addTeacher(){
        return "admin/add-teacher";
    }
    @RequestMapping("teacher-update-admin")
    public String updateTeacher(String teacherID,HttpServletRequest request){
        System.out.println(teacherID);
        request.getSession().setAttribute("teacherID",teacherID);
        return "admin/update-teacher";
    }

    //志愿
    @RequestMapping("zhiyuan-list-admin")
    public String listZhiYuanByAdmin(){
        return "admin/list-zhiyuan";
    }


    //学生操作
    @RequestMapping("student-detail-student")
    public String detailStudentByStudent(){
        return "student/detail-student";
    }
    //学生修改自己信息
    @RequestMapping("student-update-student")
    public String updateStudentByStudent(){
        return "student/update-student";
    }
    //学生填报志愿
    @RequestMapping("volunteer-student")
    public String volunteerByStudent(){
        return "student/volunteer-student";
    }

    //老师操作
    @RequestMapping("teacher-detail-teacher")
    public String detailTeacherByTeacher(){
        return "teacher/detail-teacher";
    }

    //老师修改自己信息
    @RequestMapping("teacher-update-teacher")
    public String updateTeacherByTeacher(){
        return "teacher/update-teacher";
    }
    //老师指导学生信息
    @RequestMapping("teacher-listStudent-teacher")
    public String querryStudentByTeacher(){
        return "teacher/list-student";
    }
    @RequestMapping("student-detail-teacher")
    public String detailStudentByTeacher(String studentID,HttpServletRequest request){
        System.out.println(studentID);
        request.getSession().setAttribute("studentID",studentID);
        return "teacher/detail-student";
    }
}
