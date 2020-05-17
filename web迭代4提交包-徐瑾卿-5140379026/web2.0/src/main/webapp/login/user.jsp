<%@ page import="com.module.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/7
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="css/wrong/main.css">
<link rel="stylesheet" href="../css/icono.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>

<body>
<div id="wrapper">
    <div id="main">
        <header id="header">
        </header>
        <div id="content">
            <h3 style="text-align: center">个人中心</h3>
            <a class="button right" href="userLogout">登出</a>
            <div class="utilities">

                <%
                    String   username=(String)request.getAttribute("username");
                    String phone=(String)request.getAttribute("phone");
                    String email=(String)request.getAttribute("email");
                    Integer score=(Integer)request.getAttribute("score");
                %>

                <p name="bookname" ><label>姓名:</label><input  type="text" readonly="readonly"   value="<%=username%>" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none"/></p>
                <p name="price"><label>电话:</label><input type="text"  readonly="readonly"  value="<%=phone%>" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none "/></p>
                <p name="category"><label>邮箱:</label><input type="text"  readonly="readonly"  value="<%=email%>" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none "/></p>
                <p name="score"><label>积分:</label><input type="text"  readonly="readonly"  value="<%=score%>" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none "/></p>
                <form method="post" action="orderview">
                    <input type="submit" class="button right" name="commit" value="查看订单"/>
                </form>
                <div class="clear"></div>
            </div>
        </div>


    </div>
</div>




<div id="foot">
</div>

</body>
</html>

<script>


</script>