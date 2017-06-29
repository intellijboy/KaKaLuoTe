package com.jxufe.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuburu on 2017/6/19.
 */
@Controller
@RequestMapping("admin")
public class SpringAdminControl {
    @RequestMapping("/index")
    public String toAdminIndexPage() {
        return "admin/index";
    }

    @RequestMapping("/create")
    public String toAdminCreatePage() {
        return "admin/create";
    }

    @RequestMapping("/delete")
    public String toAdminDeletePage() {
        return "admin/delete";
    }

    @RequestMapping("/query")
    public String toAdminQueryPage() {
        return "admin/query";
    }

    @RequestMapping("/update")
    public String toAdminUpdatePage() {
        return "admin/update";
    }
}
