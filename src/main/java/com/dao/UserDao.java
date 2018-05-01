package com.dao;

import com.entity.User;
import com.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * 针对于User 访问数据库
 */
public class UserDao{
    private static final String TABLENAME = " reader_info " ;

    /**
     * 根据id 加载User对象
     * @param id
     * @return 一个User 对象
     */
    public User loadById( String id ){
        String SQL = "select reader_id,name,passwd,telcode from " + TABLENAME + " where reader_id = ? " ;
        ResultSet rs = DBHelper.query( SQL , id ) ;
        User u = wrap( rs ) ;
        return u ;
    }
    public boolean add(User u) {
        String SQL = "insert into " + TABLENAME  + "(reader_id,name,passwd,telcode) values ( ? , ? , ? , ? ) " ;
        return DBHelper.execute(SQL , u.getReader_id() , u.getName() , u.getPasswd() , u.getTelcode() ) ;
    }

    /**
     * 根据用户名加载一个用户对象
     * @param username 用户名
     * @return 如果查询到了对应的 数据，返回User对象
     */
    public User loadByUsername( String username ){
        User u = null ;
        // 利用DBHelper 查询数据库
        String SQL = " select reader_id,name,passwd,telcode from " + TABLENAME + " where name = ? " ;
        ResultSet rs = DBHelper.query( SQL , username ) ;
        u = wrap( rs ) ;
        return u ;
    }
    public User wrap( ResultSet rs ){
        User u = null ;
        try {
            if(rs.next()){
                u = new User();
                u.setReader_id( rs.getString("reader_id") ) ;
                u.setName( rs.getString("name") ) ;
                u.setPasswd( rs.getString("passwd") ) ;
                u.setTelcode( rs.getString("telcode") ) ;
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
        return u ;
    }
}
