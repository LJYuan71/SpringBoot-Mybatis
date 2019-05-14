package tk.ljyuan71.utils;

import java.util.List;

/**
 *  分页数据
 */
public class Pager<E> {

    /**
     * 每页显示几条记录
     */
    public static final String PAGE_LIST = "[ 10,20,50,100]";

    private Integer offset;// 下条记录开始
    private Integer limit;// 每页显示几条记录
    private Integer beginRum;
    private Integer endRum;
    private Integer count;
    private List<E> dataList;

    public Pager(Integer beginRum, Integer endRum) {
        this.beginRum = beginRum;
        this.endRum = endRum;
    }

    public Pager() {
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getBeginRum() {
        return beginRum;
    }

    public void setBeginRum(Integer beginRum) {
        this.beginRum = beginRum;
    }

    public Integer getEndRum() {
        return endRum;
    }

    public void setEndRum(Integer endRum) {
        this.endRum = endRum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<E> getDataList() {
        return dataList;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
