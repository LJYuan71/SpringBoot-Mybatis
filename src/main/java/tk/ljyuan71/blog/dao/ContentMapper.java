package tk.ljyuan71.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.ljyuan71.blog.Bo.ArchiveBo;
import tk.ljyuan71.blog.model.Content;
import tk.ljyuan71.blog.model.ContentExample;

import java.util.List;

@Component
public interface ContentMapper {
    long countByExample(ContentExample example);

    int deleteByExample(ContentExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExampleWithBLOBs(ContentExample example);

    List<Content> selectByExample(ContentExample example);

    Content selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExampleWithBLOBs(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);

    List<ArchiveBo> findReturnArchiveBo();

    List<Content> findByCatalog(Integer mid);
}