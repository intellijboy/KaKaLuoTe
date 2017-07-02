package com.jxufe.service.iml;

import com.jxufe.dao.UserMapper;
import com.jxufe.entity.User;
import com.jxufe.security.vo.SecurityUser;
import com.jxufe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Created by liuburu on 2017/6/28.
 */
@Service
public class XmlUserServiceIml implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SecurityUser selectUserInfoByName(String userName) {
        return userMapper.selectUserInfoByName(userName);
    }

    @Override
    public List<String> selectRoleInfoByName(String userName) {
        return userMapper.selRoleInfoByName(userName);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public SecurityUser selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    public void permitAll(){
        System.out.println("所有的用户都可以访问我...");
    }

    public void denyAll(){
        System.out.println("拒绝所有用户访问...");
    }
}
