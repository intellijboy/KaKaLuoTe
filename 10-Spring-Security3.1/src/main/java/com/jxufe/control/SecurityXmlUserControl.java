package com.jxufe.control;

import com.jxufe.entity.User;
import com.jxufe.security.vo.SecurityUser;
import com.jxufe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuburu on 2017/6/30.
 */
//@Controller
@RequestMapping("/user")
public class SecurityXmlUserControl {

   @Qualifier("securityXmlUserServiceIml")
    @Autowired
    private UserService userService;

    @RequestMapping("query/{id}")
    @ResponseBody
    public User queryUser(@PathVariable("id") Integer id) {
        SecurityUser securityUser = userService.selectByPrimaryKey(id);
        return securityUser;
    }

    @RequestMapping("add")
    @ResponseBody
    public int AddUser(User user) {
        int i = userService.insertSelective(user);
        return i;
    }


}
