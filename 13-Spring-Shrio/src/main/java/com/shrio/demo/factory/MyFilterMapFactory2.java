package com.shrio.demo.factory;

import java.util.LinkedHashMap;

/**
 * 权限过滤Map工厂
 *
 * @author liuburu
 * @create 2017/07/19
 **/
public class MyFilterMapFactory2 {

    /**
     *
     * 第一次匹配优先规则
     * /shrio/login = anon
     * /shrio/logout = logout
     * /login.jsp = anon
     * /login/success= user
     * /user/index= user
     * /** = authc
     * @return
     */
    public LinkedHashMap<String,String> loadResourceAccessRules(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/index.jsp","anon");
        map.put("/shrio/login","anon");
        map.put("/shrio/logout","logout");
        map.put("/login.jsp","anon");
        map.put("/login/success","roles[user]");
        map.put("/user/index","roles[user]");
        map.put("/admin/*","authc,roles[admin]");
        map.put("/**","authc");
        return map;
    }
}
