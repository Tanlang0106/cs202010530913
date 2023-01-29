package tl.mapper;

import tl.pojo.Volunteer;
import tl.pojo.VolunteerResult;

import java.util.List;

public interface VolunteerResultMapper {
    //添加志愿
    public boolean addVolunteerResult(Volunteer volunteer);
    //删除所有志愿
    public boolean deleteVolunteerResult();
    //通过老师id查找已指导学生的数量
    public Integer studentCount(String teacherId);
    //通过学生id查询志愿
    public VolunteerResult querryMessage(String student);
    //通过老师id查询志愿结果
    public List<VolunteerResult> querryVolunteerResult(String teacherId);

}
