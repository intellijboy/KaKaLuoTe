package com.jxufe.email;

import org.apache.commons.mail.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liuburu on 2017/4/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-email.xml"})
public class EmailTest {

    @Autowired
    private SimpleEmail simpleEmail;

    @Autowired
    private MultiPartEmail multiPartEmail;

    @Autowired
    private HtmlEmail htmlEmail;


    /*
    * 发送纯文本邮件
    * */
    @Test
    public void sendSimpleText() throws EmailException {
     // simpleEmail.addTo("1099501218@qq.com","liuburu@outlook.com","liuburu007@gmail.com","15270998540@163.com");
        simpleEmail.setFrom("15270998540@163.com","友爱空间");
        simpleEmail.addTo("1099501218@qq.com","刘卜铷");
        simpleEmail.setSubject("尊敬的友爱用户");
        simpleEmail.setMsg("本次的激活码：1099501218");
        simpleEmail.send();
    }

    @Test
    public void sendAttachments() throws EmailException, MalformedURLException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        //attachment.setPath("C:/Users/liubu/Desktop/logo.jpg");
        attachment.setURL(new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493496837106&di=b1e037b124b8492cbbeb6a54fd9a4703&imgtype=0&src=http%3A%2F%2Favatar.csdn.net%2Fblogpic%2F20141020152834593.jpg"));
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        attachment.setName("John.png");
        // Create the email message
        multiPartEmail.addTo("1099501218@qq.com", "John Doe");
        multiPartEmail.setFrom("15270998540@163.com", "刘卜铷");
        multiPartEmail.setSubject("The picture");
        multiPartEmail.setMsg("Here is the picture you wanted");

        // add the attachment
        multiPartEmail.attach(attachment);

        // send the email
        multiPartEmail.send();
    }


    @Test
    public void sendHtml() throws EmailException, MalformedURLException {
        // Create the email message
        htmlEmail.setFrom("15270998540@163.com","友爱管理员");
        htmlEmail.addTo("1099501218@qq.com", "卡卡罗特");
        htmlEmail.setSubject("Test email with inline image");

        // set the html message
        htmlEmail.setHtmlMsg("<html><h1><font color='red'>The apache logo </font></h1></html>");

        // set the alternative message
        htmlEmail.setTextMsg("Your email client does not support HTML messages");

        // send the email
        htmlEmail.send();
    }


}
