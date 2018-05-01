package com.dao;

import java.util.List;

/**
 * 定义一个公共的接口
 * @param <E>
 */
public interface BaseDao<E> {
    /**
     * 添加一个对象
     * @param o 传入的是一个对象
     * @return true 表示成功 false表示失败
     */
    boolean  add(E o);

    /**
     * 更新一个对象
     * @param o 传入的是一个对象
     * @return true表示更新成功，false表示更新失败
     */
    boolean update(E o) ;

    /**
     * 删除一个对象
     * @param o 传入的是一个对象
     * @return true表示更新成功，false表示更新失败
     */
    boolean delete(E o) ;

    /**
     * 获取所有的数据
     * @return 对象的List集合
     */
    List<E> loadAll() ;
}
