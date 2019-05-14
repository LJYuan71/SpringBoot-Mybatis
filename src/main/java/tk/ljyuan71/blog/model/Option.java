package tk.ljyuan71.blog.model;

import java.io.Serializable;

/**
 * @author 
 */
public class Option implements Serializable {
    private static final long serialVersionUID = -4720504159008519174L;
    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置值
     */
    private String value;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}