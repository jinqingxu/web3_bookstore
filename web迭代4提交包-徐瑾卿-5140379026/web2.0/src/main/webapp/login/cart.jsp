<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/7
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="com.module.entity.Cart,java.util.List" %>
<!-- Javascript goes in the document HEAD -->
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<link rel="stylesheet" href="css/admin.css">
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
        width:180px;
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
<link rel="stylesheet" href="../css/icono.min.css">
<div id="head" style="margin-left: 100px" >
    <br>
    <br>
    <p style="text-align: center;font-size: 30px">购物车</p>
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
        <table class="altrowstable" id="alternatecolor" style="margin-left:140px;font-size: 18px;margin-top: 20px">
            <tr>
                <th>选择</th>
                <th  style=" display:none;">编号</th>
                <th>图片</th>
                <th>书名</th>
                <th>类别</th>
                <th>价格</th>
                <th>数量</th>
            </tr>
            <tr>

                    <%
              List<Cart> carts= (List<Cart>)request.getAttribute("mycart");
              if(carts!=null){
            for(int i=0;i<carts.size();i++){
                Cart temp=new Cart();
                temp=(Cart)carts.get(i);
                if(temp.getNumber()>0){

        %>
            <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">

                <td><input name="choose" type="checkbox" value="<%=i%>"/></td>
                <td  style=" display:none;"><%=temp.getBookid()%></td>
                <td><img id="photopath" src="<%=temp.getPicture()%>" width="150" height="150"/></td>
                <td><%=temp.getBookname()%></td>
                <td><%=temp.getCategory()%></td>
                <td  class="price" id="price<%=i%>"><%=temp.getPrice()%></td>
                <td>
                    <label>数量</label><input class="text_box" id="text_box<%=i%>" name="" style="width:30px" type="text" value="<%=temp.getNumber()%>" />

                </td>
                <input style="display:none"  id="cartsize" value="<%=carts.size()%>" />



            </tr>
            <%
                        }
                    }
                }
            %>
        </table>
        <p>
            <input  type="submit"  name="commit" value="提交" style="margin-left: 550px;margin-top: 20px"  onclick="placeorder()"/>
            <input  name="commit" type="submit"   value="付款"  style="" onclick="pay()" >
        </p>
    </div>


</div>



</body>
</html>




<script>
    function pay(){
        window.location.href="/login/order.jsp";
    }
    function reload(){
        $.post('cartView', function(data) {
            var result=eval(data);
            console.log(result);
        },'json');
    }
    function placeorder(){
        var  s=document.getElementsByName("choose");
        var checked="";
        var numbers="";

        for( var i = 0; i <s.length; i++ ){
            if(s[i].checked==true){
                console.log(s[i].value);
                checked+=s[i].value;
                checked+="|";
                var num=document.getElementById("text_box"+i).value;
                console.log(num);
                numbers+=num;
                numbers+="|";

            }
        }

        $.post('orderPlace',{checked:checked,numbers:numbers}, function(data) {
            result=eval(data);
            console.log(result.message);
            alert(result.message);
        },'json');
    }
    $(function() {
        var debug=document.getElementById("cartsize");
        console.log(debug);
        ;
        $(".add").click(function () {
            var t=$(this).parent().find('input[class*=text_box]');
            t.val(parseInt(t.val())+1)
            setTotal();
        })
        $(".min").click(function () {
            var t=$(this).parent().find('input[class*=text_box]');
            t.val(parseInt(t.val())-1)
            if(parseInt(t.val())<0){
                t.val(0);
            }
            setTotal();
        })
        function setTotal(){
            var s=0;
            $("#alternatecolor tr").each(function(){
                $("#alternatecolor tr td").each(function() {

                    s += parseInt($(this).find('input[class*=text_box]').val()) * parseFloat($(this).find('span[class*=price]').text());
                    console.log(s);
                });
            });
            $("#total").html(s.toFixed(2));
        }
        setTotal();



    })
</script>


