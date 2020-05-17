<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2017/3/21
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="com.module.entity.Cart,java.util.List" %>
<!-- Javascript goes in the document HEAD -->
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<link rel="stylesheet" href="css/admin.css">
<style type="text/css">
    table.altrowstable {
        font-family: verdana,arial,sans-serif;
        font-size:11px;
        color:#333333;
        border-width: 1px;
        border-color: #a9c6c9;
        border-collapse: collapse;
    }
    table.altrowstable th {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
    table.altrowstable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
    .oddrowcolor{
        background-color:#d4e3e5;
    }
    .evenrowcolor{
        background-color:#c3dde0;
    }
</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" import=" com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.module.bean.Order" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
<!doctype html>
<html lang="en-US">
<head>
    <script type="text/javascript" src="css/jquery-1.6.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/css/wrong/main.css">
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<link rel="stylesheet" href="css/icono.min.css">
<div id="head" style="margin-left: 100px" >
    <br>
    <p style="text-align: center;font-size: 30px;margin-top: 20px">用户界面</p>

    <p class="signout" style="float:right">
        <a class="icono-home" href="newBookList" ></a>
        <a class="icono-cart" href="cartView" ></a>
        <a class="icono-signout" href="userLogout"></a>
        <a class="icono-disqus" href="comment.jsp"></a>
        <a class="icono-user" href="orderview"></a>

    </p>
</div>
<div id="container" style="margin-left: 100px">
    <div id="basic">
       <br>
        <br>
        <p>
        <h2 style="margin-left: 200px;font-size: 20px">成功订单</h2>
        </p>
        <table class="altrowstable" id="alternatecolor" align="center" style="margin-left:100px;margin-top: 20px">
            <tr>
                <th>date</th>
                <th>sumnumber</th>
                <th>sumprice</th>
            </tr>
            <tr>
                    <%
              List<Order> myorder= (List<Order>)request.getAttribute("myorder");
              if(myorder!=null){
            for(int i=0;i<myorder.size();i++){
                Order temp=myorder.get(i);
        %>
            <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
                <td><%=temp.getDate()%></td>
                <td><%=temp.getSumnumber()%></td>
                <td  class="price" id="price<%=i%>"><%=temp.getSumprice()%></td>
            </tr>
            <%

                    }
                }
            %>
        </table>
        <p>
        <h2 style="margin-left: 200px;margin-top:20px;font-size: 20px">失败订单</h2>
        </p>
        <table class="altrowstable" id="alternatecolor2" align="center" style="margin-left:100px;margin-top: 20px">
            <tr>
                <th>date</th>
                <th>sumnumber</th>
                <th>sumprice</th>
            </tr>
            <tr>
                    <%
              List<Order> fails= (List<Order>)request.getAttribute("fails");
              if(myorder!=null){
            for(int i=0;i<fails.size();i++){
                Order temp=fails.get(i);
        %>
            <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
                <td><%=temp.getDate()%></td>
                <td><%=temp.getSumnumber()%></td>
                <td  class="price" id="price<%=i%>"><%=temp.getSumprice()%></td>
            </tr>
            <%

                    }
                }
            %>
        </table>




    </div>
    <div id="detail">
        <br>
        <br>
        <p style="font-size: 20px" >个人信息</p>
        <div style="font-size:15px;margin-top: 20px">
            <p name="bookname" ><label >姓名:</label><input style="font-size:15px" id="name" type="text" readonly="readonly"   value="" style=" width:140px;background:#F0F8FF;text-align:left;border-style:none"/></p>
            <br>
            <br>
            <p name="price"><label >电话:</label><input style="font-size:15px" id="telephone" type="text"  readonly="readonly"  value="" style=" width:140px;background:#F0F8FF;text-align:left;border-style:none "/></p>
            <br>
            <br>
            <p name="category"><label>邮箱:</label><input style="font-size: 15px" id="email" type="text"  readonly="readonly"  value="" style=" width:140px;background:#F0F8FF;text-align:left;border-style:none "/></p>
            <br>
            <br>
            <p name="score"><label>积分:</label><input style="font-size:15px" id="score" type="text"  readonly="readonly"  value="" style=" width:140px;background:#F0F8FF;text-align:left;border-style:none "/></p>
            <br>
        </div>
    </div>
</div>
<script>
    $.post('userView', function(data) {
        var result = eval(data);
        console.log(result);
        document.getElementById("name").setAttribute("value",result.user.username);
        document.getElementById("telephone").setAttribute("value",result.user.phone);
        document.getElementById("email").setAttribute("value",result.user.email);
        document.getElementById("score").setAttribute("value",result.user.score);

    },'json');
</script>
</body>
</html>
