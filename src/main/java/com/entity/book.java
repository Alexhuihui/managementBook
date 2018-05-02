package com.entity;

import java.io.Serializable;

public class book implements Serializable{
    // 与数据库中的字段 sernum 对应
    private String sernum ;
    // 与数据库中的字段 book_id 对应
    private String bid ;
    // 与数据库中的字段 reader_id 对应
    private String rid ;
    // 与数据库中的字段 lend_date 对应
    private String ldate ;
    // 与数据库中的字段 backdate 对应
    private String bdate;
    // 与数据库中的字段 isback 对应
    private String iback;

    public String getSernum() {
        return sernum;
    }

    public void setSernum(String sernum) {
        this.sernum = sernum;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getLdate() {
        return ldate;
    }

    public void setLdate(String ldate) {
        this.ldate = ldate;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getIback() {
        return iback;
    }

    public void setIback(String iback) {
        this.iback = iback;
    }
}
