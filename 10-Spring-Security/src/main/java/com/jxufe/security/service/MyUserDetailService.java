package com.jxufe.security.service;

import com.jxufe.dao.UserMapper;
import com.jxufe.security.vo.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuburu on 2017/6/24.
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("{}.loadUserByUsername方法执行:参数为{}",this.getClass().getName(),userName);
        //1.查询userDetals信息进行返回
        SecurityUser securityUser = userMapper.selectUserInfoByName(userName);
        //2.把userMapper注入到SecurityUserBean中
        obtainAuthorities(userMapper,securityUser);
        return securityUser;
    }

    private void obtainAuthorities(UserMapper userMapper, SecurityUser securityUser) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<String> stringList = userMapper.selRoleInfoByName(securityUser.getUsername());
        for(String roleName:stringList){
            authorityList.add(new SimpleGrantedAuthority(roleName));
        }
        securityUser.setAuthorities(authorityList);
        logger.info("{}-->加载用户{}权限：{}成功",this.getClass().getName(),securityUser.getUsername(),authorityList);
    }
}
