package com.as.user.controller;

import com.as.common.Base;
import com.as.common.query.hibernate.Conditions;
import com.as.erp.trade.micro.system.entity.UserConfigItem;
import com.as.erp.trade.micro.system.service.SystemConfigItemService;
import com.as.erp.trade.micro.system.service.UserConfigItemService;
import com.as.user.service.impl.UserServiceImpl;
import com.as.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Newbody on 2016/3/1.
 */
@Controller
public class UserController extends Base {
    @Autowired
    private SystemConfigItemService systemConfigItemService;
    @Autowired
    private UserConfigItemService userConfigItemService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "user/login", method = RequestMethod.GET)
    public String login(
            ModelMap modelMap
    ) {
        return "user/login";
    }

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            ModelMap modelMap
    ) throws UnsupportedEncodingException {
        User user = userService.get(Conditions.newInstance().eq("username", username));
        boolean flag = false;
        if(user != null) {
            if (user.getAdminPassword().equals(password)) {
                user.setRole(User.ROLE_ADMIN);
                session.setAttribute("user", user);
                flag = true;
            } else if (user.getStandardPassword().equals(password)) {
                user.setRole(User.ROLE_STANDARD);
                session.setAttribute("user", user);
                flag = true;
            }
        }

        if (flag) {
            session.setMaxInactiveInterval(-1);
        }

        if(!flag) {
//            modelMap.put("error", java.net.URLEncoder.encode("用户名或密码错误<br/><br/>请重新输入", "UTF-8"));
            modelMap.put("error", 1);
            return "redirect:/user/login";
        }

        Map<String, String> mapping = new HashMap<>();
        mapping.put("quotation", "/quotation/operating");
        mapping.put("product", "/product/list");
        mapping.put("factory", "/factory/list");
        mapping.put("order", "/order");
        mapping.put("container", "/container/containerSheet");
        mapping.put("payable", "/payable");

        String favorModule = userConfigItemService.getValue(user, UserConfigItem.USER_FAVOR_MODULE);
        if(favorModule != null) {
            return "redirect:" + mapping.get(favorModule);
        } else {
            return "redirect:/quotation/operating";
        }
    }

    @RequestMapping("user/logout")
    public String logout(
            HttpSession session
    ) {
        session.invalidate();
        return "redirect:/user/login";
    }

    @RequestMapping(value = "user/modifyPassword", method = RequestMethod.GET)
    public String modifyPassowrd(
            HttpSession session,
            ModelMap modelMap
    ) throws UnsupportedEncodingException {
        User user = (User) session.getAttribute("user");
        if(user.getRole().equals(User.ROLE_ADMIN)) {
            return "user/modify-password";
        } else {
//            modelMap.put("error", java.net.URLEncoder.encode("权限不足", "UTF-8"));
            modelMap.put("error", 1);
            return "redirect:/user/login";
        }
    }

    @RequestMapping(value = "user/modifyPassword", method = RequestMethod.POST)
    public String modifyPassowrd(
//            @RequestParam("username") String username,
            @RequestParam(value = "adminPassword", required = false) String adminPassword,
            @RequestParam(value = "confirmedAdminPassword", required = false) String confirmedAdminPassword,
            @RequestParam(value = "standardPassword", required = false) String standardPassword,
            @RequestParam(value = "confirmedStandardPassword", required = false) String confirmedStandardPassword,
            HttpSession session,
            ModelMap modelMap
    ) {
        User user = (User) session.getAttribute("user");
        if(user.getRole().equals(User.ROLE_ADMIN)) {
            if (StringUtils.isNotBlank(adminPassword) && adminPassword.equals(confirmedAdminPassword)) {
                user.setAdminPassword(adminPassword);
            }
            if (StringUtils.isNotBlank(standardPassword) && standardPassword.equals(confirmedStandardPassword)) {
                user.setAdminPassword(standardPassword);
            }
            userService.update(user);

            return "redirect:/user/modifyPassword";
        } else {
            modelMap.put("error", "权限不足");
            return "redirect:/user/login";
        }
    }

}
