package com.shrio.demo.service;

import com.shrio.demo.entity.SecUser;

import java.util.List;
import java.util.Set;

/**
 * 用户服务接口
 *
 * @author liuburu
 * @create 2017/07/19
 **/
public interface SecUserService {

    SecUser queryByUserName(String userName);

    Set<String> queryRoles(String userName);

    List<String> queryPermissions(String userName);

}
