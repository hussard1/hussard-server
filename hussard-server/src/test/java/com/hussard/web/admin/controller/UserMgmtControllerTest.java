package com.hussard.web.admin.controller;

import com.hussard.web.base.user.service.UserService;
import com.hussard.web.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by hussard on 2016. 7. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserMgmtControllerTest {

    @Autowired
    private UserService userService;

    @Test
    public void getList(){

        assertNotNull(userService.getList(searchWord, pageSize, pageNum));
//        assertEquals();
    }
}
