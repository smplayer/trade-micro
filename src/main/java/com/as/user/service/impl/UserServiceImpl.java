package com.as.user.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.user.dao.UserDao;
import com.as.user.entity.User;
import com.as.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Newbody on 2016/3/2.
 */

@Transactional
@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, String> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    protected GenericDao<User, String> getDao() {
        return userDao;
    }
}
