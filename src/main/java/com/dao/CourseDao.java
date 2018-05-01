package com.dao;

import com.entity.Course;
import com.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements  BaseDao<Course>{
    private static final String TABLENAME = " book_info " ;
    public Course loadById(String book_id ){
        String SQL = " select book_id ,name , author ,  publish , ISBN , introduction , language , price , pubdate , class_id , number from " + TABLENAME + " where book_id = ? " ;
        ResultSet rs = DBHelper.query( SQL , book_id ) ;
        List<Course> list = wrap( rs ) ;
        return list.get(0) ;
    }
    @Override
    public boolean add(Course c) {
        String SQL = "insert into " + TABLENAME + " values ( ? ,? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
        return DBHelper.execute(SQL , c.getBook_id() , c.getName() , c.getAuthor() , c.getPublish() , c.getISBN() , c.getIntroduction() , c.getLanguage() , c.getPrice() , c.getPubdate() , c.getClass_id() , c.getNumber());
    }

    @Override
    public boolean update(Course c) {
        String SQL = "update  " + TABLENAME + " set name = ? ,  author= ? , publish = ? , ISBN = ?, introduction = ?, language = ?, price = ?, pubdate = ?, class_id = ?, number = ? where book_id = ?  ";
        return DBHelper.execute(SQL , c.getName() , c.getAuthor() , c.getPublish() , c.getISBN() , c.getIntroduction() , c.getLanguage() , c.getPrice() , c.getPubdate() , c.getClass_id() , c.getNumber() ,c.getBook_id() );
    }

    @Override
    public boolean delete(Course c) {
        String SQL = "delete from " + TABLENAME + " where book_id = ? " ;
        return DBHelper.execute(SQL , c.getBook_id() );
    }

    @Override
    public List<Course> loadAll() {
        String SQL = "select book_id ,name , author ,  publish , ISBN , introduction , language , price , pubdate , class_id , number from  " + TABLENAME  ;
        ResultSet rs = DBHelper.query(SQL ) ;
        return  wrap( rs ) ;
    }

    public List<Course> wrap(ResultSet rs ){
        List<Course> list = new ArrayList<>() ;
        try {
            while( rs.next() ){
               Course c = new Course() ;
                c.setBook_id( rs.getString("book_id"));
                c.setName( rs.getString("name"));
                c.setAuthor( rs.getString("author"));
                c.setPublish( rs.getString("Publish"));
                c.setISBN( rs.getString("ISBN"));
                c.setIntroduction( rs.getString("Introduction"));
                c.setLanguage( rs.getString("Language"));
                c.setPrice( rs.getString("Price"));
                c.setPubdate( rs.getString("Pubdate"));
                c.setClass_id( rs.getString("Class_id"));
                c.setNumber(rs.getString("NUMBER"));

                list.add( c ) ;
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
        return list ;
    }
}
