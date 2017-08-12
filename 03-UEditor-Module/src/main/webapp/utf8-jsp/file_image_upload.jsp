<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <title>完整demo666</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="/utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/utf8-jsp/ueditor.custom.attachement.js"></script>
    <script type="text/javascript" charset="utf-8" src="/utf8-jsp/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div {
            width: 100%;
        }
    </style>
</head>
<body>
<div>
    <%--图片上传插件begin--%>
    <div name="content" id="editor" type="text/plain" style="width:700px;height:200px;" ></div>
    <%--图片上传插件begin--%>
        <h1>单独图片上传</h1>
    <%--单独图片上传表单begin--%>
    <form name="upfile" action="/ueditor/form" method="post">
        <input type="hidden" name="fileids" value=""/><%--文件ID列表JSON格式字符串，加密--%>
        <input id="picture" type="text" name="filenames" value="">
        <input type="button" value="选择文件" onclick="upImage()">
        <input type="submit" value="提交">
    </form>
    <%--单独图片上传表单begin--%>

        <h1>单独文件上传</h1>

    <%--单独文件上传表单begin--%>
    <form name="upfile" action="/ueditor/form" method="post">
        <input type="hidden" name="fileids" value=""/><%--文件ID列表JSON格式字符串，加密--%>
        <input id="picture" type="text" name="filenames" value="">
        <input type="button" value="选择文件" onclick="upFiles()">
        <input type="submit" value="提交">
    </form>
    <%--单独文件上传表单begin--%>
</div>
</div>
</body>
</html>

<script type="text/javascript">
    //实例化编辑器
    var _editor = UE.getEditor('editor', {
        isShow: true,
        focus: false,
        enableAutoSave: false,
        autoSyncData: false,
        autoFloatEnabled:false,
        wordCount: false,
        sourceEditor: null,
        scaleEnabled:true,
        toolbars: [["attachment"]]
    });
    _editor.ready(function () {
        //侦听图片上传
        _editor.addListener('beforeInsertImage', function (t, arg) {
            ////arg就是上传图片的返回值，是个数组，如果上传多张图片，请遍历该值。
            console.log("图片上传事件");
            console.log(arg);
            console.log(t);
        });
        //侦听文件上传，取上传文件列表中第一个上传的文件的路径
        _editor.addListener('afterUpfile', function (t, arg) {
            //$("#file").attr("value", _editor.options.filePath + arg[0].url);
            console.log(arg[0].url);
            console.log("文件上传事件");
            console.log(arg);
            console.log(t);
        });
        _editor.addListener('selectionchange',function(){
//            console.log("选区已经变化！");
        })
    });
    //弹出图片上传的对话框
    function upImage() {
        var myImage = _editor.getDialog("insertimage");
        myImage.title = "自定义图片上传";
        myImage.open();
    }
    //弹出文件上传的对话框
    function upFiles() {
        var myFiles = _editor.getDialog("attachment");
        myFiles.title = "自定义文件上传";
        myFiles.open();
    }

</script>