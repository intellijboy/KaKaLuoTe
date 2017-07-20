package com.shrio.demo.dao;

import com.shrio.demo.entity.SecUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * ${DESCRIPTION}
 *
 * @author liuburu
 * @create 2017/07/20
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SecUserMapperTest {
    @Test
    public void selectSecUser() throws Exception {
        SecUser secUser = userMapper.selectSecUser("admin");
        System.out.println(secUser);
    }

    @Test
    public void selectPermissions() throws Exception {
        List<String> stringList = userMapper.selectPermissions("admin");
        for (String permit:stringList){
            System.out.println(permit);
        }
    }


    @Test
    public void selectRoles() throws Exception {
        Set<String> roles = userMapper.selectRoles("admin");
        for (String roleName:roles){
            System.out.println(roleName);
        }
    }

    @Autowired
    private SecUserMapper userMapper;

}