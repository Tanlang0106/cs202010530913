package tl.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tl.mapper.*;
import tl.pojo.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

@Controller
public class StudentController {
    @Autowired
    public StudentMapper studentMapper;
    @Autowired
    public TimeMapper timeMapper;
    @Autowired
    public VolunteerMapper volunteerMapper;
    @Autowired
    public VolunteerResultMapper volunteerResultMapper;
    @Autowired
    public TeacherMapper teacherMapper;

    //添加学生数据
    @ResponseBody
    @RequestMapping(value = "add-student",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String addStudent(String id,String password, String name,String clazz,String major,String sex,String intro,MultipartFile pic, HttpServletRequest request){
        File fileOne = null;
        byte[] photoOne = null;
        //文件名,不包含后缀名
        String fileName = pic.getOriginalFilename();
        //后缀名
        String prefixOne = fileName.substring(fileName.lastIndexOf("."));
        try {
            //将pic转化为file
            fileOne = File.createTempFile(fileName,prefixOne);
            pic.transferTo(fileOne);

            //再将file转化为byte[]
            FileInputStream fis = new FileInputStream(fileOne);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer,0,len);
            }

            photoOne = baos.toByteArray();
            fis.close();
            baos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(fileOne+"=======");
        System.out.println(photoOne+"------------");




        JSONObject jsonObject=new JSONObject();
        Student student=new Student();
        student.setId(id);
        student.setPassword(password);
        student.setName(name);
        student.setClazz(clazz);
        student.setMajor(major);
        student.setSex(sex);
        student.setIntro(intro);
        student.setImage(photoOne);
        System.out.println(student);
        if(studentMapper.addStudnet(student)){
            jsonObject.put("msg","添加成功");
        }else {
            jsonObject.put("msg","添加失败");
        }

        return jsonObject.toString();
    }


    //通过学生id查询学生信息
    @ResponseBody
    @RequestMapping(value = "querry-student-student",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String querryStudentByStudent(String id){
        JSONObject jsonObject=new JSONObject();


        Student student = studentMapper.querryStudentById(id);
        jsonObject.put("msg",student);


        return jsonObject.toString();
    }

    //修改学生头像
    @ResponseBody
    @RequestMapping(value = "upImage-student",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String updateImage(MultipartFile pic,String id){
        File fileOne = null;
        byte[] photoOne = null;
        //文件名,不包含后缀名
        String fileName = pic.getOriginalFilename();
        //后缀名
        String prefixOne = fileName.substring(fileName.lastIndexOf("."));
        try {
            //将pic转化为file
            fileOne = File.createTempFile(fileName,prefixOne);
            pic.transferTo(fileOne);

            //再将file转化为byte[]
            FileInputStream fis = new FileInputStream(fileOne);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer,0,len);
            }

            photoOne = baos.toByteArray();
            fis.close();
            baos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONObject jsonObject=new JSONObject();

        if(studentMapper.updateImage(photoOne,id)){
            jsonObject.put("msg","修改成功");
        }else {
            jsonObject.put("msg","修改失败");
        }

        return jsonObject.toString();
    }

    //通过学生id修改学生信息
    @ResponseBody
    @RequestMapping(value = "update-student-student",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateStudentByStudent(Student student){
        JSONObject jsonObject=new JSONObject();
        System.out.println(student);

        if (studentMapper.updateStudentByStudent(student)){
            jsonObject.put("code","修改成功");
        }else {
            jsonObject.put("code","修改失败");
        }



        return jsonObject.toString();
    }

    //获取志愿填报开始与截止时间
    @ResponseBody
    @RequestMapping(value = "time-student",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String listTime(){

        JSONObject jsonObject=new JSONObject();
        Time time = timeMapper.getTime();

        jsonObject.put("msg",time);
        return jsonObject.toString();
    }
    //学生查询所有老师数量
    @ResponseBody
    @RequestMapping(value = "querry-teacherCount-student",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String querryTeacherCountByStudent(){

        JSONObject jsonObject=new JSONObject();
        int i = studentMapper.querryTeacherCountByStudent();

        jsonObject.put("count",i);
        return jsonObject.toString();
    }
    //学生提交支援
    @ResponseBody
    @RequestMapping(value = "submit-volunteer-student",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String submitVolunteerByStudent(Volunteer volunteer){
        System.out.println(volunteer);
        JSONObject jsonObject=new JSONObject();
        if (volunteerMapper.addVolunteer(volunteer)){
            jsonObject.put("msg","添加成功");
            System.out.println("添加成功");
        }else {
            jsonObject.put("msg","添加失败");
            System.out.println("添加失败");
        }
        return jsonObject.toString();
    }
    //通过学生id删除学生volunteer中的志愿数据
    @ResponseBody
    @RequestMapping(value = "delete-volunteer-student",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteVolunteerByStudent(String studentId){
        JSONObject jsonObject=new JSONObject();
        if (volunteerMapper.deleteAllVolunteerByStudent(studentId)){
            jsonObject.put("msg","删除成功");
        }else {
            jsonObject.put("msg","删除失败");
        }
        return jsonObject.toString();
    }
    //通过学生id查询指导老师的名字
    @ResponseBody
    @RequestMapping(value = "querry-volunteer-student",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String querryVolunteerByStudent(String studentId){
        System.out.println(studentId+"-----");
        JSONObject jsonObject=new JSONObject();
        VolunteerResult volunteerResult = volunteerResultMapper.querryMessage(studentId);
        if (volunteerResult==null){
            jsonObject.put("code","500");
        }else {
            jsonObject.put("code","200");
            Teacher teacher = teacherMapper.querryTeacherById(volunteerResult.getTeacherId());
            jsonObject.put("name",teacher.getName());
        }
        return jsonObject.toString();
    }

}
