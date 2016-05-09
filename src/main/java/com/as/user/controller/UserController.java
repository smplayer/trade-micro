package com.as.user.controller;

import com.as.common.Base;
import com.as.user.service.impl.UserServiceImpl;
import com.as.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Newbody on 2016/3/1.
 */
@Controller
public class UserController extends Base {


//    @Resource(name = "userDao")
    @Autowired
    private UserServiceImpl userService;

    @ResponseBody
    @RequestMapping("user/test")
    public String userTest(){
        User user = new User();
        user.setUsername("username1");
        user.setPassword("password1");
        userService.save(user);

        return "test";
    }

}
