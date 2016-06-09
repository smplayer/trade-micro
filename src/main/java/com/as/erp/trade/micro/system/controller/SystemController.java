package com.as.erp.trade.micro.system.controller;

import com.as.user.entity.User;
import com.as.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by yrx on 2016/6/2.
 */
@Controller
public class SystemController {

    @Autowired
    private UserService userService;

    @RequestMapping("system/construction")
    public String systemConstruction() {
        return "system/construction";
    }

    @RequestMapping(value = "system/setPassword", method = RequestMethod.GET)
    public String setPassword() {
        return "system/set-password/set-password";
    }

    @RequestMapping(value = "system/setPassword", method = RequestMethod.POST)
    public String setPassword(
            @RequestParam String oldAdminPassword,
            @RequestParam String newAdminPassword,
            @RequestParam String confirmNewAdminPassword,
            @RequestParam String oldStandardPassword,
            @RequestParam String newStandardPassword,
            @RequestParam String confirmNewStandardPassword,
            ModelMap modelMap,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");

        boolean flag = true;

        if (user.getRole().equals(User.ROLE_ADMIN)) {

            if (
//                    newAdminPassword != null && newAdminPassword.equals(confirmNewAdminPassword) &&
//                            newStandardPassword != null && newStandardPassword.equals(confirmNewStandardPassword)
                    newAdminPassword != null && newStandardPassword != null
                    ) {
                user.setAdminPassword(newAdminPassword);
                user.setStandardPassword(newStandardPassword);
                userService.update(user);
            } else {
                flag = false;
                modelMap.put("error", "权限不足");
            }

        } else {
            flag = false;
            modelMap.put("error", "权限不足");
        }

        if (flag)
            return "common/close-after-success";
        else
            return "redirect:/system/setPassword";
    }

}
