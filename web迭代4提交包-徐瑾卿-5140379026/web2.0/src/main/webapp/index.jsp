<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2017/3/17
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/wrong/main.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="/js/jquery.i18n.properties-1.0.9.js"></script>

</head>
<body>
<div id="wrapper">
    <div id="main">
        <header id="header">
        </header>
        <div id="content">
            <p></p>
            <p></p>
            <h1 style="text-align:center" id="h1_welcome"></h1>
            <p></p>

            <div class="utilities">



                <a href="login/welcomeuser.jsp" class="button left" id="a_login"></a>
                <a href="register.jsp" class="button middle" id="a_register"></a>
                <a href="chatroom.jsp" class="button middle" id="a_chatroom"></a>
                <a href="/admin/welcomeadmin.jsp" class="button middle" id="a_admin"></a>
                <select id="lan" style="margin-left: 40px">
                    <option value ="en" id="option_en"></option>
                    <option value ="zh" id="option_zh"></option>

                </select>
                <button  class="button-right" onclick="changelanguage()" id="button_change"></button>

                <div class="clear"></div>

            </div>

        </div>


    </div>
</div>

</body>
</html>
<script>
    var language=null;
    function changelanguage(){
        language=document.getElementById("lan").value;
        console.log(language);
        var lan2=language;
        jQuery.i18n.properties({
            name: 'strings_'+lan2, //资源文件名称
            path: '/i18n/', //资源文件路径
            mode: 'map', //用Map的方式使用资源文件中的值
            language: lan2,
            callback: function () {//加载成功后设置显示内容
                $('#h1_welcome').html($.i18n.prop('Welcome'));
                $('#a_login').html($.i18n.prop('Login'));
                $('#a_register').html($.i18n.prop('Register'));
                $('#a_chatroom').html($.i18n.prop('Chatroom'));
                $('#a_admin').html($.i18n.prop('Admin'));
                $('#button_change').html($.i18n.prop('Change'));
                $('#option_en').html($.i18n.prop('En'));
                $('#option_zh').html($.i18n.prop('Ch'));
            }
        });
        var lan_select = document.getElementById('lan');
        if(language=="zh") {
            lan_select.style.marginLeft = "120px";
        }
        else{
            console.log("ok2");
            lan_select.style.marginLeft = "40px";
        }
    }

    var lan=language||navigator.language;
    console.log(lan);
    jQuery.i18n.properties({
            name: 'strings', //资源文件名称
            path: '/i18n/', //资源文件路径
            mode: 'map', //用Map的方式使用资源文件中的值
            language: lan,
            callback: function () {//加载成功后设置显示内容
                $('#h1_welcome').html($.i18n.prop('Welcome'));
                $('#a_login').html($.i18n.prop('Login'));
                $('#a_register').html($.i18n.prop('Register'));
                $('#a_chatroom').html($.i18n.prop('Chatroom'));
                $('#a_admin').html($.i18n.prop('Admin'));
                $('#button_change').html($.i18n.prop('Change'));
                $('#option_en').html($.i18n.prop('En'));
                $('#option_zh').html($.i18n.prop('Ch'));
            }
        });

    var lan_select = document.getElementById('lan');
    if(lan=="zh-CN") {
        lan_select.style.marginLeft = "120px";
    }
    else{
        lan_select.style.marginLeft = "40px";
    }
</script>