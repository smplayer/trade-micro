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

            if (StringUtils.isNotBlank(product.getFactoryId())) {
                Factory factory = factoryService.getById(product.getFactoryId());
                if (factory != null)
                    product.setFactoryContactNumber(
                            StringUtils.isNotBlank(factory.getMobileNumber()) ? factory.getMobileNumber() :
                                    StringUtils.isNotBlank(factory.getPhoneNumber()) ? factory.getPhoneNumber() : null
                    );
            }
        }


        if (
                StringUtils.isNotBlank(product.getCompanyProductName()) &&
                        StringUtils.isNotBlank(product.getCompanyProductNo()) &&
                        StringUtils.isNotBlank(product.getPackageForm()) &&
                        StringUtils.isNotBlank(product.getUnit()) &&
                        StringUtils.isNotBlank(product.getCartonSize()) &&
//                StringUtils.isNotBlank(product.getFunctionDescription()) &&

                        product.getFactoryPrice() != null && product.getFactoryPrice() != 0D &&
                        product.getPackingQuantity() != null && product.getPackingQuantity() != 0D &&
                        product.getGrossWeight() != null && product.getGrossWeight() != 0D &&
                        product.getNetWeight() != null && product.getNetWeight() != 0D
        ) {
            product.setProductStatus(Product.PRODUCT_STATUS_COMPLETE);
        } else {
            product.setProductStatus(Product.PRODUCT_STATUS_INCOMPLETE);
        }

    }

    public void publishEventForFactoryProductQuantity(String oldFactoryId, String newFactoryId, String productId){
        Map<String, Object> source = new HashMap<>();
        source.put("oldFactoryId", oldFactoryId);
        source.put("newFactoryId", newFactoryId);
        source.put("productId", productId);
        applicationContext.publishEvent(new ProductToFactoryEvent(source));
    }

    @Override
    public void save(Product product) {
        refreshProduct(product);
        product.setCompanyProductName(product.getFactoryProductName());
        product.setAddedDate(new Date());
        product.setLastFactoryQuotedDate(new Date());

        super.save(product);
        publishEventForFactoryProductQuantity(null, product.getFactoryId(), product.getId());
//        applicationContext.publishEvent(new ProductModifiedEvent(product));
    }

    @Override
    public void saveOrUpdate(Product product) {
        refreshProduct(product);

        if (product.getId() == null) {
            product.setCompanyProductName(product.getFactoryProductName());
        }

        if (product.getAddedDate() == null) {
            product.setAddedDate(new Date());
        }

        super.saveOrUpdate(product);
        applicationContext.publishEvent(new ProductModifiedEvent(product));
    }

    @Override
    public void update(Product product) {
        refreshProduct(product);

        applicationContext.publishEvent(new ProductModifiedEvent(product));

        super.update(product);
    }

    @Override
    public Product selectFactory(String id, String factoryId) {
        Product product = getById(id);

        String oldFactoryId = product.getFactoryId();

        product.setFactoryId(factoryId);

        publishEventForFactoryProductQuantity(oldFactoryId, factoryId, id);

        update(product);
        return product;
    }

    @Override
    public void delete(String id) {
        applicationContext.publishEvent(new ProductDeletedEvent(id));
//        _publishForFactoryProductQuantity(getById(id));

        super.delete(id);
    }

    @Override
    public void copyProducts(String targetId, Integer count) {

        Product target = getById(targetId);

        for (int i = 0; i < count; i++) {
            Product newProduct = new Product();

            newProduct.setName(target.getName());
            newProduct.setImageURL(target.getImageURL());
            newProduct.setFactoryProductName(target.getFactoryProductName());
            newProduct.setCompanyProductName(target.getCompanyProductName());
            newProduct.setFactoryProductNo(target.getFactoryProductNo());
//            newProduct.setCompanyProductNo(target.getCompanyProductNo());
            newProduct.setFactoryPrice(target.getFactoryPrice());
            newProduct.setLastFactoryQuotedDate(new Date());
            newProduct.setCartonSize(target.getCartonSize());
            newProduct.setPackingQuantity(target.getPackingQuantity());
            newProduct.setGrossWeight(target.getGrossWeight());
            newProduct.setNetWeight(target.getNetWeight());
            newProduct.setUnit(target.getUnit());
//            newProduct.setRemark(target.getRemark());
            newProduct.setFactoryId(target.getFactoryId());
            newProduct.setFactoryName(target.getFactoryName());
            newProduct.setLinkman(target.getLinkman());
            newProduct.setFactoryContactNumber(target.getFactoryContactNumber());
            newProduct.setPackageForm(target.getPackageForm());
            newProduct.setFunctionDescription(target.getFunctionDescription());
            newProduct.setCategory(target.getCategory());
            newProduct.setSubCategory(target.getSubCategory());
            newProduct.setAddedDate(new Date());
            newProduct.setProductStatus(target.getProductStatus());

            save(newProduct);

        }
    }
}
