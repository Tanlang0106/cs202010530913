package tl.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tl.mapper.AdminMapper;
import tl.mapper.StudentMapper;
import tl.mapper.TeacherMapper;
import tl.pojo.Admin;
import tl.pojo.Student;
import tl.pojo.Teacher;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @ResponseBody
    @RequestMapping(value = "login-test",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login(String id, String password, HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        Integer integer = adminMapper.querryAdminExist(id, password);
        if (integer==1){
            jsonObject.put("msg","你的账号是管理员账号");
            jsonObject.put("code",200);
            request.getSession().setAttribute("loginID",id);
            request.getSession().setAttribute("loginType",0);
        }else {
            Integer integer1 = studentMapper.querryStudentExist(id, password);
            if (integer1==1){
                jsonObject.put("msg","你的账号是学生账号");
                jsonObject.put("code",200);
                request.getSession().setAttribute("loginID",id);
                request.getSession().setAttribute("loginType",1);
            }else {
                Integer integer2 = teacherMapper.querryTeacherExist(id, password);
                if (integer2==1){
                    jsonObject.put("msg","你的账号是教师账号");
                    jsonObject.put("code",200);
                    request.getSession().setAttribute("loginID",id);
                    request.getSession().setAttribute("loginType",2);
                }else {
                    jsonObject.put("msg","你的账号不存在或是密码错误");
                    jsonObject.put("code",500);
                }
            }
        }
        return jsonObject.toString();
    }
    //查看账号类型及账号信息
    @ResponseBody
    @RequestMapping(value = "query-type",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String codeType(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        Object loginType = request.getSession().getAttribute("loginType");
        Object loginID = request.getSession().getAttribute("loginID");
        jsonObject.put("msg",loginType.toString());
        jsonObject.put("id",loginID.toString());
        return jsonObject.toString();
    }

    //通过管理员id查询管理员信息
    @ResponseBody
    @RequestMapping(value = "querry-root-login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String querryRootLogin(String id){
        JSONObject jsonObject=new JSONObject();


        Admin admin = adminMapper.querryAdmin(id);
        jsonObject.put("msg",admin);


        return jsonObject.toString();
    }
    //通过学生id查询学生信息
    @ResponseBody
    @RequestMapping(value = "querry-student-login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String querryStudentLogin(String id){
        JSONObject jsonObject=new JSONObject();


        Student student = studentMapper.querryStudentById(id);
        jsonObject.put("msg",student);


        return jsonObject.toString();
    }
    //通过老师id查询老师信息
    @ResponseBody
    @RequestMapping(value = "querry-teacher-login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String querryTeacherLogin(String id){
        JSONObject jsonObject=new JSONObject();


        Teacher teacher = teacherMapper.querryTeacherById(id);
        jsonObject.put("msg",teacher);


        return jsonObject.toString();
    }
}
