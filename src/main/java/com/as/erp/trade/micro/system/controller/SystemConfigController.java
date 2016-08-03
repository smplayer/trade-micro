package com.as.erp.trade.micro.system.controller;

import com.as.erp.trade.micro.system.entity.SystemConfigItem;
import com.as.erp.trade.micro.system.service.SystemConfigItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yrx on 2016/5/19.
 */
@Controller
public class SystemConfigController {

    @Autowired
    private SystemConfigItemService systemConfigItemService;

    @ResponseBody
    @RequestMapping("ajax/system/config/getValue")
    public Object getValue(
            @RequestBody Map<String, Object> req,
            HttpSession session
    ) {
        String value = systemConfigItemService.getValue((String) req.get("key"));
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        map.put("key", req.get("key"));
        map.put("value", value);
        return map;
    }

    @RequestMapping(value = "system/config/number", method = RequestMethod.GET)
    public String config1() {
        return "system/config/number";
    }

    @RequestMapping(value = "system/config/number", method = RequestMethod.POST)
    public String config1(
            @RequestParam String startProductNumber,
            @RequestParam String orderNumber
    ) {
//        String prefix = startProductNumber.substring(0, startProductNumber.lastIndexOf("A") + 1);
        Pattern pattern = Pattern.compile("[A-Za-z]{1,2}[1-9][0-9]{4}");
        Matcher matcher = pattern.matcher(startProductNumber);
        if (matcher.matches()) {
            Pattern prefixPattern = Pattern.compile("[A-Za-z]{1,2}");
            Pattern numberPattern = Pattern.compile("[1-9][0-9]{4}");
            Matcher prefixMatcher = prefixPattern.matcher(startProductNumber);
            Matcher numberMatcher = numberPattern.matcher(startProductNumber);

            if (prefixMatcher.find()) {
                String prefix = prefixMatcher.group();
                systemConfigItemService.setValue(SystemConfigItem.PRODUCT_N0_PREFIX, prefix);
            }
            if (numberMatcher.find()) {
                String begin = numberMatcher.group();
                systemConfigItemService.setValue(SystemConfigItem.PRODUCT_NO_BEGIN, begin);
            }
        }

        return "system/config/config-number-finish";
    }

}
