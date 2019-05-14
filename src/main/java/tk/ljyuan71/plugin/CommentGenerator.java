package tk.ljyuan71.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentGenerator extends DefaultCommentGenerator {


    /**
     * 父类时间
     */
    private boolean suppressDate;

    /**
     * 父类所有注释
     */
    private boolean suppressAllComments;

    /**
     * 当前时间
     */
    private String currentDateStr;
    /**
     * 计算机用户名
     */
    private String userName = System.getProperty("user.name");

    public CommentGenerator() {
        super();
        suppressDate = false;
        suppressAllComments = false;
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    /**
     * Java类的类注释
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" ");
        sb.append(getDateString());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        innerClass.addJavaDocLine(" */");
    }

    /**
     * 为类添加注释
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(userName);
        sb.append(" ");
        sb.append(currentDateStr);
        innerClass.addJavaDocLine(" */");
    }


    /**
     * Mybatis的Mapper.xml文件里面的注释
     */
    @Override
    public void addComment(XmlElement xmlElement) {

    }

    /**
     *
     *@Title getDateString
     *@Description: 此方法返回格式化的日期字符串以包含在Javadoc标记中和XML注释。 如果您不想要日期，则可以返回null在这些文档元素中。
     *@Author fendo
     *@Date 2017年10月5日 下午3:45:58
     *@return
     *@throws
     */
    protected String getDateString() {
        String result = null;
        if (!suppressDate) {
            result = currentDateStr;
        }
        return result;
    }

    /**
     *
     *@Title addJavadocTag
     *@Description: 此方法为其添加了自定义javadoc标签。
     *@Author fendo
     *@Date 2017年10月5日 下午3:49:05
     *@param javaElement
     *@param markAsDoNotDelete
     *@throws
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }


    /**
     * 为枚举添加注释
     */
    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    /**
     * Java属性注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    /**
     * 去掉XXXMapper.java里面的接口方法的注释
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    /**
     * 为字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        System.out.println(introspectedTable.getFullyQualifiedTable()+" "+ field.getName()+" "+introspectedColumn.getRemarks());
        String remarks = introspectedColumn.getRemarks();
        if(remarks == null || "".equals(remarks.trim())){
            remarks = field.getName();
        }
        field.addJavaDocLine("/** " + remarks + " */");
    }

    /**
     * 给getter方法加注释
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        String remarks = introspectedColumn.getRemarks();
        if(remarks == null || "".equals(remarks.trim())){
            remarks = introspectedColumn.getActualColumnName();
        }
        method.addJavaDocLine("/** 获取: " + remarks+ " 的值 */");
    }

    /**
     * 给setter方法加注释
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        String remarks = introspectedColumn.getRemarks();
        if(remarks == null || "".equals(remarks.trim())){
            remarks = introspectedColumn.getActualColumnName();
        }
        method.addJavaDocLine("/** 设置: " + remarks + " 的值 */");
    }

    /**
     * 给Java文件加注释，这个注释是在文件的顶部，也就是package上面。
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        compilationUnit.addFileCommentLine("/*");
        compilationUnit.addFileCommentLine("*");
        compilationUnit.addFileCommentLine("* Copyright(C) 2017-2020 XXX公司");
        compilationUnit.addFileCommentLine("* @date "+sdf.format(new Date())+"");
        compilationUnit.addFileCommentLine("*/");
    }

    /**
     * 为模型类添加注释
     */
    @Override
    public void addModelClassComment(TopLevelClass arg0, IntrospectedTable arg1) {

    }

    /**
     * 为调用此方法作为根元素的第一个子节点添加注释。
     */
    @Override
    public void addRootComment(XmlElement arg0) {

    }


}
