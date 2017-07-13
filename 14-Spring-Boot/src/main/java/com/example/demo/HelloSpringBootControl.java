package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World Spring Boot
 *
 * @author liuburu
 * @create 2017/07/10
 **/
@RestController("springboot")
public class HelloSpringBootControl {

    @RequestMapping("hello")
    public String sayHello(){
        return "hello world Spring Boot,I coming!";
    }
}
