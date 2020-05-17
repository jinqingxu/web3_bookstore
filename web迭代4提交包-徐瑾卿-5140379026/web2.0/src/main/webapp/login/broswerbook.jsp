<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/11
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.module.bean.Book,java.util.List" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<link href="/css/broswer.css" rel="stylesheet" type="css" media="all" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<link rel="stylesheet" href="../css/icono.min.css">
<link href="../css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
<html>
<body>
<div id="head" style="margin-left: 125px">
    <p><h3 style="text-align: center">当前在线人数:${requestScope.count}  </h3>
    <p  style="margin-left: 800px;margin-bottom: 10px">
        <a class="icono-home" href="newBookList" ></a>
        <a class="icono-cart" href="cartView" ></a>
        <a class="icono-signout" href="userLogout"></a>
        <a class="icono-disqus" href="comment.jsp"></a>
        <a class="icono-user" href="orderview"></a>
    </p>
    </p>

</div>
<div id="container" style="margin-left: 125px"> <div id="basic"> <div id="book" >
    <div style="margin-left:20px;margin-top: 20px">
        <%
            List<Book> books= (List<Book>)request.getAttribute("books");
            for(int i=0;i<books.size();i++) {
                Book mybook=books.get(i);
        %>
        <div id="item"  >
            <img name="bookid" value = "<%=mybook.getId()%>"  src="<%=mybook.getPhotopath()%>"  width="150" height="150" onclick="getdetail(<%=mybook.getId()%>)" />
            <p name="bookname" ><label>书名:</label><input  type="text" readonly="readonly"   value="<%=mybook.getBookname()%>" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none"/></p>
            <p name="price"><label>价格:</label><input type="text"  readonly="readonly"  value="<%=mybook.getPrice()%>" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none "/></p>
            <p name="category"><label>类别:</label><input type="text"  readonly="readonly"  value="<%=mybook.getCategory()%>" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none "/></p>
        </div>

        <% } %>
    </div>
</div>
    <div id="detail" style="margin-top:20px">
        <p><label>书名:</label><input id="bookquery" class="query" type="text"/><input  type="submit"  name="commit" value="查询" style="margin-left: 55px" onclick="bookquery()"></p>
        <p>基本信息</p><br>
        <img id="photopath" src="../images/start.jpg" width="300" height="300"/>
        <br>
        <br>
        <p id="bk"> <label>书名:</label><input id="bookname"  name="bookname" type="text"   readonly="readonly"  value="" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none"/></p>
        <p id="pr"><label>价格:</label><input id="price" name="price" type="text"   readonly="readonly" value="" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none"/></p>
        <p>详细信息</p><br>
        <p id="sk"> <label>库存:</label><input id="stockbalance"  name="stockbalance" type="text"   readonly="readonly"  value="" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none"/></p>
        <p id="ph"><label>出版商:</label><input id="publishhouse" name="publishhouse" type="text"   readonly="readonly" value="" style=" width:120px;background:#F0F8FF;text-align:left;border-style:none"/></p>
        <p id="dp"><label>描述:</label><input id="description"  name="description" type="text"   readonly="readonly" value="" style=" width:300px;background:#F0F8FF;text-align:left;border-style:none"/></p>
        <p id="ml"><label>喜欢人数:</label><input id="mylike"  name="mylike" type="text"   readonly="readonly" value="" style=" width:300px;background:#F0F8FF;text-align:left;border-style:none"/></p>
        <p><input class="min" name="" type="button" value="-" />
            <input class="text_box" id="booknumber" name="booknumber" type="text" value="1" style="width:30px;" />
            <input class="add" name="" type="button" value="+" />
            <input  type="submit"  name="commit" value="购物车" style="margin-left: 85px" onclick="cartinsert()">
            <input  type="submit"  name="commit" value="喜欢" style="margin-left: 10px" onclick="updatemylike()">
            <input id="bookid" type="text" name="bookid" readonly="readonly"  value="" style=" display:none;"/></p>

    </div>

</div>
</div>


</div>

</body>
</html>
<script>

    $(function() {
        $(".add").click(function () {
            var t = $(this).parent().find('input[class*=text_box]');
            t.val(parseInt(t.val()) + 1)
        })
        $(".min").click(function () {
            var t = $(this).parent().find('input[class*=text_box]');
            t.val(parseInt(t.val()) - 1)
            if (parseInt(t.val()) < 0) {
                t.val(0);
            }
        })
    })

    function updatemylike(){
        var tempid=document.getElementById("bookid").value;
        $.post('updatemylike',{id:tempid}, function(data) {
            result=eval(data);
            document.getElementById("mylike").setAttribute("value",result.mylike);
        },'json');
    }

    function bookquery(){
        var bookname=document.getElementById("bookquery").value;
        var url="http://localhost:8083/i/rest/book?bookname="+bookname;
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'jsonp',//here
            jsonp: "callback",
            jsonpCallback:"success_jsonpCallback",
            success: function (data) {
                var result=data;
                document.getElementById("bookname").setAttribute("value",result.bookname);
                document.getElementById("photopath").setAttribute("src",result.photopath);
                document.getElementById("stockbalance").setAttribute("value",result.stockbalance);
                document.getElementById("description").setAttribute("value",result.description);
                document.getElementById("publishhouse").setAttribute("value",result.publishhouse);
                document.getElementById("mylike").setAttribute("value",result.mylike);
                document.getElementById("bookid").setAttribute("value",result.id);
                document.getElementById("price").setAttribute("value",result.price);
            }
        });

    }
    function cartinsert(){
        var bookid=document.getElementById("bookid").value;
        var booknumber=document.getElementById("booknumber").value;
        console.log(bookid);
        console.log(booknumber);
        $.post('cartinsert',{bookid:bookid,booknumber:booknumber}, function(data) {
            result=eval(data);

            console.log(result.message);
            alert(result.message);
        },'json');
    }
    function getdetail(tempid){

        $.post('bookqueryid',{id:tempid}, function(data) {
            result=eval(data);
            document.getElementById("bookname").setAttribute("value",result.bookname);
            document.getElementById("photopath").setAttribute("src",result.photopath);
            document.getElementById("stockbalance").setAttribute("value",result.stockbalance);
            document.getElementById("description").setAttribute("value",result.description);
            document.getElementById("publishhouse").setAttribute("value",result.publishhouse);
            document.getElementById("mylike").setAttribute("value",result.mylike);
            document.getElementById("bookid").setAttribute("value",result.id);
            document.getElementById("price").setAttribute("value",result.price);


        },'json');

    }
</script>


