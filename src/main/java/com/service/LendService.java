package com.service;

import com.dao.DbDao;
import com.entity.LendList;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Objects;

public class LendService {
    private DbDao lendDao;

    {
        this.lendDao = new DbDao("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","qwerty123");

    }

    /**
     * 还书
     * @return 如果还书成功返回true
     */
    public boolean update(long lendId){
        if(selectIsBack(lendId) == 1){
            return false;
        }
        String update_sql = "update lend_list set is_back = 1, back_date = ? where sernum = ?";
        try {
            lendDao.modify(update_sql, new Date(), lendId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public int selectIsBack(long sernum){
        String select_sql = "select is_back from lend_list where sernum = ?";
        int isBack = 0;
        try {
            ResultSet lends = lendDao.query(select_sql, sernum);
            if(lends.next()){
                isBack = lends.getInt("is_back");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isBack;
    }

    /**
     *查询表
     * @return 返回数据库中lend_list表中的所有数据所对应的对象集合
     */
    public List<LendList> select(){
        String select_sql = "select * from lend_list";
        List<LendList> lendArrayList = new ArrayList<>();
        try {
            ResultSet lends = lendDao.query(select_sql);
            while (lends.next()){
                long sernum = lends.getLong("sernum");
                long bookId = lends.getLong("book_id");
                int readerId = lends.getInt("reader_id");
                java.util.Date lendDate = lends.getDate("lend_date");
                java.util.Date backDate = lends.getDate("back_date");
                int isBack = lends.getInt("is_back");
                String readerName = selectReaderName(readerId);
                String bookName = selectBookName(bookId);
                LendList lend = this.wrap(sernum, bookId, readerId, lendDate, backDate, isBack, readerName, bookName);
                lendArrayList.add(lend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lendArrayList;
    }

    /**
     * 根据bookId来查询bookName
     * @param bookId
     * @return 返回对应的bookName
     */
    public String selectBookName(long bookId){
        String select_sql = "select name from book_info where book_id = ?";
        String bookName = null;
        bookName = getString(bookId, select_sql, bookName);

        return bookName;
    }

    private String getString(long bookId, String select_sql, String bookName) {
        try {
            ResultSet lends = lendDao.query(select_sql, bookId);
            if(lends.next()){
                bookName = lends.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookName;
    }

    /**
     * 根据readerId来查询readerName
     * @param readerId
     * @return 返回对应的readerName
     */
    public String selectReaderName(int readerId){
        String select_sql = "select name from reader_info where reader_id = ?";
        String readerName = null;
        readerName = getString(readerId, select_sql, readerName);

        return readerName;
    }

    /**
     * 根据传入的数据new一个LendList对象并返回
     * @return 返回一个LendList对象
     */
    public LendList wrap(long sernum, long bookId, int readerId, Date lendDate, Date backDate, int isBack, String readerName, String bookName){
        LendList lend = new LendList();

        lend.setSernum(sernum);
        lend.setBackDate(backDate);
        lend.setBookId(bookId);
        lend.setIsBack(isBack);
        lend.setLendDate(lendDate);
        lend.setReaderId(readerId);
        lend.setBookName(bookName);
        lend.setReaderName(readerName);
        if(Objects.nonNull(backDate) && isBack == 1){
            long count = compareDate(lendDate, backDate);
            if(count == 0){
                lend.setMessage("已还，你是一个守信用的人");
            }else {
                lend.setMessage("你超出还书期限" + count + "天了，罚你给图书馆做义务劳动" + count + "小时.");
            }
        }
        return lend;
    }

    /**
     * 90天为期限
     * @param lendDate 借书日期
     * @param backDate 还书日期
     * @return 如果没超过期限则返回-1，如果超出期限，超过一天多返回1
     */
    public long compareDate(Date lendDate, Date backDate){
        long time = backDate.getTime() - lendDate.getTime();
//        System.out.println("lendDate = " + lendDate);
//        System.out.println("backDate = " + backDate);
//        System.out.println("lendDate.getTime() = " + lendDate.getTime());
//        System.out.println("backDate.getTime() = " + backDate.getTime());


        long count = 0;
        long nd = 60*60*24*1000;
//        System.out.println("time = " + time);
//        System.out.println("deadLine = " + nd);
        if(time > nd*90){
            count = (time / nd) - 90;
//            System.out.println("count = " + count);
            if(count == 0){
                count = 1;
            }
        }
        return count;
    }
}
