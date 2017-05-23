package com.jxufe.control;

import com.alibaba.fastjson.JSON;
import com.jxufe.test.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by liuburu on 2017/5/10.
 */
@Controller
public class MyControl {

    @RequestMapping(value = "validate",method = {RequestMethod.POST})
    public String hibernateValidatorHandler(@Valid User user,Errors errors){
        System.out.println(JSON.toJSONString(user));
        if(errors.getErrorCount() > 0){
            System.out.println("出错了!");
            for(FieldError error:errors.getFieldErrors()){
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }
        }
        return "success";
    }
}
