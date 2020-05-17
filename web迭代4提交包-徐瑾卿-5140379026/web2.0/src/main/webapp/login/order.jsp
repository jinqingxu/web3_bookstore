<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/7
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsencrypt/2.3.1/jsencrypt.min.js"></script>
<html>
<head>
    <title>RSA</title>
    <link rel="stylesheet" type="text/css" href="../css/wrong/main.css">
</head>
<link rel="stylesheet" href="../css/icono.min.css">
<body>
<div id="wrapper">
    <div id="main">
        <header id="header">
        </header>
        <div id="content">
            <h2>支付页面</h2>
            <a class="icono-home" style="margin-left: 560px" href="newBookList" ></a>
            <div class="utilities">
                <form action="payaction" id="loginForm" method="post" >
                    <table style="margin-left: 200px">
                        <tr>
                            <td style="font-size: 15px">账号</td>
                            <td><input type="text" name="username" /></td>
                        </tr>
                        <tr><br></tr>
                        <tr><br></tr>
                        <tr>
                            <td style="font-size: 15px">密码</td>
                            <td><input type="password" name="password" id="password" /></td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <input type="submit"  value="提交" style="margin-left: 280px" onclick="encryptPassword()"/></td>
                    <input type="reset" value="重置"/></td>

                </form>


                <div class="clear"></div>
            </div>
        </div>


    </div>
</div>


</body>
</html>
<script type="text/javascript">
    //获取public key
    //var publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAviSuCu4Yg/WAyjp06qiaE/ioI2M/ACT9UTUVxWtM7IZlXMQZPjLn0H1x0zmJ/VLIhnBliyb06QLvtrrBFRt4jnOJR5LjoTg/g8XYdVXN6a+XFjqFvOUPgzZ7OdywOoXxiO+M7WrvT0XgqyBqCnDADpY1eucDqfIDYYOBHKbtMkh0N4ZVBcfULb1Sm+Q7ed+jUa8eXPQPhMrWvhQkIeZJh+hCIrNjXUxyfZPh1tSvqoJYArbyHZs8LnbUtjIQCx9OlR9+xJTx3L9h89I4D+hqA4CZqxUzfibsu5XgYKnoSri2OCR2FefSfYlCd8Fysp0wET/r1L141qnhoMQtrUs8jwIDAQAB";
    var publicKey="";
    $.post('getpublickey',function(data){
            var result=eval(data);
            publicKey=result.publicKey;
            console.log(publickey);
        },'json'
    );
    function encryptPassword() {
        //对密码进行加密

        var encrypt = new JSEncrypt();

        encrypt.setPublicKey(publicKey);
        var password = document.getElementById("password").value;
        document.getElementById("password").value = encrypt.encrypt(password);
        //提交之前，检查是否已经加密。假设用户的密码不超过20位，加密后的密码不小于20位
        var password = document.getElementById("password").value;
        var username=document.getElementById("username").value;
        if(password.length < 20) {
            //实际提示，可以换一种说法
            alert("encryption failed");
            //若没有加密，阻止提交
        }
        $.post('payaction',{username:username,password:password},'json'
        );
    }

</script>