<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2017/7/7
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>三种方法</title>
</head>
<body>
<form name="serForm" action="/springmvc/upload-1" method="post"  enctype="multipart/form-data">
    <h1>采用流的方式上传文件(每次读取1个字节)</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>
<br>
<form name="serForm" action="/springmvc/upload-2" method="post"  enctype="multipart/form-data">
    <h1>采用流的方式上传文件(每次读取1024个字节)</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>
<form name="serForm" action="/springmvc/upload-3" method="post"  enctype="multipart/form-data">
    <h1>采高效流读取上传文件(每次读取1个字节)</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>
<form name="serForm" action="/springmvc/upload-4" method="post"  enctype="multipart/form-data">
    <h1>采高效流读取上传文件(每次读取1024个字节)</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>
<form name="serForm" action="/springmvc/upload-5" method="post"  enctype="multipart/form-data">
    <h1>采用 multipartFile.transferTo(newFile)方法</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>
<form name="serForm" action="/springmvc/upload-6" method="post"  enctype="multipart/form-data">
    <h1>采用spring提供的上传文件的方法方法</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>
</body>
</html>
