<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center">
    <form action="/validate" method="post">
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" value="10086" name="userId"></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" value="刘卜铷" name="userName"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="text" value="123456" name="userPassword"></td>
            </tr>
            <tr>
                <td>签名</td>
                <td><input type="text" value="曾经许下的诺言" name="signature"></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text" value="1099501218@qq.com" name="email"></td>
            </tr>
            <tr>
                <td>性别</td>
                <td><input type="text" value="1" name="sex"></td>
            </tr>
            <tr>
                <td>生日</td>
                <td><input type="text" value="1994-11-12" name="birthday"></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"></td>
            </tr>
        </table>
        <br><br>
        <fmt:message key="i18n.username"></fmt:message>
        <br><br>
        <fmt:message key="i18n.password"></fmt:message>
        <br><br>
    </form>
</div>

</body>
</html>
