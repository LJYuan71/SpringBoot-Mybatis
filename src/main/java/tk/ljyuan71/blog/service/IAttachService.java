package tk.ljyuan71.blog.service;

import com.github.pagehelper.PageInfo;
import tk.ljyuan71.blog.model.Attach;;

/**
 * Created by wangq on 2017/3/20.
 */
public interface IAttachService {
    /**
     * 分页查询附件
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Attach> getAttachs(Integer page, Integer limit);

    /**
     * 保存附件
     *
     * @param fname
     * @param fkey
     * @param ftype
     * @param author
     */
    void save(String fname, String fkey, String ftype, Integer author);

    /**
     * 根据附件id查询附件
     * @param id
     * @return
     */
    Attach selectById(Integer id);

    /**
     * 删除附件
     * @param id
     */
    void deleteById(Integer id);
}
