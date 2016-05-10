package com.as.erp.trade.micro.quotation.vo;

import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItem;

/**
 * Created by yrx on 2016/5/10.
 */
public class QuotationProductItemVO {
    private QuotationProductItem quotationProductItem;
    private Product product;

    public QuotationProductItem getQuotationProductItem() {
        return quotationProductItem;
    }

    public void setQuotationProductItem(QuotationProductItem quotationProductItem) {
        this.quotationProductItem = quotationProductItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
