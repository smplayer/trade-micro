package com.as.erp.trade.micro.system.controller;

import com.as.erp.trade.micro.system.entity.SystemConfigItem;
import com.as.erp.trade.micro.system.entity.UserConfigItem;
import com.as.erp.trade.micro.system.service.SystemConfigItemService;
import com.as.erp.trade.micro.system.service.UserConfigItemService;
import com.as.user.entity.User;
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
public class UserConfigController {

    @Autowired
    private UserConfigItemService userConfigItemService;

    @ResponseBody
    @RequestMapping("ajax/user/config/getValue")
    public Object getValue(
            @RequestBody Map<String, Object> req,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        String value = userConfigItemService.getValue(user, (String) req.get("key"));
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        map.put("key", req.get("key"));
        map.put("value", value);
        return map;
    }

    @ResponseBody
    @RequestMapping("ajax/user/config/setFavorModule")
    public Object setFavorModule(
            @RequestBody Map<String, Object> req,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");

        String module = (String) req.get("module");
        UserConfigItem config = userConfigItemService.setValue(user, UserConfigItem.USER_FAVOR_MODULE, module);

        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        map.put("config", config);
        return map;
    }


}
