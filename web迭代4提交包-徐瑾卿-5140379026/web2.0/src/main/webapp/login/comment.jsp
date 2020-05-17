<%--
  Created by IntelliJ IDEA.
  User: jinqingxu
  Date: 14/06/2017
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String socPath="ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <style type="text/css">
        *{
            margin:0;
            padding:0;
        }
        body{
            background-color:#FFFFFF;

        }
        .clear{
            clear:both;
        }
        /*head*/
        #head{
            background-color:#E0FFFF;
            width:1000px;
            height:80px;
            margin-left: 50px;
            margin-top: 0px;
        }
        #button{
            background-color:#E0FFFF;
            width:20px;
            height:20px;
            margin-left:1000px;
        }
        /*container*/
        #container{
            background-color:#F0F8FF;
            width:1000px;
            height:2000px;
            margin-left: 50px;
        }


        /*content*/
        #basic{
            float:left;
            background-color:#F0F8FF;
            width:1000px;
            height:2000px;


        }

        #book{
            float:left;
            background-color:#F0F8FF;
            width:600px;
            height:2000px;
        }
        #item{
            float:left;
            background-color:#F0F8FF;
            width:200px;
            height:300px;
        }
        #detail{
            float:right;
            background-color:#F0F8FF;
            width:400px;
            height:800px;
        }
        /*foot*/
        #foot{
            background-color:#E0FFFF;
            height:30px;
            width:1100px;
            margin-left:150px;
            margin-right:150px;
        }
        :-moz-placeholder {
            color: #c9c9c9 !important;
            font-size: 13px;
        }

        ::-webkit-input-placeholder {
            color: #ccc;
            font-size: 13px;
        }
        .item p.submit{
            text-align: right;
        }
        input {
            font-family: 'Lucida Grande', Tahoma, Verdana, sans-serif;
            font-size: 14px;
        }
        input[type=submit] {
            padding: 0 18px;
            height: 29px;
            font-size: 12px;
            font-weight: bold;
            color: #527881;
            text-shadow: 0 1px #e3f1f1;
            background: #cde5ef;
            border: 1px solid;
            border-color: #b4ccce #b3c0c8 #9eb9c2;
            border-radius: 16px;
            outline: 0;
            -webkit-box-sizing: content-box;
            -moz-box-sizing: content-box;
            box-sizing: content-box;
            background-image: -webkit-linear-gradient(top, #edf5f8, #cde5ef);
            background-image: -moz-linear-gradient(top, #edf5f8, #cde5ef);
            background-image: -o-linear-gradient(top, #edf5f8, #cde5ef);
            background-image: linear-gradient(to bottom, #edf5f8, #cde5ef);
            -webkit-box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
            box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
        }
        input[type=submit]:active {
            background: #cde5ef;
            border-color: #9eb9c2 #b3c0c8 #b4ccce;
            -webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
            box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
        }
    </style>
    <title>comment</title>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
<script type="text/javascript">
    $(function() {
        $.post('getcomment', function (data) {
            var result = eval(data);
            console.log(result);
            var tbody = document.getElementById("tbody-result");
            var str = "";
            console.log(result.total);
            console.log(result.rows);
            for (var i=0;i < result.total;++i){
                str += "<tr>";
                str += "<td>";
                str+="<b>";
                str += result.rows[i].bookname;
                str+="</b>";
                str += "</td>";
                str += "</tr>";
                str += "<tr>";
                str += "<td>";
                str += result.rows[i].content;
                str += "</td>";
                str += "</tr>";
            }

            tbody.innerHTML = str;

        }, 'json');
    })
    function add(){
        var bookname=document.getElementById("bookname").value;
        var content=document.getElementById("enter").value;
        $.post('addcomment',{bookname:bookname,content:content}, function(data) {
            result=eval(data);
            console.log(result.message);
            alert(result.message);
        },'json');
    }
    function search(){
        var key=document.getElementById("term").value;
        $.post('searchcomment',{key:key}, function(data) {
            var result = eval(data);
            console.log(result);
            var tbody = document.getElementById("tbody-result");
            var str = "";
            console.log(result.total);
            console.log(result.rows);
            for (var i=0;i < result.total;++i){
                str += "<tr>";
                str += "<td>";
                str+="<b>";
                str += result.rows[i].bookname;
                str+="</b>";
                str += "</td>";
                str += "</tr>";
                str += "<tr>";
                str += "<td>";
                str += result.rows[i].content;
                str += "</td>";
                str += "</tr>";
            }

            tbody.innerHTML = str;
        },'json');
    }

</script>
<script type="text/javascript" src="css/jquery-1.6.2.min.js"></script>
<body>
<link rel="stylesheet" href="../css/icono.min.css">
<div id="head">
    <p><h1 style="text-align: center">查询书评</h1>
    <p  style="margin-left: 800px;margin-bottom: 10px">
    <a class="icono-home" href="newBookList" ></a>
    <a class="icono-cart" href="cartView" ></a>
    <a class="icono-signout" href="userLogout"></a>
    <a class="icono-disqus" href="comment.jsp"></a>
    <a class="icono-user" href="orderview"></a>
    </p>
</div>
<div id="container">
    <div id="basic">
        <p style="margin-left: 10px">提交书评</p>
        <label style="margin-left: 10px">书名：</label><input    style="margin-left: 10px" id="bookname" type="text" size="20" maxlength="20"/><input type="submit" style="margin-left: px"id="add" value="提交" onclick="add();"/>
        <br></br>
        <p><textarea  style="margin-left: 10px" id="enter" cols="100" rows="10" ></textarea></p>
        <br></br>
        <br></br>
        <p style="margin-left: 10px">全文搜索</p>
        <label style="margin-left: 10px">关键词:</label><input id="term" type="text" size="20" maxlength="20"/>
        <input type="submit" id="search" value="查询" onclick="search();"/><br/>
        <br>
        <table style="margin-left: 10px" class="ui nine column table celled table-result" id="table-result">
            <thead>
            </thead>
            <tbody id="tbody-result">
            </tbody>
        </table>
        <br></br>

    </div>
</div>


</body>
</html>
