package com.as.erp.trade.micro.quotation;

import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import com.as.erp.trade.micro.quotation.vo.QuotationProductItemDraftPropModifiedVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by yrx on 2016/5/16.
 */
@Component
public class QuotationProductItemDraftPropModifiedListener implements SmartApplicationListener {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private QuotationProductItemDraftService quotationProductItemDraftService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == QuotationProductItemDraftPropModifiedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == QuotationProductItemDraftPropModifiedVO.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        QuotationProductItemDraftPropModifiedVO vo = (QuotationProductItemDraftPropModifiedVO) event.getSource();

        QuotationProductItemDraft item = quotationProductItemDraftService.getById(vo.getId());
        String propertyName = vo.getPropertyName();

        if("factoryPrice".equals(propertyName)) {
            reCalQuotedPrice(item);
            reCalTotalAmount(item);
        } else if ("cartonSize".equals(propertyName)){
            reCalCartonVolume(item);
            reCalTotalVolume(item);
            reCalQuotedPrice(item);
            reCalTotalAmount(item);
        } else if ("packingQuantity".equals(propertyName)){
            reCalOrderedProductQuantity(item);
            reCalTotalVolume(item);
            reCalQuotedPrice(item);
            reCalTotalAmount(item);
        } else if ("orderedCartonQuantity".equals(propertyName)){
            reCalOrderedProductQuantity(item);
            reCalTotalVolume(item);
            reCalQuotedPrice(item);
            reCalTotalAmount(item);
        } else if ("quotedPrice".equals(propertyName)){
            reCalTotalAmount(item);
        } else if ("factoryProductName".equals(propertyName)){
            initCompanyProductName(item);
        } else if ("factoryName".equals(propertyName)){
            item.setFactoryId(null);
        }

        quotationProductItemDraftService.update(item);
    }

    @Override
    public int getOrder() {
        return 0;
    }


    private void reCalQuotedPrice(QuotationProductItemDraft item){
        Quotation quotation = quotationService.getById(item.getQuotationId());
        Double containerVolume = quotation.getContainerVolume();
        if(item.getCartonVolume() != null && item.getCartonVolume() > 0 && item.getCartonVolume() > 0 && item.getPackingQuantity() > 0) {
            Double cartonVolume = item.getCartonVolume();
            Double containerPackageCount = round(containerVolume / cartonVolume, 3);
            Double containerProductCount = round(containerPackageCount * item.getPackingQuantity(), 3);

            Double price = 0D;
            if (quotation.getProfitPercent() != null) {
                price = round(item.getFactoryPrice() / (1 - quotation.getProfitPercent().doubleValue() / 100D), 3);
            } else {
                price = round(quotation.getProfitAmount() / containerProductCount + item.getFactoryPrice(), 3);
            }

            Double singleFee = round( quotation.getCustomsClearanceFee() / containerProductCount, 3);
            price = round((price + singleFee) / quotation.getExchangeRate(), quotation.getDecimalPlaces());
            item.setQuotedPrice(price);
        } else {
            item.setQuotedPrice(null);
        }
    }

    private void reCalOrderedProductQuantity(QuotationProductItemDraft item) {
        item.setOrderedProductQuantity(item.getPackingQuantity() * item.getOrderedCartonQuantity());
    }

    private void reCalTotalVolume(QuotationProductItemDraft item) {
        item.setTotalVolume(round(item.getCartonVolume() * item.getOrderedCartonQuantity(), 0));
    }

    private void reCalTotalAmount(QuotationProductItemDraft item) {
        item.setTotalAmount(round(item.getQuotedPrice() * item.getOrderedProductQuantity(), 0));
    }

    private void reCalCartonVolume(QuotationProductItemDraft item) {
        if(StringUtils.isNotBlank(item.getCartonSize())) {
            String cartonSize = item.getCartonSize();
            String[] sizeElems = cartonSize.split("X");
            Double volume = round( Double.valueOf(sizeElems[0]) * Double.valueOf(sizeElems[1]) * Double.valueOf(sizeElems[2]) / (100*100*100) , 3);
            item.setCartonVolume(round(volume, 2));
        } else {
            item.setCartonVolume(0D);
        }
    }

    private void initCompanyProductName(QuotationProductItemDraft item) {
        if(StringUtils.isBlank(item.getCompanyProductName()))
            item.setCompanyProductName(item.getFactoryProductName());
    }

    private Double round(Double value, Integer scale) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }


}
