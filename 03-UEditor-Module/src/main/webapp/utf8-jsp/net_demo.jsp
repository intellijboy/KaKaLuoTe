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
    <script type="text/javascript" charset="utf-8" src="/utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/utf8-jsp/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div {
            width: 100%;
        }
    </style>
</head>
<body>
<%--图片上传插件begin--%>
<script type="text/plain" id="upload_ue"></script>
<%--图片上传插件begin--%>
<div>
    <%--单独图片上传表单begin--%>
    <h1>单独图片上传</h1>
    <input type="text" id="picture" name="cover"/>
    <a href="javascript:void(0);" onclick="upImage();">上传图片</a>
    <%--单独图片上传表单end--%>
    <h1>单独文件上传</h1>
    <%--单独文件上传表单begin--%>
    <input type="text" id="file"/>
    <a href="javascript:void(0);" onclick="upFiles();">上传文件</a>
    <%--单独文件上传表单begin--%>
</div>
</div>
</body>
</html>

<script type="text/javascript">
    //实例化编辑器
    var _editor = UE.getEditor('upload_ue', {
        toolbars: [
            [
                'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', '|',
                'forecolor', 'backcolor', '|',
                'customstyle', 'paragraph', 'fontfamily', 'fontsize'
            ],
            [
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
                'link', 'unlink', '|',
                'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'help'
            ]
        ],
        autoHeightEnabled: true,
        autoFloatEnabled: true,
        initialContent: '欢迎使用ueditor!!!!', /*初始化内容*/
        enableContextMenu: false, /*右键菜单*/
        elementPathEnabled: false, /*元素路径*/
        autosave: false, /*自动保存关闭*/
        emotionLocalization: true/*启用本地表情*/
    });


    _editor.ready(function () {
        //设置编辑器不可用
        _editor.setDisabled();
        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
        _editor.hide();
        //侦听图片上传
        _editor.addListener('beforeInsertImage', function (t, arg) {
            ////arg就是上传图片的返回值，是个数组，如果上传多张图片，请遍历该值。
            alert("图片上传");
            console.log("图片上传事件");
            console.log(arg);
            console.log(t);
        });
        //侦听文件上传，取上传文件列表中第一个上传的文件的路径
        _editor.addListener('afterUpfile', function (t, arg) {
            alert("图片上传");
            //$("#file").attr("value", _editor.options.filePath + arg[0].url);
            console.log("文件上传事件");
            console.log(arg);
            console.log(t);
        });

    });
    //弹出图片上传的对话框
    function upImage() {
        var myImage = _editor.getDialog("insertimage");
        myImage.open();
    }
    //弹出文件上传的对话框
    function upFiles() {
        var myFiles = _editor.getDialog("attachment");
        myFiles.open();
    }
</script>