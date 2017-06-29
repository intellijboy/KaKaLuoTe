package com.jxufe.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liuburu on 2017/6/19.
 */
@Controller
@RequestMapping("security")
public class LoginControl {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(
            @RequestParam("username") String userName,
            @RequestParam("password") String password) {
        String redirectURL = "/" + userName + "/index";
        System.out.println("redirectURL==>" + redirectURL);
        return redirectURL;
    }


    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String loginTargetUrl(Model model) {
        model.addAttribute("userName", "test name");
        return "success";
    }


    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public String toAuthFailPage() {
        return "auth_fail";
    }

}
