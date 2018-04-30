package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbDao
{
    private Connection conn;
    private String driver;
    private String url;
    private String username;
    private String pass;

    public DbDao() {

    }

    public DbDao(String driver , String url
            , String username , String pass)
    {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.pass = pass;
    }
    // 下面是各个成员属性的setter和getter方法
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getDriver() {
        return (this.driver);
    }
    public String getUrl() {
        return (this.url);
    }
    public String getUsername() {
        return (this.username);
    }
    public String getPass() {
        return (this.pass);
    }


    // 插入记录
    public boolean insert(String sql , Object... args)
            throws Exception
    {
        this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length ; i++ )
        {
            pstmt.setObject( i + 1 , args[i]);
        }
        if (pstmt.executeUpdate() != 1)
        {
            return false;
        }
        pstmt.close();
        return true;
    }
    // 执行查询
    public ResultSet query(String sql , Object... args)
            throws Exception
    {
        this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length ; i++ )
        {
            pstmt.setObject( i + 1 , args[i]);
        }
        return pstmt.executeQuery();
    }
    // 执行修改
    public void modify(String sql , Object... args)
            throws Exception
    {
        this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length ; i++ )
        {
            pstmt.setObject( i + 1 , args[i]);
        }
        pstmt.executeUpdate();
        pstmt.close();
    }

    //执行删除
    public void delete(String sql , Object... args)
            throws Exception
    {
        this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length ; i++ )
        {
            pstmt.setObject( i + 1 , args[i]);
        }
        pstmt.executeUpdate();
        pstmt.close();
    }

    // 关闭数据库连接的方法
    public void closeConn() throws Exception
    {
        if (conn != null && !conn.isClosed())
        {
            conn.close();
        }
    }

    // 获取数据库连接
    public void getConnection() throws Exception
    {
        if (conn == null)
        {
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,username,pass);
        }
    }
}