<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    int num = 1000;
    pageContext.setAttribute("num",num);
%>
<html>
<head>
    <title>首页</title>
    <link href="/css/common-page.css" rel="stylesheet">
</head>
<body>
<div class="headBox">
    <jsp:include page="share/share_head.jsp">
        <jsp:param name="userName" value="卡卡罗特444"></jsp:param>
    </jsp:include>
</div>
<div>
    <div class="leftBox">
        <jsp:include page="share/share_left.jsp"></jsp:include>
    </div>
    <h1>
        Num=<c:out value="${num}"></c:out>
    </h1>
    <div class="centerBox">
        <div class="centerMain">
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
            <h1>首页</h1>
        </div>
        <div class="footerBox">
            <jsp:include page="share/share_footer.jsp"></jsp:include>
        </div>
    </div>
</div>


</body>
</html>
