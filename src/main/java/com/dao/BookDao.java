package com.dao;

import com.entity.book;
import com.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements BaseDao<book>{

    private static final String TABLENAME = " lend_list ";

    public book loadByid(String id) {
        String SQL ="select sernum, book_id,reader_id ,lend_date,back_date, is_back from" + TABLENAME + "  where reader_id  =  ?  " ;
        ResultSet rs = DBHelper.query(SQL, id);
        try {
            if(!rs.next()){
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<book> list = wrap(rs);
        /*for (user list1:list) {
            System.out.println("以下为结果集");
            System.out.println(list1);
        }*/
        return list.get(0);
    }


    @Override
    public boolean add(book b) {
        return false;
    }

    @Override
    public boolean update(book b) {
        return false;
    }

    @Override
    public boolean delete(book b) {
        return false;
    }

    @Override
    public List<book> loadAll() {
        return null;
    }

    public List<book> wrap(ResultSet rs) {
        List<book> list = new ArrayList<>();
        try {
            while (rs.next()){
                book b = new book();
            b.setSernum(rs.getString("sernum"));
            b.setBid(rs.getString("book_id"));
            b.setRid(rs.getString("reader_id"));
            b.setLdate(rs.getString("lend_date"));
            b.setBdate(rs.getString("back_date"));
            b.setIback(rs.getString("is_back"));
            list.add(b);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
