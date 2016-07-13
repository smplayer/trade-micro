package com.as.erp.trade.micro.quotation.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import com.as.erp.trade.micro.quotation.QuotationProductItemDraftPropModifiedEvent;
import com.as.erp.trade.micro.quotation.dao.QuotationProductItemDraftDao;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.vo.QuotationAccumulativeTotal;
import com.as.erp.trade.micro.quotation.vo.QuotationProductItemDraftPropModifiedVO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by yrx on 2016/5/12.
 */
@Service("quotationProductItemDraftService")
public class QuotationProductItemDraftServiceImpl extends GenericServiceImpl<QuotationProductItemDraft, String> implements QuotationProductItemDraftService {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private QuotationProductItemDraftDao quotationProductItemDraftDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private FactoryService factoryService;

    @Override
    protected GenericDao<QuotationProductItemDraft, String> getDao() {
        return quotationProductItemDraftDao;
    }

    @Override
    public void generateProducts(List<String> quotationProductItemDraftIds) {
        List<QuotationProductItemDraft> list = getList(Conditions.newInstance().in("id", quotationProductItemDraftIds));
        List<Product> productList = new ArrayList<>();
        for(QuotationProductItemDraft draft : list) {
            Product product;
            if (StringUtils.isBlank(draft.getProductId())){
                product = new Product();

                product.setName(draft.getCompanyProductName());
                product.setImageURL(draft.getImageURL());
                product.setFactoryProductNo(draft.getFactoryProductNo());
                product.setCompanyProductNo(draft.getCompanyProductNo());
                product.setFactoryProductName(draft.getFactoryProductName());
                product.setCompanyProductName(draft.getFactoryProductName());
                product.setFactoryPrice(draft.getFactoryPrice());
                product.setCartonSize(draft.getCartonSize());
                product.setPackingQuantity(draft.getPackingQuantity());
                product.setGrossWeight(draft.getGrossWeight());
                product.setNetWeight(draft.getNetWeight());
                product.setUnit(draft.getUnit());
                product.setRemark(draft.getRemark());
                product.setPackageForm(draft.getPackageForm());

                //工厂信息
                product.setFactoryId(draft.getFactoryId());
                product.setFactoryName(draft.getFactoryName());
                product.setLinkman(draft.getLinkman());
                product.setFactoryContactNumber(draft.getContactNumber());

                productService.save(product);
                draft.setProductId(product.getId());
                draft.setCompanyProductNo(product.getCompanyProductNo());
                draft.setSyncToProduct(true);
                update(draft);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
//                product = productService.getById(draft.getProductId());
            }
        }
    }

    @Override
    public void selectFactoryForProductItemDraft(String id, String factoryId) {
        QuotationProductItemDraft draft = getById(id);
        Factory factory = factoryService.getById(factoryId);
        draft.setFactoryId(factory.getId());
        draft.setFactoryName(factory.getName());
        draft.setLinkman(factory.getLinkman());
        draft.setContactNumber(factory.getMobileNumber());
        update(draft);
    }

    @Override
    public QuotationAccumulativeTotal getQuotationAccumulativeTotal(String quotationId, Long pageQuantity, Long pageIndex, Integer pageSize) {
        List<QuotationProductItemDraft> draftList = getList(
                new Query().setDataIndex((pageIndex - 1) * pageSize)
                        .setPageSize(10000)
//                .setPageSize(pageSize * pageIndex.intValue())
                .setConditions(
                        Conditions.newInstance()
                        .eq("quotationId", quotationId)
                )
                .addOrder(Order.desc("createdTime"))
        );

        QuotationAccumulativeTotal accumulativeTotal = new QuotationAccumulativeTotal();
        accumulativeTotal.setCartonQuantity(0);
        accumulativeTotal.setProductQuantity(0);
        accumulativeTotal.setVolume(0D);
        accumulativeTotal.setAmount(0D);
        for (QuotationProductItemDraft draft : draftList) {
            accumulativeTotal.setCartonQuantity(accumulativeTotal.getCartonQuantity() + (draft.getOrderedCartonQuantity() != null ? draft.getOrderedCartonQuantity() : 0));
            accumulativeTotal.setProductQuantity(accumulativeTotal.getProductQuantity() + (draft.getOrderedProductQuantity() != null ? draft.getOrderedProductQuantity() : 0));
            accumulativeTotal.setVolume(accumulativeTotal.getVolume() + (draft.getTotalVolume() != null ? draft.getTotalVolume() : 0D));
            accumulativeTotal.setAmount(accumulativeTotal.getAmount() + (draft.getTotalAmount() != null ? draft.getTotalAmount() : 0D));
        }
        return accumulativeTotal;
    }

    @Override
    public void save(QuotationProductItemDraft entity) {
        entity.setAddedDate(new Date());
        entity.setLastQuotedDate(new Date());
        super.save(entity);
    }

    @Override
    public void saveOrUpdate(QuotationProductItemDraft entity) {
        entity.setLastQuotedDate(new Date());
        super.saveOrUpdate(entity);
    }

    @Override
    public QuotationProductItemDraft modify(String id, String propertyName, Object propertyValue) {
        if ("factoryPrice".equals(propertyName)) {
            modifyFactoryPrice(id, propertyName, propertyValue);
        } else if ("packingQuantity".equals(propertyName)) {
            modifyPackingQuantity(id, propertyName, propertyValue);
        } else if ("grossWeight".equals(propertyName)) {
            modifyGrossWeight(id, propertyName, propertyValue);
        } else if ("netWeight".equals(propertyName)) {
            modifyNetWeight(id, propertyName, propertyValue);
        } else if ("orderedCartonQuantity".equals(propertyName)) {
            modifyOrderedCartonQuantity(id, propertyName, propertyValue);
        } else if ("orderedProductQuantity".equals(propertyName)) {
            modifyOrderedProductQuantity(id, propertyName, propertyValue);
        } else if ("quotedPrice".equals(propertyName)) {
            modifyQuotedPrice(id, propertyName, propertyValue);
        } else if ("totalVolume".equals(propertyName)) {
            modifyTotalVolume(id, propertyName, propertyValue);
        } else if ("totalAmount".equals(propertyName)) {
            modifyTotalAmount(id, propertyName, propertyValue);
        } else {
            modifyProp(id, propertyName, propertyValue);
        }

        QuotationProductItemDraftPropModifiedVO modified = new QuotationProductItemDraftPropModifiedVO();
        modified.setId(id);
        modified.setPropertyName(propertyName);
        modified.setPropertyValue(propertyValue);

        applicationContext.publishEvent(new QuotationProductItemDraftPropModifiedEvent(modified));

        QuotationProductItemDraft item = quotationProductItemDraftDao.getById(id);
        return item;
    }

    private void modifyFactoryPrice(String id, String propertyName, Object propertyValue) {
        Double doubleValue = Double.valueOf((String) propertyValue);
        modifyProp(id, propertyName, doubleValue);
    }

    private void modifyPackingQuantity(String id, String propertyName, Object propertyValue) {
        Integer intValue = Integer.valueOf((String) propertyValue);
        modifyProp(id, propertyName, intValue);
    }

    private void modifyGrossWeight(String id, String propertyName, Object propertyValue){
        Double doubleValue = Double.valueOf((String) propertyValue);
        modifyProp(id, propertyName, doubleValue);
    }

    private void modifyNetWeight(String id, String propertyName, Object propertyValue){
        Double doubleValue = Double.valueOf((String) propertyValue);
        modifyProp(id, propertyName, doubleValue);
    }

    private void modifyOrderedCartonQuantity(String id, String propertyName, Object propertyValue){
        Integer intValue = Integer.valueOf((String) propertyValue);
        modifyProp(id, propertyName, intValue);
    }

    private void modifyOrderedProductQuantity(String id, String propertyName, Object propertyValue){
        Integer intValue = Integer.valueOf((String) propertyValue);
        modifyProp(id, propertyName, intValue);
    }

    private void modifyQuotedPrice(String id, String propertyName, Object propertyValue) {
        Double doubleValue = Double.valueOf((String) propertyValue);
        modifyProp(id, propertyName, doubleValue);
    }

    private void modifyTotalVolume(String id, String propertyName, Object propertyValue) {
        Double doubleValue = Double.valueOf((String) propertyValue);
        modifyProp(id, propertyName, doubleValue);

    }

    private void modifyTotalAmount(String id, String propertyName, Object propertyValue) {
        Double doubleValue = Double.valueOf((String) propertyValue);
        modifyProp(id, propertyName, doubleValue);
    }

    private void modifyProp(String id, String propertyName, Object propertyValue) {
        QuotationProductItemDraft draft = quotationProductItemDraftDao.getById(id);
        try {
            Field field = draft.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(draft, propertyValue);
            quotationProductItemDraftDao.update(draft);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copyItem(String targetId) {
        QuotationProductItemDraft target = getById(targetId);

        QuotationProductItemDraft newItem = new QuotationProductItemDraft();
        newItem.setQuotationId(target.getQuotationId());
//        newItem.setProductId(target.getProductId());
//        newItem.setImageURL(target.getImageURL());
        newItem.setFactoryProductName(target.getFactoryProductName());
        newItem.setFactoryProductNo(target.getFactoryProductNo());
        newItem.setCompanyProductName(target.getCompanyProductName());
        newItem.setCompanyProductNo(target.getCompanyProductNo());
        newItem.setPackageForm(target.getPackageForm());
        newItem.setUnit(target.getUnit());
        newItem.setFactoryPrice(target.getFactoryPrice());
        newItem.setCartonSize(target.getCartonSize());
        newItem.setCartonVolume(target.getCartonVolume());
        newItem.setPackingQuantity(target.getPackingQuantity());
        newItem.setGrossWeight(target.getGrossWeight());
        newItem.setNetWeight(target.getNetWeight());
        newItem.setOrderedCartonQuantity(target.getOrderedCartonQuantity());
        newItem.setQuotedPrice(target.getQuotedPrice());
        newItem.setOrderedProductQuantity(target.getOrderedProductQuantity());
        newItem.setTotalVolume(target.getTotalVolume());
        newItem.setTotalAmount(target.getTotalAmount());
        newItem.setFactoryId(target.getFactoryId());
        newItem.setFactoryName(target.getFactoryName());
        newItem.setLinkman(target.getLinkman());
        newItem.setContactNumber(target.getContactNumber());
        newItem.setRemark(target.getRemark());
        newItem.setAddedDate(new Date());

        save(newItem);
    }
}
