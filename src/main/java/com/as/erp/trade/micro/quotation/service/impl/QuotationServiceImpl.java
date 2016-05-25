package com.as.erp.trade.micro.quotation.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.hibernate.Conditions;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.quotation.QuotationModifiedEvent;
import com.as.erp.trade.micro.quotation.dao.QuotationDao;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.entity.QuotationOperating2Archive;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationOperating2ArchiveService;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yrx on 2016/5/4.
 */
@Service("quotationService")
public class QuotationServiceImpl extends GenericServiceImpl<Quotation, String> implements QuotationService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private QuotationProductItemDraftService quotationProductItemDraftService;

    @Autowired
    private QuotationOperating2ArchiveService quotationOperating2ArchiveService;

    @Autowired
    private QuotationDao quotationDao;

    @Override
    protected GenericDao<Quotation, String> getDao() {
        return quotationDao;
    }

    @Override
    public void save(Quotation entity) {
        Integer serialNumber =  get(Projections.max("serialNumber"), Integer.class);
        if(serialNumber == null) {
            serialNumber = 10000;
        }
        entity.setSerialNumber(++serialNumber);
        super.save(entity);
    }

    @Override
    public void saveOrUpdate(Quotation entity) {
        if (entity.getSerialNumber() == null){
            Integer serialNumber =  get(Projections.max("serialNumber"), Integer.class);
            if(serialNumber == null) {
                serialNumber = 10000;
            }
            entity.setSerialNumber(++serialNumber);
        }
        super.saveOrUpdate(entity);
        applicationContext.publishEvent(new QuotationModifiedEvent(entity));
    }

    @Override
    public Quotation getUniqueOperatingQuotation() {
        List<Quotation> list = getList(Conditions.newInstance().eq("operationFlag", Quotation.FLAG_OPERATING));
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("数据库中出现多个操作中的报价表");
        }
        return null;
    }

    @Override
    public Quotation saveToArchive(String id) {
        QuotationOperating2Archive quotationOperating2Archive = quotationOperating2ArchiveService.get(
                Conditions.newInstance()
                .eq("operatingId", id)
        );

        Quotation operating = getById(id);
        Quotation archive = null;
        if(quotationOperating2Archive != null){
            archive = getById(quotationOperating2Archive.getArchiveId());
            copyQuotationProps(operating, archive);
            update(archive);

            List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                    Conditions.newInstance()
                            .eq("quotationId", operating.getId())
            );
            for (QuotationProductItemDraft draft : draftList){
                quotationProductItemDraftService.delete(draft.getId());
            }
            delete(operating.getId());
            quotationOperating2ArchiveService.delete(quotationOperating2Archive.getId());
        } else {
            operating.setOperationFlag(Quotation.FLAG_ARCHIVED);
            update(operating);
            archive = operating;
        }
        return archive;
    }

    @Override
    public Quotation reloadFromArchive(String id) {

        QuotationOperating2Archive quotationOperating2Archive = quotationOperating2ArchiveService.get(
                Conditions.newInstance()
                        .eq("archiveId", id)
        );
        Quotation operating;
        Quotation archive = getById(id);
        if(quotationOperating2Archive != null){
            operating = getById(quotationOperating2Archive.getOperatingId());
            copyQuotationProps(archive, operating);
            update(operating);

            List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                    Conditions.newInstance()
                    .eq("quotationId", operating.getId())
            );
            for (QuotationProductItemDraft draft : draftList){
                quotationProductItemDraftService.delete(draft.getId());
            }
        } else {
            operating = new Quotation();
            copyQuotationProps(archive, operating);
            operating.setOperationFlag(Quotation.FLAG_OPERATING);
            save(operating);
            quotationOperating2Archive = new QuotationOperating2Archive();
            quotationOperating2Archive.setOperatingId(operating.getId());
            quotationOperating2Archive.setArchiveId(archive.getId());
            quotationOperating2ArchiveService.save(quotationOperating2Archive);
        }

        List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                Conditions.newInstance()
                        .eq("quotationId", archive.getId())
        );
        for (QuotationProductItemDraft draft : draftList){
            QuotationProductItemDraft newDraft = new QuotationProductItemDraft();
            newDraft.setQuotationId(operating.getId());
            newDraft.setProductId(draft.getProductId());
            newDraft.setImageURL(draft.getImageURL());
            newDraft.setFactoryProductName(draft.getFactoryProductName());
            newDraft.setFactoryProductNo(draft.getFactoryProductNo());
            newDraft.setCompanyProductName(draft.getCompanyProductName());
            newDraft.setCompanyProductNo(draft.getCompanyProductNo());
            newDraft.setPackageForm(draft.getPackageForm());
            newDraft.setUnit(draft.getUnit());
            newDraft.setFactoryPrice(draft.getFactoryPrice());
            newDraft.setCartonSize(draft.getCartonSize());
            newDraft.setCartonVolume(draft.getCartonVolume());
            newDraft.setPackingQuantity(draft.getPackingQuantity());
            newDraft.setGrossWeight(draft.getGrossWeight());
            newDraft.setNetWeight(draft.getNetWeight());
            newDraft.setOrderedCartonQuantity(draft.getOrderedCartonQuantity());
            newDraft.setQuotedPrice(draft.getQuotedPrice());
            newDraft.setOrderedProductQuantity(draft.getOrderedProductQuantity());
            newDraft.setTotalVolume(draft.getTotalVolume());
            newDraft.setTotalAmount(draft.getTotalAmount());
            newDraft.setFactoryId(draft.getFactoryId());
            newDraft.setFactoryName(draft.getFactoryName());
            newDraft.setLinkman(draft.getLinkman());
            newDraft.setContactNumber(draft.getContactNumber());
            newDraft.setRemark(draft.getRemark());
            newDraft.setAddedDate(draft.getAddedDate());
            quotationProductItemDraftService.save(newDraft);
        }

        return operating;
    }

    private void copyQuotationProps(Quotation a, Quotation b) {
        b.setSerialNumber(a.getSerialNumber());
        b.setCustomerName(a.getCustomerName());
        b.setRegion(a.getRegion());
        b.setTradeClauseType(a.getTradeClauseType());
        b.setTradeClause(a.getTradeClause());
        b.setShipmentPort(a.getShipmentPort());
        b.setContainerType(a.getContainerType());
        b.setContainerVolume(a.getContainerVolume());
        b.setProfitPercent(a.getProfitPercent());
        b.setProfitAmount(a.getProfitAmount());
        b.setCustomsClearanceFee(a.getCustomsClearanceFee());
        b.setCurrency(a.getCurrency());
        b.setExchangeRate(a.getExchangeRate());
        b.setDecimalPlaces(a.getDecimalPlaces());
        b.setEditor(a.getEditor());
        b.setTel(a.getTel());
        b.setStartedDate(a.getStartedDate());
        b.setLastQuotedDate(a.getLastQuotedDate());
    }

}
