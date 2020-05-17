<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/7
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/user.css" rel="stylesheet" type="css" media="all" />
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/icono.min.css">
<link rel="stylesheet" type="text/css" href="../css/wrong/main.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="head" style="margin-left: 100px" >
    <p style="font-size: 20px;text-align: center">统计管理</p>

    <p class="signout" style="float:right">
        <a class="icono-signout" href="welcomeadmin.jsp"></a>
        <a class="icono-heart" href="adminbook.jsp" ></a>
        <a class="icono-document" href="adminuser.jsp" ></a>
        <a class="icono-pin" href="statistic.jsp"></a>
    </p>
</div>
<style>
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
<script>
    $.post('getlog', function(data) {
        var result = eval(data);
        console.log(result);
        var tbody = document.getElementById("tbody-result");
        var str = "";
        console.log(result.total);
        console.log(result.rows);
        for (var i=0;i < result.total;++i){
            str += "<tr>";
            str += "<td>";
            str += result.rows[i].id;
            str += "</td>";
            str += "<td>";
            str += result.rows[i].username;
            str += "</td>";
            str += "<td>";
            str += result.times[i];
            str += "</td>";
            str += "<td>";
            str += result.rows[i].action;
            str += "</td>";
            str += "<td>";
            str += result.rows[i].status;
            str += "</td>";
            str+="</tr>";
        }
        tbody.innerHTML = str;
    },'json');
</script>
<div id="container" style="margin-left: 100px">

    <div id="basic">
            <p style="margin-left: 10px; font-size:20px">统计方式</p>
            <p></p>
            <p></p>
            <p style="margin-top: 30px">
            <select id="testSelect" style="margin-left: 10px">
                <option value ="username">username</option>
                <option value ="bookname">bookname</option>
                <option value="category">category</option>
            </select>
            <input style="margin-left: 10px" id="keyword" name="keyword"  type="text"/>
                <input type="submit" style="margin-right: 400px" value="查询"onclick="statisticone()"／>
            </p>

            <p></p>
            <p></p>

        <div style="margin-top: 50px" >
            <p><label style="margin-left: 10px" >起始日期</label> <input id="start" type="date" />
                <label style="margin-left: 10px">结束日期</label> <input id="end" type="date" />
                <input type="submit" style="margin-right:300px" onclick="statistictwo()" value="查询"></button>
            </p>
            <br>
            <br>
            <br>
            <p style=" font-size:20px;margin-left: 10px">查看日志</p>
            <br>
            <table style="margin-left: 10px;font-size: 10px" class="altrowstable" id="table-result">
                <thead >
                <tr>
                <td>id</td>
                <td>username</td>
                <td>date</td>
                <td>action</td>
                <td>status</td>
                </tr>
            </thead>
            <tbody id="tbody-result">
            </tbody>
        </table>


    </div>
    </div>
    <div id="detail">
            <p style=" font-size:20px">统计结果</p>
            <p></p>
            <p></p>
        <div style="font-size:15px;margin-top: 20px">
            <p > <label>总金额:</label><input id="sumprice"  name="stockbalance" type="text"   readonly="readonly"  value="" /></p>
            <br>
            <br>
            <p ><label>书总数</label>:</label><input id="sumnumber" name="publishhouse" type="text"   readonly="readonly" value="" /></p>
            <br>
            <br>
            <p ><label>订单数:</label><input id="sumorder" name="description" type="text"   readonly="readonly" value="" /></p>
    </div>
    </div>

</div>


<div id="foot">
</div>

</body>
</html>
<script>
    function statisticone(){
        var obj = document.getElementById("testSelect");
        var index = obj.selectedIndex; // 选中索引
        var keyword = obj.options[index].value; // 选中文本
        var thevalue=document.getElementById("keyword").value;
        $.post("statisticone",{keyword:keyword,thevalue:thevalue},function(data){
            var result=eval(data);
            console.log(result.sumprice);
            console.log(result.sumorder);
            console.log(result.sumnumber);
            result=eval(data);

            document.getElementById("sumprice").setAttribute("value",result.sumprice);
            document.getElementById("sumorder").setAttribute("value",result.sumorder);
            document.getElementById("sumnumber").setAttribute("value",result.sumnumber);


        },'json');
    }
    function statistictwo(){
        var start=document.getElementById("start").value;
        var end=document.getElementById("end").value;
        $.post("statistictwo",{start:start,end:end},function(data){

            var result=eval(data);
            console.log(result.sumprice);
            console.log(result.sumorder);
            console.log(result.sumnumber);
            result=eval(data);

            document.getElementById("sumprice").setAttribute("value",result.sumprice);
            document.getElementById("sumorder").setAttribute("value",result.sumorder);
            document.getElementById("sumnumber").setAttribute("value",result.sumnumber);


        },'json');
    }
</script>
