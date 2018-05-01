package com.entity;

import java.io.Serializable;
import java.sql.Date;


public class User implements Serializable {
    private String reader_id ;
    private String name ;
    private String passwd ;
    private String sex ;
    private Date birth;
    private String address;
    private String telcode;

    public String getReader_id() {
        return reader_id;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelcode() {
        return telcode;
    }

    public void setTelcode(String telcode) {
        this.telcode = telcode;
    }
}
