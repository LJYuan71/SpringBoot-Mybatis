package tk.ljyuan71.blog.service;

import com.github.pagehelper.PageInfo;
import tk.ljyuan71.blog.Bo.CommentBo;
import tk.ljyuan71.blog.model.Comment;
import tk.ljyuan71.blog.model.CommentExample;


/**
 * Created by BlueT on 2017/3/16.
 */
public interface ICommentService {

    /**
     * 保存对象
     * @param comment
     */
    String insertComment(Comment comment);

    /**
     * 获取文章下的评论
     * @param cid
     * @param page
     * @param limit
     * @return CommentBo
     */
    PageInfo<CommentBo> getComments(Integer cid, int page, int limit);

    /**
     * 获取文章下的评论
     * @param commentExample
     * @param page
     * @param limit
     * @return Comment
     */
    PageInfo<Comment> getCommentsWithPage(CommentExample commentExample, int page, int limit);


    /**
     * 根据主键查询评论
     * @param coid
     * @return
     */
    Comment getCommentById(Integer coid);


    /**
     * 删除评论，暂时没用
     * @param coid
     * @param cid
     * @throws Exception
     */
    void delete(Integer coid, Integer cid);

    /**
     * 更新评论状态
     * @param comments
     */
    void update(Comment comments);

}
