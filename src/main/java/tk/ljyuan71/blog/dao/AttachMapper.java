package tk.ljyuan71.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.ljyuan71.blog.model.Attach;
import tk.ljyuan71.blog.model.AttachExample;

import java.util.List;

@Component
public interface AttachMapper {
    long countByExample(AttachExample example);

    int deleteByExample(AttachExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attach record);

    int insertSelective(Attach record);

    List<Attach> selectByExample(AttachExample example);

    Attach selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attach record, @Param("example") AttachExample example);

    int updateByExample(@Param("record") Attach record, @Param("example") AttachExample example);

    int updateByPrimaryKeySelective(Attach record);

    int updateByPrimaryKey(Attach record);
}