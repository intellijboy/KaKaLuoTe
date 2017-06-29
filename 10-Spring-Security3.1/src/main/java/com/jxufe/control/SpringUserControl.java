package com.jxufe.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuburu on 2017/6/19.
 */
@Controller
@RequestMapping("user")
public class SpringUserControl {

    @RequestMapping("/index")
    public String toUserIndexPage(Model model, HttpServletRequest request){
        return "user/index";
    }
}
