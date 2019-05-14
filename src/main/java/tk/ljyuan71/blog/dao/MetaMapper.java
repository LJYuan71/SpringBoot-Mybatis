package tk.ljyuan71.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.ljyuan71.blog.dto.MetaDto;
import tk.ljyuan71.blog.model.Meta;
import tk.ljyuan71.blog.model.MetaExample;

import java.util.List;
import java.util.Map;

@Component
public interface MetaMapper {
    long countByExample(MetaExample example);

    int deleteByExample(MetaExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(Meta record);

    int insertSelective(Meta record);

    List<Meta> selectByExample(MetaExample example);

    Meta selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") Meta record, @Param("example") MetaExample example);

    int updateByExample(@Param("record") Meta record, @Param("example") MetaExample example);

    int updateByPrimaryKeySelective(Meta record);

    int updateByPrimaryKey(Meta record);

    List<MetaDto> selectFromSql(Map<String, Object> paraMap);

    MetaDto selectDtoByNameAndType(@Param("name") String name, @Param("type") String type);

    Integer countWithSql(Integer mid);
}