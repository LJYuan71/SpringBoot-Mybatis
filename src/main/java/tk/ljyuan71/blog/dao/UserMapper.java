package tk.ljyuan71.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.ljyuan71.blog.model.User;
import tk.ljyuan71.blog.model.UserExample;

import java.util.List;

@Component
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}