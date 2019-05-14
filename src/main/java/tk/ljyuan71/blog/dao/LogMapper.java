package tk.ljyuan71.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.ljyuan71.blog.model.Log;
import tk.ljyuan71.blog.model.LogExample;

import java.util.List;

@Component
public interface LogMapper {
    long countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}