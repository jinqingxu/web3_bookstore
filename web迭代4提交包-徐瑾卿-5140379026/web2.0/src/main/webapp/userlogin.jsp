<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2017/3/21
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" import=" com.opensymphony.xwork2.ActionContext" %>
<html>
<head>
    <title>Login</title>

</head>

<body>
<section class="container">

    <div class="login" style="margin-left:500px;margin-top:150px">
        <h1>用户登录</h1>
        <form method=post action="j_security_check" >
            <p>
                <span>用户名:</span>
                <br />
                <input type="text"  name= "j_username" >
            </p>
            <p>
                <span>密码:</span>
                <br />
                <input type="password"  name= "j_password" >
            </p>
            <p>
                <input type="submit" style="margin-left: 90px" value="登陆">
            </p>
        </form>

    </div>

</section>

</body>
</html>


