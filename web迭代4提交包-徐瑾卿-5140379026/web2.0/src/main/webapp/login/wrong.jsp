<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/6
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" import=" com.opensymphony.xwork2.ActionContext" %>

<!doctype html>
<html lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="css/wrong/main.css">
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<div id="wrapper">
    <div id="main">
        <header id="header">
        </header>
        <div id="content">
            <p> ${requestScope.wmessage}</p>
            <div class="utilities">
                <a class="button right" href=${requestScope.raddress}>返回...</a>
                <div class="clear"></div>
            </div>
        </div>


    </div>
    </div>

</body>
</html>
