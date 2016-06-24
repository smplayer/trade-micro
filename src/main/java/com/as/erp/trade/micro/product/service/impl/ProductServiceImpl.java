package com.as.erp.trade.micro.product.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.factory.ProductToFactoryEvent;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.product.ProductDeletedEvent;
import com.as.erp.trade.micro.product.ProductModifiedEvent;
import com.as.erp.trade.micro.product.dao.ProductDao;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import com.as.erp.trade.micro.system.entity.SystemConfigItem;
import com.as.erp.trade.micro.system.service.SystemConfigItemService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yrx on 2016/4/29.
 */
@Service("productService")
public class ProductServiceImpl extends GenericServiceImpl<Product, String> implements ProductService {

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private SystemConfigItemService systemConfigItemService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected GenericDao<Product, String> getDao() {
        return productDao;
    }

    private void refreshProduct(Product product) {
        if (
                StringUtils.isNotBlank(product.getCompanyProductName()) &&
                StringUtils.isNotBlank(product.getPackageForm()) &&
                StringUtils.isNotBlank(product.getCartonSize()) &&
                StringUtils.isNotBlank(product.getFunctionDescription()) &&

                product.getFactoryPrice() != null &&
                product.getPackingQuantity() != null &&
                product.getGrossWeight() != null &&
                product.getNetWeight() != null
        ) {
            product.setProductStatus(Product.PRODUCT_STATUS_COMPLETE);
        } else {
            product.setProductStatus(Product.PRODUCT_STATUS_INCOMPLETE);
        }

        if(product.getId() == null) {

            String companyProductNo = productDao.get(Projections.max("companyProductNo"), String.class);
            if (companyProductNo == null) {

                companyProductNo = systemConfigItemService.getValue(SystemConfigItem.PRODUCT_N0_PREFIX);
                if (companyProductNo == null) {
                    companyProductNo = "A";
                }

                String begin = systemConfigItemService.getValue(SystemConfigItem.PRODUCT_NO_BEGIN);
                if (begin != null)
                    companyProductNo += (Integer.valueOf(begin) + 1);
                else
                    companyProductNo += "10000";
            } else {
                //TO-DO
                String prefix = systemConfigItemService.getValue(SystemConfigItem.PRODUCT_N0_PREFIX);
                if (prefix == null) {
                    prefix = "A";
                }

                String [] noElems = companyProductNo.split("[a-zA-Z]+");
                Integer nextNumber = Integer.valueOf(noElems[1]) + 1;
                companyProductNo = prefix + nextNumber;
            }
            product.setCompanyProductNo(companyProductNo);

            if (product.getFactoryId() != null) {
                Factory factory = factoryService.getById(product.getFactoryId());
                product.setFactoryContactNumber(
                        StringUtils.isNotBlank(factory.getMobileNumber()) ? factory.getMobileNumber() :
                                StringUtils.isNotBlank(factory.getPhoneNumber()) ? factory.getPhoneNumber() : null
                );
            }
        }
    }

    private void _publishForFactoryProductQuantity(Product product){
        if (product.getFactoryId() != null) {
            Map<String, Object> source = new HashMap<>();
            source.put("newFactoryId", product.getFactoryId());
            source.put("product", product);
            applicationContext.publishEvent(new ProductToFactoryEvent(source));
        }

    }

    @Override
    public void save(Product product) {
        refreshProduct(product);
        product.setAddedDate(new Date());

        _publishForFactoryProductQuantity(product);

        super.save(product);
//        applicationContext.publishEvent(new ProductModifiedEvent(product));
    }

    @Override
    public void saveOrUpdate(Product product) {
        refreshProduct(product);
        if (product.getAddedDate() == null) {
            product.setAddedDate(new Date());
        }

        _publishForFactoryProductQuantity(product);

        super.saveOrUpdate(product);
        applicationContext.publishEvent(new ProductModifiedEvent(product));
    }

    @Override
    public void update(Product product) {
        refreshProduct(product);

        _publishForFactoryProductQuantity(product);

        applicationContext.publishEvent(new ProductModifiedEvent(product));
        super.update(product);
    }

    @Override
    public Product selectFactory(String id, String factoryId) {
        Product product = getById(id);
        product.setFactoryId(factoryId);

        _publishForFactoryProductQuantity(product);

        update(product);
        return product;
    }

    @Override
    public void delete(String id) {
        applicationContext.publishEvent(new ProductDeletedEvent(id));
        _publishForFactoryProductQuantity(getById(id));
        super.delete(id);
    }
}
