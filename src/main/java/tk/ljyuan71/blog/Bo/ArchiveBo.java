package tk.ljyuan71.blog.Bo;


import java.io.Serializable;
import java.util.List;

import tk.ljyuan71.blog.model.Content;

public class ArchiveBo implements Serializable {

    private String date;
    private String count;
    private List<Content> articles;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Content> getArticles() {
        return articles;
    }

    public void setArticles(List<Content> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Archive [" +
                "date='" + date + '\'' +
                ", count='" + count + '\'' +
                ", articles=" + articles +
                ']';
    }
}
