package com.as.user.controller;

import com.as.common.Base;
import com.as.user.service.impl.UserServiceImpl;
import com.as.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Newbody on 2016/3/1.
 */
@Controller
public class UserController extends Base {


    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            ModelMap modelMap
    ){

        return "redirect:/quotation/operating/list";
    }

}
