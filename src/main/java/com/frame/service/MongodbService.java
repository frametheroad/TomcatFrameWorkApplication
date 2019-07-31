package com.frame.service;

import com.frame.exception.NullIdException;

import java.util.List;

/**
 * Created by wuming on 2019/7/30.
 */
public interface MongodbService<T> {
    void update(T entity) throws NullIdException;

    /**
     * 添加或者修改（ALL）</br>
     * 如果有_ID值则为修改，如果没有_ID值则为添加</br>
     *
     * @return null
     */
    void saveOrUpdate(T entity);

    /**
     * 添加</br>
     *
     * @return null
     */
    void save(T entity);

    /**
     * 查询全部</br>
     *
     * @param entity 查询实体 </br>
     *               因为MongoDB具体要查询的Document是设置在实体类的,所以需要传具体的实体类</br>
     * @return java.util.List </br>
     */
    List<T> findAll(T entity);

    /**
     * 根据_ID查询</br>
     *
     * @param id     编码</br>
     * @param entity 查询实体 </br>
     *               因为MongoDB具体要查询的document是设置在实体类的,所以需要传具体的实体类
     * @return entity </br>
     */
    T findById(String id, T entity);

    /**
     * 根据_ID删除
     *
     * @param id     编码</br>
     * @param entity 查询实体</br>
     *               因为MongoDB具体要查询的document是设置在实体类的,所以需要传具体的实体类 </br>
     * @return null </br>
     */
    void deleteById(String id, T entity);

    /**
     * 根据_ID修改某一个键值队</br>
     *
     * @param id     编码</br>
     * @param key    键</br>
     * @param value  值</br>
     * @param entity 操作实体</br>
     *               因为MongoDB具体要查询的document是设置在实体类的,所以需要传具体的实体类 </br>
     * @return null
     */
    void updateById(String id, String key, Object value, T entity);
}
