package com.as.common.query.hibernate;

import com.as.common.Base;
import com.as.common.query.Restrictor;

import static com.as.common.config.PagingConfig.MAX_PAGE_SIZE;
import static com.as.common.config.PagingConfig.DEFAULT_PAGE_SIZE;

/**
 * Created by Newbody on 2016/2/26.
 */
public class QueryForHibernate extends Base {
    private Restrictor restrictor;
    private Long pageIndex = 1L;
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    public Long getPageIndex() {
        return  1 > pageIndex ? 1 : pageIndex;
    }

    public QueryForHibernate setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public Integer getPageSize() {
        return 1 > pageSize ? DEFAULT_PAGE_SIZE : MAX_PAGE_SIZE < pageSize ? MAX_PAGE_SIZE : pageSize;
    }

    public QueryForHibernate setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Long getDataIndex() {
        return (pageIndex - 1) * pageSize;
    }
}
