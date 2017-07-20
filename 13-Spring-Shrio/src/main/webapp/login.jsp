<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2017/6/19
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<body>

<h2>登陆页面 </h2>
<h1><shrio:user>记住我：用户</shrio:user><shrio:guest>没有记住我：来宾</shrio:guest></h1>
<form action="/shrio/login" method="post">
    <input type="text" value="kakaluote" name="username"><br>
    <input type="password" value="123456" name="password"><br>
    <input type="checkbox" name="remember" value="1">记住我？<br>
    <input type="submit" value="提交"><br>
</form>
</body>
</html>
