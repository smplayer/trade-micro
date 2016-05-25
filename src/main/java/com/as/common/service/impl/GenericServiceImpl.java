package com.as.common.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.entity.BaseEntity;
import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.common.service.GenericService;
import org.hibernate.criterion.Projection;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Newbody on 2016/3/11.
 */
@Transactional
public abstract class GenericServiceImpl<E extends BaseEntity, PK extends Serializable> implements GenericService<E, PK> {

    protected abstract GenericDao<E, PK> getDao();
    protected Class<E> entityClass;

    public GenericServiceImpl() {
        Class c = getClass();
        ParameterizedType ptype = null;
        do { // 遍历所有超类，直到找泛型定义
            try {
                ptype = (ParameterizedType) c.getGenericSuperclass();
            } catch (Exception e) {
            }
            c = c.getSuperclass();
        } while (ptype == null && c != null);
        if (ptype == null) {
            System.out.println("子类中没有定义泛型的具体类型");
        } else {
            this.entityClass = (Class<E>) ptype.getActualTypeArguments()[0];
        }
    }

    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    @Override
    public void save(E entity) {
        entity.setCreatedTime(new Date().getTime());
        getDao().save(entity);
    }

    @Override
    public void save(Collection<E> entities) {
        for (E e : entities){
            save(e);
        }
    }

    @Override
    public void saveOrUpdate(E entity) {
        if (entity.getCreatedTime() == null)
            entity.setCreatedTime(new Date().getTime());
        else
            entity.setLastUpdatedTime(new Date().getTime());
        getDao().saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdate(Collection<E> entities) {
        for (E e : entities){
            saveOrUpdate(e);
        }
    }

    @Override
    public void delete(PK id) {
        getDao().delete(id);
    }

    @Override
    public void delete(Collection<PK> ids) {
        for (PK id : ids){
            delete(id);
        }
    }

    @Override
    public void update(E entity) {
        entity.setLastUpdatedTime(new Date().getTime());
        getDao().update(entity);
    }

    @Override
    public void update(Collection<E> entities) {
        for (E e : entities){
            update(e);
        }
    }

    @Override
    public E getById(PK id) {
        return getDao().getById(id);
    }

    @Override
    public E get(Conditions conditions) {
        return getDao().get(conditions);
    }

    @Override
    public List<E> getList() {
        return getDao().getList();
    }

    @Override
    public List<E> getList(Conditions conditions) {
        return getDao().getList(conditions);
    }

    @Override
    public List<E> getList(Query query) {
        return getDao().getList(query);
    }

    @Override
    public PageHandler getPage(Query query) {
        return getDao().getPage(query);
    }

    @Override
    public <T> T get(Projection projection, Class<T> type) {
        return getDao().get(projection, type);
    }
}
