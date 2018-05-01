package com.utils;

import com.entity.ReaderInfo;

import java.sql.*;

public class JdbcMysql {
    private static Connection conn=null;
   private static PreparedStatement ps=null;

    /*
    加载和建立连接
     */
    public static Connection getconn() {


             String username="root";
             String password="";
             String url="jdbc:mysql://127.0.0.1:3306/library";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn= DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        return conn;
    }
/*
创建PreparedStatement对象
 */
    public static PreparedStatement CreatePs(String SQL){
        conn=getconn();

        try {
            ps=conn.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

   return  ps;
    }
    /*
    向读者表中添加数据
     */
    public static  void InsertTable( ReaderInfo user){
        String SQL="insert into reader_info values(?,?,?,?,?,?,?)";
        ps=CreatePs(SQL);
        try {
        ps.setObject(1,Integer.parseInt(user.getReader_id()));
        ps.setObject(2,user.getName());
        ps.setObject(3,user.getPasswd());
        ps.setObject(4,user.getSex());
        ps.setObject(5,DateTransform2.sToD(user.getBirth()));
        ps.setObject(6,user.getAddress());
        ps.setObject(7,user.getTelcode());
        ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    删除读者信息
     */
    public static  void deleteData(ReaderInfo user){
        String SQL="delete from reader_info where reader_id=? ";
        ps=CreatePs(SQL);
        try {
            ps.setObject(1,user.getReader_id());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    查询读者信息
     */
    public static  void selectData(ReaderInfo user){
        ResultSet rs=null;
        String SQL="select * from reader_info where reader_id=?";
        ps=CreatePs(SQL);
        try {
            ps.setObject(1,user.getReader_id());
            rs= ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
