package tl.mapper;

import org.apache.ibatis.annotations.Param;
import tl.pojo.Admin;
import tl.pojo.Time;

import java.util.List;

public interface AdminMapper {
    //查询管理员账号是否存在
    public Integer querryAdminExist(@Param("id") String id, @Param("password")String password);
    //通过id查询管理员信息
    public Admin querryAdmin(String id);
    //修改管理员头像
    public boolean updateImage(@Param("image") byte[] image,@Param("id") String id);

}
