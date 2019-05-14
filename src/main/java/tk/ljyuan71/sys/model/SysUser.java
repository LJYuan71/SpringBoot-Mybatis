/*
*
* Copyright(C) 2017-2020 XXX公司
* @date 2019-03-20
*/
package tk.ljyuan71.sys.model;

import java.io.Serializable;

public class SysUser implements Serializable {
    /** 主键 */
    private String id;

    /** 用户名称 */
    private String userName;

    /** 昵称 */
    private String nickName;

    /** 用户密码 */
    private String password;

    /** 性别:1男,2女 */
    private Short gender;

    /** 出生年月日 */
    private Long birthday;

    /** 联系电话 */
    private String phone;

    /** 联系邮箱 */
    private String email;

    /** 联系地址 */
    private String addr;

    /** 是否删除 */
    private Short isDelect;

    private static final long serialVersionUID = 1L;

    /** 获取: 主键 的值 */
    public String getId() {
        return id;
    }

    /** 设置: 主键 的值 */
    public void setId(String id) {
        this.id = id;
    }

    /** 获取: 用户名称 的值 */
    public String getUserName() {
        return userName;
    }

    /** 设置: 用户名称 的值 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 获取: 昵称 的值 */
    public String getNickName() {
        return nickName;
    }

    /** 设置: 昵称 的值 */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /** 获取: 用户密码 的值 */
    public String getPassword() {
        return password;
    }

    /** 设置: 用户密码 的值 */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 获取: 性别:1男,2女 的值 */
    public Short getGender() {
        return gender;
    }

    /** 设置: 性别:1男,2女 的值 */
    public void setGender(Short gender) {
        this.gender = gender;
    }

    /** 获取: 出生年月日 的值 */
    public Long getBirthday() {
        return birthday;
    }

    /** 设置: 出生年月日 的值 */
    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    /** 获取: 联系电话 的值 */
    public String getPhone() {
        return phone;
    }

    /** 设置: 联系电话 的值 */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 获取: 联系邮箱 的值 */
    public String getEmail() {
        return email;
    }

    /** 设置: 联系邮箱 的值 */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 获取: 联系地址 的值 */
    public String getAddr() {
        return addr;
    }

    /** 设置: 联系地址 的值 */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /** 获取: 是否删除 的值 */
    public Short getIsDelect() {
        return isDelect;
    }

    /** 设置: 是否删除 的值 */
    public void setIsDelect(Short isDelect) {
        this.isDelect = isDelect;
    }
}