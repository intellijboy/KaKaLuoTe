<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2 style="color: rebeccapurple">我是共享导航栏</h2>
<h2>
    <a href="/include/index">首页</a>
    <a href="/include/wenda">问答</a>
    <a href="/include/article">文章</a>
    <a href="/include/index"><%=request.getParameter("userName")%></a>
    <p>用户名:${requestScope.userName}</p>
</h2>
