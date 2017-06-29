package com.jxufe.service.iml;

import com.jxufe.dao.UserMapper;
import com.jxufe.security.vo.SecurityUser;
import com.jxufe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuburu on 2017/6/28.
 */
@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SecurityUser selectUserInfoByName(String userName) {
        return userMapper.selectUserInfoByName(userName);
    }

    @Override
    public List<String> selRoleInfoByName(String userName) {
        return userMapper.selRoleInfoByName(userName);
    }
}
