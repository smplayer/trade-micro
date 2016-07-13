package com.as.erp.trade.micro.quotation.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.quotation.QuotationModifiedEvent;
import com.as.erp.trade.micro.quotation.dao.QuotationDao;
import com.as.erp.trade.micro.quotation.entity.FavorQuotationItem;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.entity.QuotationOperating2Archive;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.FavorQuotationItemService;
import com.as.erp.trade.micro.quotation.service.QuotationOperating2ArchiveService;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import com.as.erp.trade.micro.quotation.vo.ArchiveTotalValues;
import com.as.erp.trade.micro.quotation.vo.QuotationAccumulativeTotal;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private FavorQuotationItemService favorQuotationItemService;

    @Autowired
    private QuotationDao quotationDao;

    @Override
    protected GenericDao<Quotation, String> getDao() {
        return quotationDao;
    }

    @Override
    public void save(Quotation entity) {
        Integer serialNumber = get(Projections.max("serialNumber"), Integer.class);
        if (serialNumber == null) {
            serialNumber = 10000;
        }
        entity.setSerialNumber(++serialNumber);
        super.save(entity);
    }

    @Override
    public void delete(String id) {
        List<QuotationProductItemDraft> items = quotationProductItemDraftService.getList(
                Conditions.newInstance().eq("quotationId", id)
        );
        for (QuotationProductItemDraft item : items) {
            quotationProductItemDraftService.delete(item.getId());
        }

        QuotationOperating2Archive operating2Archive = quotationOperating2ArchiveService.get(Conditions.newInstance().eq("archiveId", id));

        if (operating2Archive != null && operating2Archive.getOperatingId() != null) {
            List<QuotationProductItemDraft> operatingItems = quotationProductItemDraftService.getList(
                    Conditions.newInstance().eq("quotationId", operating2Archive.getOperatingId())
            );
            for (QuotationProductItemDraft item : operatingItems) {
                quotationProductItemDraftService.delete(item.getId());
            }

            List<FavorQuotationItem> favorList = favorQuotationItemService.getList(
                    Conditions.newInstance().eq("quotationId", operating2Archive.getOperatingId())
            );
            for (FavorQuotationItem favor : favorList) {
                favorQuotationItemService.delete(favor.getId());
            }
        }

        if (operating2Archive != null)
            quotationOperating2ArchiveService.delete(operating2Archive.getId());

        super.delete(id);
    }

    @Override
    public void saveOrUpdate(Quotation entity) {
        if (entity.getSerialNumber() == null) {
            Integer serialNumber = get(Projections.max("serialNumber"), Integer.class);
            if (serialNumber == null) {
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
        if (operating != null) {
            if (quotationOperating2Archive != null) {
                archive = getById(quotationOperating2Archive.getArchiveId());
                copyQuotationProps(operating, archive);
                archive.setArchivedDate(new Date());
                update(archive);

                List<QuotationProductItemDraft> archivedDraftList = quotationProductItemDraftService.getList(
                        Conditions.newInstance()
                                .eq("quotationId", archive.getId())
                );
                for (QuotationProductItemDraft draft : archivedDraftList) {
                    quotationProductItemDraftService.delete(draft.getId());
                }

                List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                        new Query().setPageSize(10000)
                                .addOrder(Order.asc("createdTime"))
                                .setConditions(
                                        Conditions.newInstance()
                                                .eq("quotationId", operating.getId())
                                )
                );
                for (QuotationProductItemDraft draft : draftList) {
                    QuotationProductItemDraft newItem = new QuotationProductItemDraft();
                    copyProductItemProps(draft, newItem);
                    newItem.setQuotationId(archive.getId());
                    quotationProductItemDraftService.save(newItem);
                    quotationProductItemDraftService.delete(draft.getId());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                delete(operating.getId());
                quotationOperating2ArchiveService.delete(quotationOperating2Archive.getId());
            } else {
                operating.setOperationFlag(Quotation.FLAG_ARCHIVED);
                operating.setArchivedDate(new Date());
                update(operating);
                archive = operating;
            }
        }
        return archive;
    }

    @Override
    public Quotation reloadFromArchive(String id) {

        QuotationOperating2Archive quotationOperating2Archive = quotationOperating2ArchiveService.get(
                Conditions.newInstance()
                        .eq("archiveId", id)
        );
        Quotation operating = null;
        Quotation archive = getById(id);
        if (quotationOperating2Archive != null) {
            operating = getById(quotationOperating2Archive.getOperatingId());
        } else {
            quotationOperating2Archive = new QuotationOperating2Archive();
        }
        if (operating != null) {
            copyQuotationProps(archive, operating);
            update(operating);

            List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                    Conditions.newInstance()
                            .eq("quotationId", operating.getId())
            );
            for (QuotationProductItemDraft draft : draftList) {
                quotationProductItemDraftService.delete(draft.getId());
            }
        } else {
            operating = new Quotation();
            copyQuotationProps(archive, operating);
            operating.setOperationFlag(Quotation.FLAG_OPERATING);
            save(operating);
            quotationOperating2Archive.setOperatingId(operating.getId());
            quotationOperating2Archive.setArchiveId(archive.getId());
            quotationOperating2ArchiveService.save(quotationOperating2Archive);
        }

        List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                new Query().setConditions(
                        Conditions.newInstance()
                                .eq("quotationId", archive.getId())
                ).addOrder(Order.asc("createdTime"))
        );
        for (QuotationProductItemDraft draft : draftList) {
            QuotationProductItemDraft newDraft = new QuotationProductItemDraft();
            copyProductItemProps(draft, newDraft);
            newDraft.setQuotationId(operating.getId());

            quotationProductItemDraftService.save(newDraft);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return operating;
    }

    @Override
    public Quotation copyFromArchive(String id) {
        Quotation archive = getById(id);
        Quotation operating = new Quotation();
        copyQuotationProps(archive, operating);
        operating.setGeneratedOrder(false);
        operating.setOperationFlag(Quotation.FLAG_OPERATING);
        save(operating);
//
//        List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
//                new Query().setConditions(
//                        Conditions.newInstance()
//                                .eq("quotationId", archive.getId())
//                ).addOrder(Order.asc("createdTime"))
//        );
//        for (QuotationProductItemDraft draft : draftList) {
//            QuotationProductItemDraft newDraft = new QuotationProductItemDraft();
//            copyProductItemProps(draft, newDraft);
//            newDraft.setQuotationId(operating.getId());
//            quotationProductItemDraftService.save(newDraft);
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        return operating;
    }

    @Override
    public Quotation getOperatingOrReloadFromArchive(String id) {
        Quotation quotation = getById(id);
        if (quotation != null) {
            if (quotation.getOperationFlag().equals(Quotation.FLAG_OPERATING)) {
                return quotation;
            } else {
                return reloadFromArchive(quotation.getId());
            }
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> getTotalValuesOfArchiveListAsMap(List<Quotation> archiveList) {
        Map<String, Object> map = new HashMap<>();
        for (Quotation a : archiveList) {
            List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                    new Query()
                            .setConditions(Conditions.newInstance().eq("quotationId", a.getId()))
            );

            ArchiveTotalValues archiveTotalValues = new ArchiveTotalValues();
            archiveTotalValues.setCartonQuantity(0);
            archiveTotalValues.setProductQuantity(0);
            archiveTotalValues.setVolume(0D);
            archiveTotalValues.setAmount(0D);
            for (QuotationProductItemDraft draft : draftList) {
                archiveTotalValues.setCartonQuantity(archiveTotalValues.getCartonQuantity() + (draft.getOrderedCartonQuantity() != null ? draft.getOrderedCartonQuantity() : 0));
                archiveTotalValues.setProductQuantity(archiveTotalValues.getProductQuantity() + (draft.getOrderedProductQuantity() != null ? draft.getOrderedProductQuantity() : 0));
                archiveTotalValues.setVolume(archiveTotalValues.getVolume() + (draft.getTotalVolume() != null ? draft.getTotalVolume() : 0D));
                archiveTotalValues.setAmount(archiveTotalValues.getAmount() + (draft.getTotalAmount() != null ? draft.getTotalAmount() : 0D));

                Double containerVolume = a.getContainerVolume();
                archiveTotalValues.setContainerQuantity(archiveTotalValues.getVolume() / containerVolume);
            }

            map.put(a.getId(), archiveTotalValues);

        }

        return map;
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
        b.setGeneratedOrder(a.getGeneratedOrder());
    }

    private void copyProductItemProps(QuotationProductItemDraft s, QuotationProductItemDraft t) {
        t.setQuotationId(s.getQuotationId());
        t.setProductId(s.getProductId());
        t.setSyncToProduct(s.getSyncToProduct());
        t.setImageURL(s.getImageURL());
        t.setFactoryProductName(s.getFactoryProductName());
        t.setFactoryProductNo(s.getFactoryProductNo());
        t.setCompanyProductName(s.getCompanyProductName());
        t.setCompanyProductNo(s.getCompanyProductNo());
        t.setPackageForm(s.getPackageForm());
        t.setUnit(s.getUnit());
        t.setFactoryPrice(s.getFactoryPrice());
        t.setCartonSize(s.getCartonSize());
        t.setCartonVolume(s.getCartonVolume());
        t.setPackingQuantity(s.getPackingQuantity());
        t.setGrossWeight(s.getGrossWeight());
        t.setNetWeight(s.getNetWeight());
        t.setOrderedCartonQuantity(s.getOrderedCartonQuantity());
        t.setQuotedPrice(s.getQuotedPrice());
        t.setOrderedProductQuantity(s.getOrderedProductQuantity());
        t.setTotalVolume(s.getTotalVolume());
        t.setTotalAmount(s.getTotalAmount());
        t.setFactoryId(s.getFactoryId());
        t.setFactoryName(s.getFactoryName());
        t.setLinkman(s.getLinkman());
        t.setContactNumber(s.getContactNumber());
        t.setRemark(s.getRemark());
        t.setAddedDate(s.getAddedDate());
    }

}
