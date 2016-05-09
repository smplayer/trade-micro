package com.as.erp.trade.micro.quotation.controller;

import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.entity.QuotationModuleConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yrx on 2016/5/4.
 */
@Controller
public class QuotationSettingController extends BaseQuotationController {

    @RequestMapping(value = "quotation/operating/setting", method = RequestMethod.GET)
    public String setting(ModelMap modelMap) {
        QuotationModuleConfig quotationModuleConfig = quotationModuleConfigService.getUnique();
        if(StringUtils.isNotBlank(quotationModuleConfig.getOperatingQuotationId())){
            Quotation quotation = quotationService.getById(quotationModuleConfig.getOperatingQuotationId());
            modelMap.put("quotation", quotation);
        }
        return "quotation/operating-setting";
    }

    @RequestMapping(value = "quotation/operating/setting", method = RequestMethod.POST)
    public String setting(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "customerName", required = false) String customerName,
            @RequestParam(value = "region", required = false) String region,

            @RequestParam(value = "FOB", required = false) String FOB,
            @RequestParam(value = "CNF", required = false) String CNF,
            @RequestParam(value = "GIF", required = false) String GIF,
//            @RequestParam(value="tradeClause", required=false) String tradeClause,

            @RequestParam(value = "shipmentPort", required = false) String shipmentPort,
            @RequestParam(value = "containerType", required = false) String containerType,
            @RequestParam(value = "profitPercent", required = false) Double profitPercent,
            @RequestParam(value = "profitAmount", required = false) Double profitAmount,
            @RequestParam(value = "customsClearanceFee", required = false) Double customsClearanceFee,
            @RequestParam(value = "currency", required = false) String currency,
            @RequestParam(value = "exchangeRate", required = false) Double exchangeRate,
            @RequestParam(value = "decimalPlaces", required = false) Integer decimalPlaces,
            @RequestParam(value = "editor", required = false) String editor,
            @RequestParam(value = "tel", required = false) String tel,
            ModelMap modelMap
    ) {
        Quotation quotation = null;
        QuotationModuleConfig quotationModuleConfig = quotationModuleConfigService.getUnique();
        if(StringUtils.isNotBlank(quotationModuleConfig.getOperatingQuotationId())){
            quotation = quotationService.getById(quotationModuleConfig.getOperatingQuotationId());
        } else {
            quotation = new Quotation();
        }
        quotation.setCustomerName(customerName);
        quotation.setRegion(region);
        quotation.setShipmentPort(shipmentPort);
        quotation.setContainerType(containerType);
        quotation.setProfitPercent(profitPercent);
        quotation.setProfitAmount(profitAmount);
        quotation.setCustomsClearanceFee(customsClearanceFee);
        quotation.setCurrency(currency);
        quotation.setExchangeRate(exchangeRate);
        quotation.setDecimalPlaces(decimalPlaces);
        quotation.setEditor(editor);
        quotation.setTel(tel);

        String tradeClauseType = null;
        String tradeClause = null;
        if(StringUtils.isNotBlank(FOB)){
            tradeClauseType = "FOB";
            tradeClause = FOB;
        } else if (StringUtils.isNotBlank(CNF)){
            tradeClauseType = "CNF";
            tradeClause = CNF;
        } else if (StringUtils.isNotBlank(GIF)){
            tradeClauseType = "GIF";
            tradeClause = GIF;
        }
        quotation.setTradeClauseType(tradeClauseType);
        quotation.setTradeClause(tradeClause);

        quotationService.saveOrUpdate(quotation);

        //改为事件机制
        if(StringUtils.isBlank(quotationModuleConfig.getOperatingQuotationId())){
            quotationModuleConfig.setOperatingQuotationId(quotation.getId());
            quotationModuleConfigService.saveOrUpdate(quotationModuleConfig);
        }

        return "quotation/operating";
    }


}
