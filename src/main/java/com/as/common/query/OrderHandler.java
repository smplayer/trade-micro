package com.as.common.query;

import com.as.common.Base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Newbody on 2016/2/26.
 */
public class OrderHandler extends Base {
    private List<Order> orderList = new ArrayList<>();

    public OrderHandler add(Order order) {
        this.orderList.add(order);
        return this;
    }

    public String toSqlString() {
        Iterator<Order> iterator = orderList.iterator();
        StringBuilder builder = new StringBuilder();
        if (iterator.hasNext()) {
            builder.append(" ORDER BY ");
        }
        while (iterator.hasNext()) {
            Order restriction = iterator.next();
            builder.append(restriction.getPropertyName()).append(" ").append(restriction.getOrderMode().getValue());
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        return builder.toString();
    }
}
