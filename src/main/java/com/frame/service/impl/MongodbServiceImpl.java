package com.frame.service.impl;

import com.frame.exception.NullIdException;
import com.frame.service.MongodbService;
import com.frame.utils.ReflectUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuming on 2019/7/30.
 */
@Service
public class MongodbServiceImpl<T> implements MongodbService<T> {
    @Autowired
    MongoOperations mongoTemplate;


    /**
     * * 修改</br>
     * *
     * * @return null
     */
    public void update(T entity) throws NullIdException {
        boolean falg = ReflectUtil.reflectObject(entity);
        if (!falg) {
            throw new NullIdException("_ID is null");
        }
        mongoTemplate.save(entity);
    }


    /**
     * * 添加或者修改（ALL）</br>
     * * 如果有_ID值则为修改，如果没有_ID值则为添加</br>
     * *
     * * @return null
     */
    public void saveOrUpdate(T entity) {
        mongoTemplate.save(entity);
    }


    /**
     * 添加
     */
    public void save(final T entity) {
        mongoTemplate.insert(entity);
    }


    /**
     * * 查询全部
     * *
     * * @param entity 查询实体 </br>
     * *      因为MongoDB具体要查询的document是设置在实体类的,所以需要传具体的实体类
     * * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(T entity) {
        return (List<T>) mongoTemplate.findAll(entity.getClass());
    }


    /**
     * * 根据_ID查询
     * *
     * * @param entity 查询实体 </br>
     * *      因为MongoDB具体要查询的document是设置在实体类的,所以需要传具体的实体类
     * * @return
     */
    @SuppressWarnings("unchecked")
    public T findById(String id, T entity) {
        return (T) mongoTemplate.findOne(new Query(Criteria.where("_id").is(new ObjectId(id))),
                entity.getClass());
    }


    /**
     * * 根据_ID删除
     * *
     * * @param id 编码</br>
     * * @param entity 查询实体</br>
     * *      因为MongoDB具体要查询的document是设置在实体类的,所以需要传具体的实体类 </br>
     * * @return null </br>
     */
    public void deleteById(String id, T entity) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(new ObjectId(id))), entity.getClass());
    }


    /**
     * * 根据_ID修改某一个键值队</br>
     * *
     * * @param id 编码</br>
     * * @param key 键</br>
     * * @param value 值</br>
     * * @param entity 操作实体</br>
     * *      因为MongoDB具体要查询的document是设置在实体类的,所以需要传具体的实体类 </br>
     * * @return null
     */
    public void updateById(String id, String key, Object value, T entity) {
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(new ObjectId(id))),
                Update.update(key, value), entity.getClass());
    }
}
