package com.dao;

import com.entity.User;
import com.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String SQL = " select reader_id,name,passwd,sex,birth,address,telcode from " + TABLENAME + " where name = ? " ;
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

    public User loadByName(String name) {
        String SQL = "select reader_id,name,passwd,sex,birth,address,telcode from " + TABLENAME + " where name = ? ";
        System.out.println(name);
        ResultSet rs = DBHelper.query(SQL, name);
        List<User> list = wrapList(rs);
        /*for (user list1:list) {
            System.out.println("以下为结果集");
            System.out.println(list1);
        }*/
        return list.get(0);
    }

    public boolean updateu(String password,String id) {
        String SQL = "UPDATE " + TABLENAME + "set passwd = ?  where reader_id = ? " ;
        return DBHelper.execute(SQL , password ,  id )  ;
    }

    public List loadAll() {
        String SQL = "select reader_id,name,passwd,sex,birth,address,telcode from" + TABLENAME;
        ResultSet rs = DBHelper.query(SQL);
        return wrapList(rs);
    }

    public List<User> wrapList(ResultSet rs) {
        List<User> list = new ArrayList<>();
        try {
            while(rs.next()){
                User u = new User();
                u.setReader_id(rs.getString("reader_id"));
                u.setName(rs.getString("name"));
                u.setPasswd(rs.getString("passwd"));
                u.setSex(rs.getString("sex"));
                u.setBirth(rs.getString("birth"));
                u.setAddress(rs.getString("address"));
                u.setTelcode(rs.getString("telcode"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
