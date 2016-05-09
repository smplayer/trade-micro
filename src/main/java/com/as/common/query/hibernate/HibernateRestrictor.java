package com.as.common.query.hibernate;

import com.as.common.query.Restrictor;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Newbody on 2016/3/11.
 */
public class HibernateRestrictor implements Restrictor {


    private List<Criterion> criterionEntries = new ArrayList<>();
    private List<Order> orderEntries = new ArrayList<>();

    public List<Criterion> getCriterionEntries() {
        return criterionEntries;
    }

    public List<Order> getOrderEntries() {
        return orderEntries;
    }

    @Override
    public Restrictor eq(String propertyName, Object value) {
        criterionEntries.add(Restrictions.eq(propertyName, value));
        return this;
    }

    @Override
    public Restrictor ne(String propertyName, Object value) {
        criterionEntries.add(Restrictions.ne(propertyName, value));
        return this;
    }

    @Override
    public Restrictor in(String propertyName, Collection<?> value) {
        criterionEntries.add(Restrictions.in(propertyName, value));
        return this;
    }

    @Override
    public Restrictor asc(String propertyName) {
        orderEntries.add(Order.asc(propertyName));
        return this;
    }

    @Override
    public Restrictor desc(String propertyName) {
        orderEntries.add(Order.desc(propertyName));
        return this;
    }
}
