package com.shrio.demo.control;

import com.shrio.demo.entity.ShrioUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Shrio用户登录控制器
 *
 * @author liuburu
 * @create 2017/07/16
 **/
@Controller
@RequestMapping("shrio")
public class ShrioLoginControl {


    @RequestMapping(value = "login",method = {RequestMethod.POST})
    public String shiroLogin(ShrioUser shrioUser,@RequestParam(value = "remember",defaultValue = "0") Boolean remember) {
        Subject currentUser = SecurityUtils.getSubject();
        //2.判断是否认证过，否则进行重新认证
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(shrioUser.getUsername(), shrioUser.getPassword());
            //1.设置rememberMe
            token.setRememberMe(remember);
            currentUser.login(token);
        }
        return "redirect:/login/success";
    }


}
