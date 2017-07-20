package com.shrio.demo.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuburu on 2017/7control/9.
 */
@Controller
@RequestMapping("/login")
public class NormalPageControl {

    @RequestMapping("/success")
    public String toSuccessPage(){
        return "success";
    }
}
