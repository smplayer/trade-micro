package com.as.common.query;

import java.util.Collection;

/**
 * Created by Newbody on 2016/3/11.
 */
public interface Restrictor {

    public Restrictor eq(String propertyName, Object value);

    public Restrictor ne(String propertyName, Object value);

    public Restrictor in(String propertyName, Collection<?> value);

    public Restrictor asc(String propertyName);

    public Restrictor desc(String propertyName);

}
