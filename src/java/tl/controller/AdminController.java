package tl.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tl.mapper.*;
import tl.pojo.Student;
import tl.pojo.Teacher;
import tl.pojo.Time;
import tl.pojo.Volunteer;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
public class AdminController {
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

    //查询所有的学生数据
    @ResponseBody
    @RequestMapping(value = "queryAllStudent", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String queryAllStudent() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", studentMapper.querryAllStudent());
        return jsonObject.toString();
    }

    //分页查询所有学生
    @ResponseBody
    @RequestMapping(value = "getAllStudent-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getAllStudentByPage(int page, int limit) {
        JSONObject jsonObject = new JSONObject();

        int begin = (page - 1) * limit;
        if (begin < 0) {
            begin = 0;
        }

        List<Student> students = studentMapper.querryAllStudentByPage(begin, limit);
        System.out.println(students);
        int count = studentMapper.getCount();
        System.out.println(count);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", count);
        jsonObject.put("data", students);

        return jsonObject.toString();
    }

    //通过学生id删除学生数据
    @ResponseBody
    @RequestMapping(value = "student-delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteStudent(String studentID) {
        JSONObject jsonObject = new JSONObject();


        try {
            int count = 0;
            String[] ids = studentID.split(",");
            for (int i = 1; i < ids.length; i++) {
                if (studentMapper.deleteStudent(ids[i]))
                    count++;
            }
            jsonObject.put("code", 200);
            jsonObject.put("count", count);
        } catch (Exception ex) {
            jsonObject.put("code", 500);
        }

        return jsonObject.toString();
    }

    //    添加学生
    @ResponseBody
    @RequestMapping(value = "student-add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addStudent(Student student) {
        System.out.println(student);
        JSONObject jsonObject = new JSONObject();
        if (studentMapper.addStudentByAdmin(student)) {
            jsonObject.put("code", "200");
        } else {
            jsonObject.put("code", "500");
        }
        return jsonObject.toString();
    }

    //    从session中获取学生id
    @ResponseBody
    @RequestMapping(value = "student-id", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querryStudentId(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        Object studentId = request.getSession().getAttribute("studentID");
        System.out.println("=====" + studentId.toString());
        jsonObject.put("studentID", studentId.toString());

        return jsonObject.toString();
    }

    //    通过学生id查询学生信息
    @ResponseBody
    @RequestMapping(value = "student-querry", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querryStudentById(String id) {
        JSONObject jsonObject = new JSONObject();
        Student student = studentMapper.querryStudentById(id);
        jsonObject.put("msg", student);

        return jsonObject.toString();
    }

    //    修改学生信息
    @ResponseBody
    @RequestMapping(value = "student-update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateStudent(Student student) {
        System.out.println(student);
        JSONObject jsonObject = new JSONObject();
        if (studentMapper.updateStudentByAdmin(student)) {
            jsonObject.put("code", "200");
        } else {
            jsonObject.put("code", "500");
        }
        return jsonObject.toString();
    }


    //分页查询所有老师
    @ResponseBody
    @RequestMapping(value = "getAllTeacher-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getAllTeacherByPage(int page, int limit) {
        JSONObject jsonObject = new JSONObject();

        int begin = (page - 1) * limit;
        if (begin < 0) {
            begin = 0;
        }

        List<Teacher> teachers = teacherMapper.querryAllTeacherByPage(begin, limit);
        int count = teacherMapper.getCount();
        System.out.println(count);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", count);
        jsonObject.put("data", teachers);

        return jsonObject.toString();
    }

    //    添加老师
    @ResponseBody
    @RequestMapping(value = "teacher-add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addTeacher(Teacher teacher) {
        System.out.println(teacher);
        JSONObject jsonObject = new JSONObject();
        if (teacherMapper.addTeacherByAdmin(teacher)) {
            jsonObject.put("code", "200");
        } else {
            jsonObject.put("code", "500");
        }
        return jsonObject.toString();
    }

    //通过老师id删除老师数据
    @ResponseBody
    @RequestMapping(value = "teacher-delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteTeacher(String teacherID) {
        JSONObject jsonObject = new JSONObject();

        try {
            int count = 0;
            String[] ids = teacherID.split(",");
            for (int i = 1; i < ids.length; i++) {
                if (teacherMapper.deleteTeacher(ids[i]))
                    count++;
            }
            jsonObject.put("code", 200);
            jsonObject.put("count", count);
        } catch (Exception ex) {
            jsonObject.put("code", 500);
        }

        return jsonObject.toString();
    }

    //    从session中获取老师id
    @ResponseBody
    @RequestMapping(value = "teacher-id", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querryTeacherId(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        Object teacherID = request.getSession().getAttribute("teacherID");
        System.out.println("=====" + teacherID.toString());
        jsonObject.put("teacherID", teacherID.toString());

        return jsonObject.toString();
    }

    //    通过老师id查询老师信息
    @ResponseBody
    @RequestMapping(value = "teacher-querry", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querryTeacherById(String id) {
        JSONObject jsonObject = new JSONObject();
        Teacher teacher = teacherMapper.querryTeacherById(id);
        jsonObject.put("msg", teacher);

        return jsonObject.toString();
    }

    //    修改老师信息
    @ResponseBody
    @RequestMapping(value = "teacher-update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateTeacher(Teacher teacher) {
        System.out.println(teacher);
        JSONObject jsonObject = new JSONObject();
        if (teacherMapper.updateTeacherByAdmin(teacher)) {
            jsonObject.put("code", "200");
        } else {
            jsonObject.put("code", "500");
        }
        return jsonObject.toString();
    }


    //获取开始与截止时间
    @ResponseBody
    @RequestMapping(value = "time-list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String listTime() {

        JSONObject jsonObject = new JSONObject();
        Time time = timeMapper.getTime();
        System.out.println(time);
        jsonObject.put("msg", time);
        return jsonObject.toString();
    }

    //添加开始与截止日期
    @ResponseBody
    @RequestMapping(value = "time-add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addTime(String startTime, String endTime) {
        System.out.println(startTime + ".." + endTime);
        JSONObject jsonObject = new JSONObject();
        if (timeMapper.deleteTime()) {
            if (timeMapper.addTime(startTime, endTime)) {
                jsonObject.put("msg", "修改成功");
            } else {
                jsonObject.put("msg", "修改失败");
            }
        } else {
            jsonObject.put("msg", "删除失败");
        }


        return jsonObject.toString();
    }

    //修改管理员头像
    @ResponseBody
    @RequestMapping(value = "upImage-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String updateImage(MultipartFile pic, String id) {
        File fileOne = null;
        byte[] photoOne = null;
        //文件名,不包含后缀名
        String fileName = pic.getOriginalFilename();
        //后缀名
        String prefixOne = fileName.substring(fileName.lastIndexOf("."));
        try {
            //将pic转化为file
            fileOne = File.createTempFile(fileName, prefixOne);
            pic.transferTo(fileOne);

            //再将file转化为byte[]
            FileInputStream fis = new FileInputStream(fileOne);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            photoOne = baos.toByteArray();
            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();

        if (adminMapper.updateImage(photoOne, id)) {
            jsonObject.put("msg", "修改成功");
        } else {
            jsonObject.put("msg", "修改失败");
        }

        return jsonObject.toString();
    }

    //志愿分配核心算法
    @ResponseBody
    @RequestMapping(value = "assign-student-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String assignStudentByAdmin() {
        JSONObject jsonObject = new JSONObject();
        //先删除voulunteerResult里的所有数据
        volunteerResultMapper.deleteVolunteerResult();
        //查询出所有的老师
        List<Teacher> teachers = teacherMapper.querryAllTeacher();
        //n代表志愿顺序,先查一志愿，再查二志愿，依次循环
        for (int n = 1; n <= teachers.size(); n++) {
            //循环老师的list,查找出每一个老师
            for (int i = 0; i < teachers.size(); i++) {
                Teacher teacher = teachers.get(i);
                //老师的指导人数
                int count = teacher.getCount();
                //查询出volunteerResult表中该老师已添加的学生数量
                int studentCount = volunteerResultMapper.studentCount(teacher.getId());
                //判断该老师的volunteerResult表中添加的学生人数是否已达上限,如果达到上限就退出该老师的循环
                if (studentCount < count) {
                    List<Volunteer> volunteers = volunteerMapper.querryAllStudentByTeacherIdAndOrder(teacher.getId(), n);
                    //判断volunteers的大小是否比老师还缺的学生人数(count-student)小
                    if (volunteers.size() < (count - studentCount)) {
                        //volunteers的大小=循环次数
                        for (int m = 0; m < volunteers.size(); m++) {
                            //向volunteerResult表中添加最终结果
                            volunteerResultMapper.addVolunteerResult(volunteers.get(m));
                            //该同学的志愿最终结果添加成功后,在volunteer表中删除该同学的所有志愿信息
                            volunteerMapper.deleteAllVolunteerByStudent(volunteers.get(m).getStudentId());
                        }
                    } else {
                        //老师能指导的学生人数-已指导的学生人数=循环次数
                        for (int m = 0; m < (count - studentCount); m++) {
                            //向volunteerResult表中添加最终结果
                            volunteerResultMapper.addVolunteerResult(volunteers.get(m));
                            //该同学的志愿最终结果添加成功后,在volunteer表中删除该同学的所有志愿信息
                            volunteerMapper.deleteAllVolunteerByStudent(volunteers.get(m).getStudentId());
                        }
                    }
                }
            }
        }
        jsonObject.put("msg", "添加");
        return jsonObject.toString();
    }

    //查询出volunteer表中是否有数据
    @ResponseBody
    @RequestMapping(value = "querry-volunteer-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String querryVolunteerByAdmin() {
        JSONObject jsonObject = new JSONObject();
        Integer integer = volunteerMapper.querryCount();
        if (integer > 0) {
            jsonObject.put("code", "200");
        } else {
            jsonObject.put("code", "500");
        }
        return jsonObject.toString();
    }

    //删除volunteer表中的所有数据
    @ResponseBody
    @RequestMapping(value = "delete-volunteer-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String deleteVolunteerByAdmin() {
        JSONObject jsonObject = new JSONObject();
        volunteerMapper.deleteVolunteer();
        return jsonObject.toString();
    }

    //删除volunteerResult表中的所有数据
    @ResponseBody
    @RequestMapping(value = "delete-volunteerResult-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String deleteVolunteerResultByAdmin() {
        JSONObject jsonObject = new JSONObject();

        volunteerResultMapper.deleteVolunteerResult();
        return jsonObject.toString();
    }

    //通过学生id查看该学生自己已填报的志愿
    @ResponseBody
    @RequestMapping(value = "querry-volunteerByStudent-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String querryVolunteerById(String studentId) {
        System.out.println(studentId);
        JSONObject jsonObject = new JSONObject();
        List<Volunteer> volunteers = volunteerMapper.querryVolunteerById(studentId);
        if (volunteers.size()==0){
            jsonObject.put("code","500");
        }else {
            jsonObject.put("code","200");
            jsonObject.put("msg",volunteers);
        }

        return jsonObject.toString();
    }
    //通过老师id查询老师的信息
    @ResponseBody
    @RequestMapping(value = "querry-teacherId-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String querryTeacherIdByAdmin(String id) {
        JSONObject jsonObject = new JSONObject();
        Teacher teacher = teacherMapper.querryTeacherById(id);
        jsonObject.put("msg",teacher);
        return jsonObject.toString();
    }
    //判断老师的总指导人数是否超过学生总人数
    @ResponseBody
    @RequestMapping(value = "compare-count-admin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String compareCountByAdmin() {
        JSONObject jsonObject = new JSONObject();

        List<Teacher> teachers = teacherMapper.querryAllTeacher();
        int teacherCount=0;
        for (int i=0;i<teachers.size();i++){
            teacherCount+=teachers.get(i).getCount();
        }
        int studentCount = studentMapper.getCount();
        if ((teacherCount-studentCount)<0){
            jsonObject.put("code","500");
            jsonObject.put("msg",studentCount-teacherCount);
        }else {
            jsonObject.put("code","200");
        }
        return jsonObject.toString();
    }
}