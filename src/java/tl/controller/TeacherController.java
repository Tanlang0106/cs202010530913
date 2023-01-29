package tl.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tl.mapper.StudentMapper;
import tl.mapper.TeacherMapper;
import tl.mapper.VolunteerResultMapper;
import tl.pojo.Student;
import tl.pojo.Teacher;
import tl.pojo.VolunteerResult;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    public TeacherMapper teacherMapper;
    @Autowired
    public VolunteerResultMapper volunteerResultMapper;
    @Autowired
    public StudentMapper studentMapper;
    //修改老师头像
    @ResponseBody
    @RequestMapping(value = "upImage-teacher",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String updateImage(MultipartFile pic, String id){
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

        if(teacherMapper.updateImage(photoOne,id)){
            jsonObject.put("msg","修改成功");
        }else {
            jsonObject.put("msg","修改失败");
        }

        return jsonObject.toString();
    }

    //通过老师id修改老师信息
    @ResponseBody
    @RequestMapping(value = "update-teacher-teacher",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateTeacherByTeacher(Teacher teacher){
        JSONObject jsonObject=new JSONObject();
        System.out.println(teacher);

        if (teacherMapper.updateTeacherByTeacher(teacher)){
            jsonObject.put("code","修改成功");
            System.out.println("==========修改成功");
        }else {
            jsonObject.put("code","修改失败");
            System.out.println("==========修改失败");
        }



        return jsonObject.toString();
    }

    //老师查询自己指导的所有学生(数据表格)
    @ResponseBody
    @RequestMapping(value = "getAllStudent-teacher",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public String getAllStudentByPage(int page,int limit,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();


        Object loginId = request.getSession().getAttribute("loginID");
        System.out.println(loginId);
        List<VolunteerResult> volunteerResults = volunteerResultMapper.querryVolunteerResult(loginId.toString());
        System.out.println(volunteerResults);
        List<Student> students=new ArrayList<>();
        for (int i=0;i<volunteerResults.size();i++){
            VolunteerResult volunteerResult = volunteerResults.get(i);
            Student student = studentMapper.querryStudentById(volunteerResult.getStudentId());
            students.add(student);
        }

        System.out.println(students);
        int count = studentMapper.getCount();
        System.out.println(count);
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",count);
        jsonObject.put("data",students);

        return jsonObject.toString();
    }
}
