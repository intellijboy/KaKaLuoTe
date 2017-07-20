<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2017/6/19
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title>Main页面</title>
</head>
<body style="text-align: center;background: blanchedalmond">
<h2><a href="/demo/curUserName">登录认证用户访问界面</a></h2>
<h2><a href="/user/index">访问:/user/index</a></h2>
<h2><a href="/user/permission">访问:/user/permission</a></h2>
<h2><a href="/admin/index">访问:/admin/index</a></h2>
<h2><a href="/admin/create">访问:/admin/create</a></h2>
<h2><a href="/admin/delete">访问:/admin/delete</a></h2>
<h2><a href="/admin/query">访问:/admin/query</a></h2>
<h2><a href="/admin/update">访问:/admin/update</a></h2>
<h2><a href="/shrio/logout">注销</a></h2>
<%--菜单--%>
<!--注释看的见吗-->
<hr>
<h1>用户名称:<shrio:principal property="username"></shrio:principal></h1>
<h1>authenticated:<shrio:authenticated>你已经登陆</shrio:authenticated></h1>
<h1><shrio:user>记住我：用户</shrio:user><shrio:guest>没有记住我：来宾</shrio:guest></h1>
<h1>
<shrio:hasRole name="admin">Admin角色显示</shrio:hasRole>
<shrio:hasRole name="user">User角色显示</shrio:hasRole>
</h1>
<h2><shrio:hasPermission name="sys:user:add">添加权限</shrio:hasPermission></h2>
<h2><shrio:hasPermission name="sys:user:delete">删除权限</shrio:hasPermission></h2>
<h2><shrio:hasPermission name="sys:user:update">修改权限</shrio:hasPermission></h2>
<h2><shrio:hasPermission name="sys:user:query">查询权限</shrio:hasPermission></h2>
</body>
</html>
