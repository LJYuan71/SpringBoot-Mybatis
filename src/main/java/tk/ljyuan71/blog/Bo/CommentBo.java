package tk.ljyuan71.blog.Bo;


import tk.ljyuan71.blog.model.Comment;

import java.util.List;

public class CommentBo extends Comment {

    private int levels;
    private List<Comment> children;

    public CommentBo(Comment comments) {
        setAuthor(comments.getAuthor());
        setMail(comments.getMail());
        setCoid(comments.getCoid());
        setAuthorId(comments.getAuthorId());
        setUrl(comments.getUrl());
        setCreated(comments.getCreated());
        setAgent(comments.getAgent());
        setIp(comments.getIp());
        setContent(comments.getContent());
        setOwnerId(comments.getOwnerId());
        setCid(comments.getCid());
    }

    public int getLevels() {
        return levels;
    }

    public List<Comment> getChildren() {
        return children;
    }

}
