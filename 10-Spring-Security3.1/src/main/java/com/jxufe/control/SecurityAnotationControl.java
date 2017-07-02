package com.jxufe.control;

import com.jxufe.entity.User;
import com.jxufe.security.vo.SecurityUser;
import com.jxufe.service.UserService;
import com.jxufe.service.iml.AnotationUserServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuburu on 2017/6/30.
 */
@RequestMapping("anotaion")
@Controller
public class SecurityAnotationControl {

   @Qualifier("anotationUserServiceIml")
    @Autowired
    private UserService userService;

    @RequestMapping("/create")
    @ResponseBody
    public Object createMethod(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setEmail("test@qq.com");
        int result = userService.insertSelective(user);
        return "createMethod:"+result;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object deleteMethod(){
        int result = userService.deleteByPrimaryKey(5);
        return "deleteMethod"+result;
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object queryMethod(){
        SecurityUser securityUser = userService.selectByPrimaryKey(1);
        return "queryMethod:"+securityUser;
    }

    @ResponseBody
    public Object updateMethod(){
        User user = new User();
        user.setId(1);
        user.setAge(100);
        int reuslt = userService.updateByPrimaryKey(user);
        return "updateMethod:"+reuslt;
    }


    @RequestMapping("/all")
    @ResponseBody
    public Object PermitAll(){
        userService.permitAll();
        return "PermitAll";
    }

    @RequestMapping("/deny")
    @ResponseBody
    public Object deny(){
        userService.denyAll();
        return "denyALL";
    }

}
