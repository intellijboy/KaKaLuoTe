package com.shrio.demo.service.impl;

import com.shrio.demo.dao.SecUserMapper;
import com.shrio.demo.entity.SecUser;
import com.shrio.demo.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 用户服务接口实现类
 *
 * @author liuburu
 * @create 2017/07/19
 **/
@Service
public class SecUserServiceIml implements SecUserService {
    @Autowired
    private SecUserMapper secUserMapper;

    @Override
    public SecUser queryByUserName(String userName) {
        return secUserMapper.selectSecUser(userName);
    }

    @Override
    public Set<String> queryRoles(String userName) {
        return secUserMapper.selectRoles(userName);
    }

    @Override
    public List<String> queryPermissions(String userName) {
        return secUserMapper.selectPermissions(userName);
    }
}
