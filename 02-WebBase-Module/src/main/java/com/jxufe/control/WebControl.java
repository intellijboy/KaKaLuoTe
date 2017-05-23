package com.jxufe.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuburu on 2017/5/9.
 */
@Controller
@RequestMapping("include")
public class WebControl {

    @RequestMapping("/index")
    public String toIndexPage(){
        System.out.println("to index page");
        return "index";
    }

    @RequestMapping("/wenda")
    public String toWendaPage(){
        return "wenda";
    }

    @RequestMapping("/article")
    public String toArticlePage(){
        return "article";
    }
}
