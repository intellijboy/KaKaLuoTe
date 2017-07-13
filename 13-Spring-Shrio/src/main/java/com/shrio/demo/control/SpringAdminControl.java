package com.shrio.demo.control;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuburu on 2017/6/19.
 */
@Controller
@RequestMapping("admin")
public class SpringAdminControl {


    Logger logger = Logger.getLogger(SpringAdminControl.class.getName());

    @RequestMapping("/index")
    public String toAdminIndexPage() {
        logger.warn("toAdminIndexPage");
        return "admin/index";
    }

    @RequestMapping("/create")
    public String toAdminCreatePage() {
        logger.debug("toAdminCreatePage");
        logger.warn("toAdminCreatePage");
        logger.error("toAdminCreatePage");
        return "admin/create";
    }

    @RequestMapping("/delete")
    public String toAdminDeletePage() {
        logger.warn("toAdminDeletePage");
        return "admin/delete";
    }

    @RequestMapping("/query")
    public String toAdminQueryPage() {
        logger.warn("toAdminQueryPage");
        return "admin/query";
    }

    @RequestMapping("/update")
    public String toAdminUpdatePage() {
        logger.warn("toAdminUpdatePage");
        return "admin/update";
    }
}
