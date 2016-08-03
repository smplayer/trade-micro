package com.as.common.query.hibernate;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Newbody on 2016/3/11.
 */
public class Conditions {
    public static Conditions newInstance(){
        return new Conditions();
    }

    private List<Criterion> criterionList = new ArrayList<>();

    public List<Criterion> getCriterionList() {
        return criterionList;
    }

    public Conditions or(Conditions conditions){
        Disjunction disjunction = Restrictions.disjunction();
        List<Criterion> criterions = conditions.getCriterionList();
        for (Criterion criterion : criterions){
            disjunction.add(criterion);
        }
        criterionList.add(disjunction);
        return this;
    }

    public Conditions eq(String propertyName, Object value) {
        criterionList.add(Restrictions.eq(propertyName, value));
        return this;
    }

    public Conditions ne(String propertyName, Object value) {
        criterionList.add(Restrictions.ne(propertyName, value));
        return this;
    }

    public Conditions neOrIsNotNull(String propertyName, Object value) {
        criterionList.add(Restrictions.neOrIsNotNull(propertyName, value));
        return this;
    }

    public Conditions isNull(String propertyName) {
        criterionList.add(Restrictions.isNull(propertyName));
        return this;
    }

    public Conditions like(String propertyName, Object value) {
        criterionList.add(Restrictions.like(propertyName, value));
        return this;
    }

    public Conditions in(String propertyName, Collection<?> value) {
        criterionList.add(Restrictions.in(propertyName, value));
        return this;
    }

    public Conditions lt(String propertyName, Object value) {
        criterionList.add(Restrictions.lt(propertyName, value));
        return this;
    }

    public Conditions le(String propertyName, Object value) {
        criterionList.add(Restrictions.le(propertyName, value));
        return this;
    }

    public Conditions between(String propertyName, Object lo, Object hi) {
        criterionList.add(Restrictions.between(propertyName, lo, hi));
        return this;
    }

}
