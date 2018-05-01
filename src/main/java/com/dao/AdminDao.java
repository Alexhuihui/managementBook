package com.dao;

import com.entity.Admin;
import com.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 针对于Admin 访问数据库
 */
public class AdminDao implements  BaseDao<Admin> {
    private static final String TABLENAME = " admin " ;


    public Admin loadByAdmin_id(String admin_id ){
        String SQL = "select admin_id , password  from " + TABLENAME + " where admin_id = ? " ;
        ResultSet rs = DBHelper.query( SQL , admin_id ) ;
        Admin u = wrap( rs ) ;
        return u ;
    }
    @Override
    public boolean add(Admin u) {
       return false;
    }

    @Override
    public boolean update(Admin u) {
        return false;

    }

    @Override
    public boolean delete(Admin o) {
        return false;
    }

    @Override
    public List<Admin> loadAll() {
        return null;
    }

    public Admin wrap(ResultSet rs ){
        Admin u = null ;
        try {
            if(rs.next()){
                u = new Admin();
                u.setAdmin_id( rs.getString("Admin_id") ); ;
                u.setPassword( rs.getString("password") ) ;
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
        return u ;
    }
}
