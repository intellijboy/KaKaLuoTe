package com.jxufe.dao;

import com.jxufe.entity.UserRole;
import com.jxufe.security.dto.FunctionRoleDTO;
import com.jxufe.security.vo.SecurityRole;
import com.jxufe.security.vo.SecurityUser;
import com.jxufe.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liuburu on 2017/6/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class FunctionMapperTest {

    @Autowired
    private RoleFunctionMapper roleFunctionMapper;

    @Autowired
    private UserService userService;

    @Test
    public void selAllResourseRole() throws Exception {
        Collection<FunctionRoleDTO> collections = roleFunctionMapper.selAllResourseRole();
        Iterator<FunctionRoleDTO> $it = collections.iterator();
        while($it.hasNext()){
            FunctionRoleDTO next = $it.next();
            System.out.println(next.getUrl()+"--->"+next.getRoles());
        }
    }

    @Test
    public void test(){
        List<ConfigAttribute> objs = new ArrayList<>();
        objs.add(new SecurityRole());
    }
    @Test
    public void test2(){
        SecurityUser securityUser = userService.selectUserInfoByName("quuser");
        System.out.println(securityUser);
    }

}