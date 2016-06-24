package com.as.erp.trade.micro.system.controller;

import com.as.user.entity.User;
import com.as.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yrx on 2016/6/2.
 */
@Controller
public class SystemController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "system/setPassword", method = RequestMethod.GET)
    public String setPassword(
            ModelMap modelMap,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().equals(User.ROLE_ADMIN)) {

        }
        return "system/set-password/set-password";
    }

    @RequestMapping(value = "system/setPassword", method = RequestMethod.POST)
    public String setPassword(
            @RequestParam String oldAdminPassword,
            @RequestParam String newAdminPassword,
            @RequestParam String oldStandardPassword,
            @RequestParam String newStandardPassword,
            ModelMap modelMap,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");

        boolean flag = true;

        if (user.getRole().equals(User.ROLE_ADMIN)) {

            if (
//                    newAdminPassword != null && newAdminPassword.equals(confirmNewAdminPassword) &&
//                            newStandardPassword != null && newStandardPassword.equals(confirmNewStandardPassword)
                    StringUtils.isNotBlank(newAdminPassword)
                    ) {
                user.setAdminPassword(newAdminPassword);
            }
            if ( StringUtils.isNotBlank(newStandardPassword) ) {
                user.setStandardPassword(newStandardPassword);
            }

            userService.update(user);

        } else {
            flag = false;
            modelMap.put("error", "权限不足");
        }

        if (flag)
            return "system/set-password/set-password-finish";
        else
            return "redirect:/system/setPassword";
    }

    @RequestMapping("system/construction")
    public String construction(
            @RequestParam String currentModule
    ) {
        Map<String, String> map = new HashMap<>();
        map.put("currentModule",currentModule);
        return "common/construction";
    }

}
