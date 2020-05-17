<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/5
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import=" com.opensymphony.xwork2.ActionContext" %>
<script type="text/javascript" src="css/jquery-1.6.2.min.js"></script>
<!doctype html>
<html lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="../css/wrong/main.css">
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <![endif]-->
</head>
<body>

<div id="wrapper">
    <div id="main">
        <header id="header">
        </header>
        <div id="content">
            <h3 style="text-align: center">书店大厅</h3>
            <div class="utilities">
                <p>
                <form method="post" action="bookList">
                <select id="testSelect" name="testSelect">
                    <option value ="book">book</option>
                    <option value ="study">study</option>
                    <option value="travel">travel</option>
                    <option value="other">other</option>
                    <option value ="english">english</option>
                    <option value ="math">math</option>
                    <option value="chinese">chinese</option>
                    <option value="skill">skill</option>
                    <option value="novel">novel</option>
                </select>
                <input type="submit" class="button right" name="commit" value="进入书店" ></input>

                </form>

                </p>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
