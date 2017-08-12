package com.jxufe.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuburu on 2017/6/6.
 */
@Controller
@RequestMapping("jdbc")
public class JDBCControl {

    private Logger logger = LoggerFactory.getLogger(JDBCControl.class);

    @RequestMapping("/list")
    public String toListPage() throws Exception {
        logger.info("进入==>列表页面");
        System.out.println("调用业务逻辑");
        int i=10;
        if(i==10){
            logger.info("程序发生了异常:{}",new Exception("我草你妈了"));
            throw new Exception("我草你妈了");
        }
        logger.info("退出==>列表页面");
        return "list";
    }
}
