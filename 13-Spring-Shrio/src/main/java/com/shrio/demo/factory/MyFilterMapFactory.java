package com.shrio.demo.factory;

import java.util.LinkedHashMap;

/**
 * 权限过滤Map工厂
 *
 * @author liuburu
 * @create 2017/07/19
 **/
public class MyFilterMapFactory {

    /**
     *
     * 第一次匹配优先规则
     * @return
     */
    public LinkedHashMap<String,String> loadResourceAccessRules(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/shrio/login","anon");
        map.put("/shrio/logout","logout");
        map.put("/login.jsp","anon");
        map.put("/login/success","user");
        map.put("/user/index","user");
        map.put("/**","authc");
        return map;
    }
}
