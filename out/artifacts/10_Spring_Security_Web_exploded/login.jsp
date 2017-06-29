<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆首页</title>
</head>
<body>
<div style="background: pink;margin: 0 auto">
    <form action="/security/login" method="post" style="margin: 0 auto">
        <table>
            <tr>
                <td>${param.error}默认登陆：</td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" name="username" value="quuser">
                </td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;码：</td>
                <td>
                    <input type="text" name="password" value="123456">
                </td>
            </tr>
            <tr>
               <td>
                   <input type="hidden" name="${requestScope._csrf.parameterName}" value="${_csrf.token}" />
               </td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox" name="_spring_security_remember_me" /> 记住我
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="提交">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
