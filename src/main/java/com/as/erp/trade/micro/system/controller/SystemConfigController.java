package com.as.erp.trade.micro.system.controller;

import com.as.erp.trade.micro.system.entity.SystemConfigItem;
import com.as.erp.trade.micro.system.service.SystemConfigItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
            @RequestParam String startProductNumber
    ) {
        String prefix = startProductNumber.substring(0, startProductNumber.lastIndexOf("A") + 1);
        String begin = startProductNumber.substring(startProductNumber.lastIndexOf("A") + 1);
        systemConfigItemService.setValue(SystemConfigItem.PRODUCT_N0_PREFIX, prefix);
        systemConfigItemService.setValue(SystemConfigItem.PRODUCT_NO_BEGIN, begin);

        return "common/close-after-success";
    }

}
