package com.jxufe.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuburu on 2017/6/6.
 */
@Controller
@RequestMapping("jdbc")
public class JDBCControl {

    @RequestMapping("/list")
    public String toListPage(){
        return "list";
    }
}
