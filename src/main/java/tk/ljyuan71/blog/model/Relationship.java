package tk.ljyuan71.blog.model;

import java.io.Serializable;

/**
 * @author 
 */
public class Relationship implements Serializable {
    private static final long serialVersionUID = -26254014814473766L;
    /**
     * 内容主键
     */
    private Integer cid;

    /**
     * 项目主键
     */
    private Integer mid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}