package com.as.common.dao;

import com.as.common.entity.BaseEntity;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Query;
import org.hibernate.criterion.Projection;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Newbody on 2016/2/25.
 */
public interface GenericDao<E extends BaseEntity, PK extends Serializable> {
    Class<E> getEntityClass();

//    String getEntityName();
    public void save(E entity);
    public void save(Collection<E> entities);
    public void saveOrUpdate(E entity);
    public void saveOrUpdate(Collection<E> entities);

    public void delete(PK id);
//    public Boolean delete(String propertyName, Object value);
//    public Boolean delete(Criterion... criterion);

    public void update(E entity);
    public void update(Collection<E> entities);
//    public Boolean updateById(String id, String propertyName, Object propertyValue);
//    public Boolean updateById(String id, String propertyNames[], Object propertyValues[]);
//    public Boolean updateById(String id, Map<String, Object> valueMap);
//    public Boolean update(Map<String, Object> valueMap, Criterion... criterion);

//    public Boolean createOrUpdate(E entity);
//    public Boolean createOrUpdate(Collection<E> entities);
//    public Boolean merge(E entity);
//    public Boolean merge(Collection<E> entities);
//    public Boolean delete(E entity);
//    public Boolean delete(Collection<E> entities);
//    public Boolean bulkDelete(Class<E> clazz, String[] propertyNames, Object[] values);
//    public Boolean refresh(Object entity);
//    public List<E> findAll();
//    public List<E> findByOrder(String orderPropertyName, SQLOrderMode orderMode);

    public E getById(PK id);
    public E get(Conditions conditions);
    public List<E> getList();
    public List<E> getList(Conditions conditions);
    public List<E> getList(Query query);
    public PageHandler getPage(Query query);
    public <T> T get(Projection projection, Class<T> type);
    public <T> T get(Projection projection, Class<T> type, Conditions conditions);

}
