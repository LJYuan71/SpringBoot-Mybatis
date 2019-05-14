package tk.ljyuan71.blog.service;


import com.github.pagehelper.PageInfo;
import tk.ljyuan71.blog.model.Content;
import tk.ljyuan71.blog.model.ContentExample;

/**
 * Created by Administrator on 2017/3/13 013.
 */
public interface IContentService {

//    /**
//     * 保存文章
//     * @param content content
//     */
//    void insertContent(Content content);

    /**
     * 发布文章
     * @param contents
     */
    String publish(Content contents);

    /**
     *查询文章返回多条数据
     * @param p 当前页
     * @param limit 每页条数
     * @return Content
     */
    PageInfo<Content> getContents(Integer p, Integer limit);


    /**
     * 根据id或slug获取文章
     *
     * @param id id
     * @return Content
     */
    Content getContents(String id);

    /**
     * 根据主键更新
     * @param content content
     */
    void updateContentByCid(Content content);


    /**
     * 查询分类/标签下的文章归档
     * @param mid mid
     * @param page page
     * @param limit limit
     * @return Content
     */
    PageInfo<Content> getArticles(Integer mid, int page, int limit);

    /**
     * 搜索、分页
     * @param keyword keyword
     * @param page page
     * @param limit limit
     * @return Content
     */
    PageInfo<Content> getArticles(String keyword, Integer page, Integer limit);


    /**
     * @param commentExample
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Content> getArticlesWithpage(ContentExample commentExample, Integer page, Integer limit);
    /**
     * 根据文章id删除
     * @param cid
     */
    String deleteByCid(Integer cid);

    /**
     * 编辑文章
     * @param contents
     */
    String updateArticle(Content contents);


    /**
     * 更新原有文章的category
     * @param ordinal
     * @param newCatefory
     */
    void updateCategory(String ordinal, String newCatefory);
}
