package com.as.erp.trade.micro.quotation.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Query;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.product.dao.ProductDao;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.quotation.dao.QuotationProductItemDao;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItem;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemService;
import com.as.erp.trade.micro.quotation.vo.QuotationProductItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yrx on 2016/5/10.
 */
@Service("quotationProductItemService")
public class QuotationProductItemServiceImpl extends GenericServiceImpl<QuotationProductItem, String> implements QuotationProductItemService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private QuotationProductItemDao quotationProductItemDao;

    @Override
    protected GenericDao<QuotationProductItem, String> getDao() {
        return quotationProductItemDao;
    }

    @Override
    public PageHandler getQuotationProductItemVOPage(Query query) {
        PageHandler qpiPage = quotationProductItemDao.getPage(query);

        List<QuotationProductItemVO> voList = new ArrayList<>();
        for (Object oqpi : qpiPage.getDataList()) {
            QuotationProductItem qpi = (QuotationProductItem) oqpi;
            String productId = qpi.getProductId();
            Product product = productDao.getById(productId);

            QuotationProductItemVO vo = new QuotationProductItemVO();
            vo.setQuotationProductItem(qpi);
            vo.setProduct(product);
            voList.add(vo);
        }
        qpiPage.setDataList(voList);
        return qpiPage;
    }
}
