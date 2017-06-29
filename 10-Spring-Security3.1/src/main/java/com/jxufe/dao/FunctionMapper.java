package com.jxufe.dao;

import com.jxufe.entity.Function;
import com.jxufe.security.dto.FunctionRoleDTO;
import com.jxufe.security.vo.SecurityUser;
import org.springframework.security.access.ConfigAttribute;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

}