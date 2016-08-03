package com.as.erp.trade.micro.container.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.container.dao.PreloadedProductItemDao;
import com.as.erp.trade.micro.container.entity.PreloadedProductItem;
import com.as.erp.trade.micro.container.service.PreloadedProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/7/31.
 */
@Service("preloadedProductItemService")
public class PreloadedProductItemServiceImpl extends GenericServiceImpl<PreloadedProductItem, String> implements PreloadedProductItemService {

    @Autowired
    private PreloadedProductItemDao preloadedProductItemDao;

    @Override
    protected GenericDao<PreloadedProductItem, String> getDao() {
        return preloadedProductItemDao;
    }
}
