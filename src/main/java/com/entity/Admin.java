package com.entity;

import java.io.Serializable;

/**
 * 这个类与数据库中的t_user表对应
 * 其中这个类的字段与t_user表中的字段一一对应
 * @author Lujiapeng
 * @Date 2018-04-27
 */
public class Admin implements Serializable {
    // 与数据库中的字段 id 对应
    private String admin_id ;

    // 与数据库中的字段 password 对应
    private String password ;

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
