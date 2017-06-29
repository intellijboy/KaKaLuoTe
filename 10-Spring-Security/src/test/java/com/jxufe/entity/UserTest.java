package com.jxufe.entity;

import com.jxufe.dao.UserMapper;
import com.jxufe.dao.UserRoleMapper;
import com.jxufe.security.vo.SecurityUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.List;

/**
 * Created by liuburu on 2017/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class UserTest {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private UserRoleMapper userRoleMapper;

    @Autowired
    private SecurityUser securityUser;

    @Before
    public void before(){
        securityUser = userMapper.selectByPrimaryKey(1);
        securityUser = userMapper.selectByPrimaryKey(2);
        List<String> stringList = userMapper.selRoleInfoByName("quuser");
        System.out.println(stringList);
    }
    @Test
    public void getName() throws Exception {
//        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
//        System.out.println(authorities);
    }

//    @Test
//    public void getNames() throws Exception {
//        List<String> quadmin = userRoleMapper.selRoleInfoByName("quadmin");
//        for (String t:quadmin){
//            System.out.println(t);
//        }
//    }

    @Test
    public void testUser(){
//        User user = userMapper.selectByPrimaryKey(1);
//        SecurityUser securityUser = (SecurityUser) user;
//        System.out.println(securityUser.getAuthorities());
    }


    @Test
    public void testSelectUserByName(){
//        User user  = userMapper.selectUserByName("quuser");
//        System.out.println(user);
    }

}