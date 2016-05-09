package com.as.user.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.user.dao.UserDao;
import com.as.user.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Newbody on 2016/3/1.
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateGenericDaoImpl<User, String> implements UserDao {
}
