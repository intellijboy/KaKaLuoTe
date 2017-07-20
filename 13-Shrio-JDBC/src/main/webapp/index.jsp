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
    <title>欢迎</title>
</head>
<body>

<h2>欢迎页面 </h2>
<h1><shrio:user>记住我：用户</shrio:user><shrio:guest>没有记住我：来宾</shrio:guest></h1>
<h2><a href="/admin/index">访问:/admin/index</a></h2>
<h2><a href="/admin/create">访问:/admin/create</a></h2>
<h2><a href="/admin/delete">访问:/admin/delete</a></h2>
<h2><a href="/admin/query">访问:/admin/query</a></h2>
<h2><a href="/admin/update">访问:/admin/update</a></h2>
<h2><a href="/user/index">访问:/user/index</a></h2>
</body>
</html>
