package com.jxufe.service.iml;

import com.jxufe.dao.RoleFunctionMapper;
import com.jxufe.security.dto.FunctionRoleDTO;
import com.jxufe.service.FunctionRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by liuburu on 2017/6/28.
 */
@Service
public class FunctionRoleServiceIml implements FunctionRoleService{

    @Autowired
    private RoleFunctionMapper roleFunctionMapper;

    @Override
    public Collection<FunctionRoleDTO> selAllResourseRole() {
        return roleFunctionMapper.selAllResourseRole();
    }
}
