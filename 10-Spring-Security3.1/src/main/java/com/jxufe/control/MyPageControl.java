package com.jxufe.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by liuburu on 2017/6/19.
 */
@Controller
@RequestMapping("demo")
public class MyPageControl {

    @RequestMapping("session")
    public String toSessionPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("name","刘卜铷");
        return "redirect:/session_page.jsp";
    }

    @RequestMapping("/curUserName")
    @ResponseBody
    public Object getCurJson(){
        Object curUser = getCurrentUsername2();
        return  curUser;
    }

    @RequestMapping("/myLogout")
    public String logoutToLoginPage(){
        System.out.println("===>This is my Logout Control.....");
        return "redirect:/test.jsp";
    }

    public String getCurrentUsername2() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
