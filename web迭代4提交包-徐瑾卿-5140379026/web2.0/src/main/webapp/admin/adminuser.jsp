<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/9
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/admin.css" rel="stylesheet" type="css" media="all" />

<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/icono.min.css">
<html>
<head>

        <meta charset="UTF-8">
        <title style="text-align: center">管理员界面</title>
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
        <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>


</head>
<body>
<div id="head">
    <p style=" font-size:30px;text-align: center">管理用户中心</p>
    <p class="signout" style="float:right;margin:0.5cm auto"> <a class="icono-signout" href="welcomeadmin.jsp"></a></p>
</div>

<div id="container">

    <div id="basic">

        <div id="user" style="margin-left: 20px;margin-top: 20px">



            <table id="dg" title="My Users" class="easyui-datagrid" style="width:700px;height:250px;margin-left: 20px"
                   url="userGet"
                   toolbar="#toolbar" pagination="true"
                   rownumbers="true" fitColumns="true" singleSelect="true">
                <thead>
                <tr>

                    <th field="username" width="40">Username</th>
                    <th field="phone" width="40">Phone</th>
                    <th field="email" width="40">Email</th>
                    <th field="password" width="40">Password</th>

                </tr>
                </thead>
            </table>


            <div id="toolbar">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
            </div>

            <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
                 closed="true" buttons="#dlg-buttons">
                <div class="ftitle">User Information</div>
                <form id="fm" method="post"novalidate>
                    <div class="fitem">
                        <label>Username:</label>
                        <input id="username" name="username" class="easyui-textbox" required="true">
                    </div>
                    <div class="fitem">
                        <label>Phone:</label>
                        <input id="phone" name="phone" class="easyui-textbox" required="true">
                    </div>
                    <div class="fitem">
                        <label>Email:</label>
                        <input id="email" name="email" class="easyui-textbox" validType="email">
                    </div>
                    <div class="fitem">
                        <label>Password:</label>
                        <input id="password" name="password" class="easyui-textbox" >
                    </div>

                </form>
            </div>
            <div id="dlg-buttons">
                <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
            </div>
        </div>


        </div>
    </div>

    <div id="statistic">
    </div>

</div>


<div id="foot">
</div>
<script>
    var url;
    var tempid;
    function newUser(){

        $('#dlg').dialog('open').dialog('setTitle','New User');
        $('#fm').form('clear');
        url="userInsert";

    }
    function editUser(){
        var row = $('#dg').datagrid('getSelected');
        tempid=row.id;
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', 'Edit User');
            $('#fm').form('load', row);
            url="userUpdate";
        }


    }
    function saveUser(){
        var username=document.getElementById("username").value;
        var phone=document.getElementById("phone").value;
        var email=document.getElementById("email").value;
        var password=document.getElementById("password").value;
        if(url=="userInsert") {

            $.post('userInsert',{username: username, phone: phone, email: email, password: password}, function() {




            },'json');
            $('#dlg').dialog('close');		// close the dialog
            $('#dg').datagrid('reload');	// reload the user data



        }
        else{
            $.post('userUpdate',{id:tempid,username: username, phone: phone, email: email, password: password}, function() {




            },'json');
            $('#dlg').dialog('close');		// close the dialog
            $('#dg').datagrid('reload');	// reload the user data




        }

    }
    function destroyUser(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                if (r){
                    $.post('userDelete',{id:row.id},function(result){
                        if (result.success){
                            $('#dg').datagrid('reload');	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        }
                    },'json');
                }
            });
        }
    }

</script>
</body>
</html>


