package com.jxufe.security.manager;

import com.jxufe.dao.RoleFunctionMapper;
import com.jxufe.dao.RoleMapper;
import com.jxufe.security.dto.FunctionRoleDTO;
import com.jxufe.security.vo.SecurityRole;
import com.jxufe.service.FunctionRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by liuburu on 2017/6/28.
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

    private FunctionRoleService functionRoleService;


    /**
     * 存放数据库 资源-角色 关联关系
     */
    private Map<String, Collection<ConfigAttribute>> reCollectionMap;
    ;

    public MySecurityMetadataSource() {
        System.out.println("***********我是构造函数MySecurityMetadataSource");
    }

    @PostConstruct
    public void init() {
        //从数据库加载用户权限数据
        loadResourseMap();
        System.out.println("*********我是PostConstruct");
    }

    private void loadResourseMap() {
        Collection<FunctionRoleDTO> functionRoleDTOS = functionRoleService.selAllResourseRole();
        reCollectionMap = transColToMap(functionRoleDTOS);
//        Collection<ConfigAttribute> configCollection1 = new ArrayList<>();
//        Collection<ConfigAttribute> configCollection2 = new ArrayList<>();
//        Collection<ConfigAttribute> configCollection3 = new ArrayList<>();
//        Collection<ConfigAttribute> configCollection4 = new ArrayList<>();
//        ConfigAttribute configAttribute1 = new SecurityConfig("ROLE_ADMIN_CREATE");
//        ConfigAttribute configAttribute2 = new SecurityConfig("ROLE_ADMIN_QUERY");
//        ConfigAttribute configAttribute3 = new SecurityConfig("ROLE_ADMIN_DELETE");
//        ConfigAttribute configAttribute4 = new SecurityConfig("ROLE_ADMIN_UPDATE");
//        configCollection1.add(configAttribute1);
//        configCollection2.add(configAttribute2);
//        configCollection3.add(configAttribute3);
//        configCollection4.add(configAttribute4);
//        reCollectionMap.put("/admin/create", configCollection1);
//        reCollectionMap.put("/admin/query", configCollection2);
//        reCollectionMap.put("/admin/delete", configCollection3);
//        reCollectionMap.put("/admin/update", configCollection4);
    }

    private Map<String, Collection<ConfigAttribute>> transColToMap(Collection<FunctionRoleDTO> functionRoleDTOS) {
        Map<String, Collection<ConfigAttribute>> resultMap = new HashMap<>();
        Iterator<FunctionRoleDTO> $it = functionRoleDTOS.iterator();
        while($it.hasNext()){
            FunctionRoleDTO next = $it.next();
            resultMap.put(next.getUrl(),next.getRoles());
        }
        logger.info("加载系统资源权限成功===>{}",resultMap);
        return resultMap;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object url) throws IllegalArgumentException {
        //加载所有资源
        if (reCollectionMap.containsKey(url)) {
            return reCollectionMap.get(url);
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void setFunctionRoleService(FunctionRoleService functionRoleService) {
        this.functionRoleService = functionRoleService;
        System.out.println("*********访问器setFunctionRoleService");
    }
    public FunctionRoleService getFunctionRoleService() {
        return functionRoleService;
    }

}
