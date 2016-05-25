package com.as.common.service;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import org.hibernate.criterion.Projection;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Newbody on 2016/3/11.
 */
public interface GenericService<E, PK extends Serializable> {
    Class<E> getEntityClass();
    void save(E entity);
    void save(Collection<E> entities);
    void saveOrUpdate(E entity);
    void saveOrUpdate(Collection<E> entities);
    void delete(PK id);
    void delete(Collection<PK> ids);
    void update(E entity);
    void update(Collection<E> entities);
    E getById(PK id);
    E get(Conditions conditions);
    List<E> getList();
    List<E> getList(Conditions conditions);
    List<E> getList(Query query);
    PageHandler getPage(Query query);
    public <T> T get(Projection projection, Class<T> type);
}
