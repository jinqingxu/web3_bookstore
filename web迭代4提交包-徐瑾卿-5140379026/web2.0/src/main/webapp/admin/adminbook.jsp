<%--
  Created by IntelliJ IDEA.
  User: piglet
  Date: 2016/6/11
  Time: 7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="css/icono.min.css">
<link rel="stylesheet" href="css/admin.css">
<html>
<head>

    <meta charset="UTF-8">
    <title>管理员界面</title>
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>


</head>
<body>
<div id="head">
    <p style=" font-size:30px;text-align: center">管理图书中心</p>
    <p class="signout" style="float:right;margin:0.5cm auto"> <a class="icono-signout" href="welcomeadmin.jsp"></a></p>
</div>

<div id="container">

    <div id="basic">

        <div id="book" style="margin-left: 20px;margin-top: 20px">
            <table id="dg" title="My Books" class="easyui-datagrid" style="width:1000px;height:250px;margin-left: 20px"
                   url="bookGet"
                   toolbar="#toolbar" pagination="true"
                   rownumbers="true" fitColumns="true" singleSelect="true">
                <thead>
                <tr>

                    <th field="bookname" width="40">bookname</th>
                    <th field="price" width="20">price</th>
                    <th field="category" width="30">category</th>
                    <th field="stockbalance" width="20">stockbalance</th>
                    <th field="publishhouse" width="40">publishhouse</th>
                    <th field="photopath" width="80">photopath</th>
                    <th field="description" width="80">description</th>
                </tr>
                </thead>
            </table>


            <div id="toolbar">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newBook()">New Book</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editBook()">Edit Book</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyBook()">Remove Book</a>
            </div>

            <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
                 closed="true" buttons="#dlg-buttons">
                <div class="ftitle">Book Information</div>
                <form id="fm" method="post"novalidate>
                    <div class="fitem">
                        <label>Bookname:</label>
                        <input id="bookname" name="bookname" class="easyui-textbox" required="true">
                    </div>
                    <div class="fitem">
                        <label>Price:</label>
                        <input id="price" name="price" class="easyui-textbox" required="true">
                    </div>
                    <div class="fitem">
                        <label>Category:</label>
                        <input id="category" name="category" class="easyui-textbox" >
                    </div>
                    <div class="fitem">
                        <label>Stockbalance:</label>
                        <input id="stockbalance" name="stockbalance" class="easyui-textbox" >
                    </div>
                    <div class="fitem">
                        <label>Publishhouse:</label>
                        <input id="publishhouse" name="publishhouse" class="easyui-textbox" >
                    </div>
                    <div class="fitem">
                        <label>Photopath:</label>
                        <input id="photopath" name="photopath" class="easyui-textbox" >
                    </div>
                    <div class="fitem">
                        <label>Description:</label>
                        <input id="description" name="description" class="easyui-textbox" >
                    </div>

                </form>
            </div>
            <div id="dlg-buttons">
                <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBook()" style="width:90px">Save</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
            </div>
        </div>


    </div>
</div>


</div>


<div id="foot">
</div>
<script>
    var url;
    var tempid;
    function newBook(){

        $('#dlg').dialog('open').dialog('setTitle','New Book');
        $('#fm').form('clear');
        url="bookInsert";

    }
    function editBook(){
        var row = $('#dg').datagrid('getSelected');
        tempid=row.id;
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', 'Edit Book');
            $('#fm').form('load', row);
            url="bookUpdate";
        }


    }
    function saveBook(){
        var bookname=document.getElementById("bookname").value;
        var price=document.getElementById("price").value;
        var category=document.getElementById("category").value;
        var stockbalance=document.getElementById("stockbalance").value;
        var publishhouse=document.getElementById("publishhouse").value;
        var photopath=document.getElementById("photopath").value;
        console.log(price)
        var description=document.getElementById("description").value;
        if(url=="bookInsert") {

            $.post('bookInsert',{bookname:bookname, price:price, category:category,stockbalance:stockbalance,publishhouse:publishhouse,photopath:photopath,description:description}, function() {


            },'json');
            $('#dlg').dialog('close');		// close the dialog
            $('#dg').datagrid('reload');	// reload the user data



        }
        else{
            $.post('bookUpdate',{id:tempid,bookname:bookname, price:price, category:category,stockbalance:stockbalance,publishhouse:publishhouse,photopath:photopath,description:description}, function() {


            },'json');
            $('#dlg').dialog('close');		// close the dialog
            $('#dg').datagrid('reload');	// reload the user data




        }

    }
    function destroyBook(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','Are you sure you want to destroy this book?',function(r){
                if (r){
                    $.post('bookDelete',{id:row.id},function(result){
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


