<%@ page contentType="text/html;charset=UTF-8" language="java" import=" com.opensymphony.xwork2.ActionContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.module.bean.Book,java.util.List" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<link href="css/broswer.css" rel="stylesheet" type="css" media="all" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="css/icono.min.css">
<link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
<!doctype html>
<html lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="css/wrong/main.css">
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>

    <![endif]-->
</head>
<link rel="stylesheet" href="../css/icono.min.css">
<body>
<div id="wrapper">

    <div id="main">
        <header id="header">

        </header>
        <div id="content">
            <p> ${requestScope.wmessage}</p>
            <div class="utilities">
                <p>
                <a class="button right" href=${requestScope.raddress}>返回...</a>

               </p>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
