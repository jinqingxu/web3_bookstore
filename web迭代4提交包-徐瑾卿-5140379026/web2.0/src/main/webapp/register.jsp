<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/6
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8 ]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9 ]> <html lang="en" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>登陆</title>


    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <![endif]-->

    <link href="css/normalize.css" rel="stylesheet"/>
    <link href="css/jquery-ui.css" rel="stylesheet"/>
    <link href="css/jquery.idealforms.min.css" rel="stylesheet" media="screen"/>

    <style type="text/css">
        body{font:normal 15px/1.5 Arial, Helvetica, Free Sans, sans-serif;color: #222;background:url(login/pattern.png);overflow-y:scroll;padding:60px 0 0 0;}
        #my-form{width:755px;margin:0 auto;border:1px solid #ccc;padding:3em;border-radius:3px;box-shadow:0 0 2px rgba(0,0,0,.2);}
        #comments{width:350px;height:100px;}
    </style>

</head>
<body>


<div class="row">

    <div class="eightcol last">

        <!-- Begin Form -->

        <form id="my-form" method="post" action="userRegister">

            <section name="register">

                <div><label>名字:</label><input id="username" name="user.username" type="text"/></div>
                <div><label>密码:</label><input id="pass" name="user.password" type="password"/></div>
                <div><label>手机:</label><input id="phone" name="user.phone"  type="text"/></div>
                <div><label>邮箱:</label><input id="email" name="user.email" data-ideal="required email" type="text"/></div>
            </section>
            </section>

            <div><hr/></div>

            <div>
                <button type="submit">提交</button>
                <button id="reset" type="button">重置</button>
            </div>

        </form>

        <!-- End Form -->

    </div>

</div>


<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.idealforms.js"></script>
<script type="text/javascript">
    var options = {

        onFail: function(){
            alert( $myform.getInvalid().length +' invalid fields.' )
        },

        inputs: {
            'password': {
                filters: 'required pass',
            },
            'username': {
                filters: 'required username',
                data: {

                }
            },

            'comments': {
                filters: 'min max',
                data: { min: 50, max: 200 }
            },

            'langs[]': {
                filters: 'min max',
                data: { min: 2, max: 3 },
                errors: {
                    min: 'Check at least <strong>2</strong> options.',
                    max: 'No more than <strong>3</strong> options allowed.'
                }
            }
        }

    };

    var $myform = $('#my-form').idealforms(options).data('idealforms');

    $('#reset').click(function(){
        $myform.reset().fresh().focusFirst()
    });

    $myform.focusFirst();
</script>
</body>
</html>