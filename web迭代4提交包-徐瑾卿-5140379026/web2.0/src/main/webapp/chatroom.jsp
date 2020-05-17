<!--

    Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.

    You may not modify, use, reproduce, or distribute this software except in
    compliance with  the terms of the License at:
    http://java.net/projects/javaeetutorial/pages/BerkeleyLicense

-->
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
  <title>WebsocketBot</title>
  <script type="text/javascript">
      var wsocket;    // Websocket connection
      var userName;   // User's name
      var textarea;   // Chat area
      var wsconsole;  // Websocket console area
      var userlist;   // User list area
      /* Connect to the Websocket endpoint
       * Set a callback for incoming messages */
      function connect() {
          window.WebSocket = window.WebSocket || window.MozWebSocket;
          if (!window.WebSocket) {    // 检测浏览器支持
              console.log('Error: WebSocket is not supported .');
              return;
          }
          textarea = document.getElementById("textarea");
          wsconsole = document.getElementById("wsconsole");
          userlist = document.getElementById("userlist");
          var wsuri = "<%=socPath%>websocketbot";
          if ('WebSocket' in window)
              wsocket = new WebSocket(wsuri);
          else if ('MozWebSocket' in window)
              wsocket = new MozWebSocket(wsuri);
          else
              console.error("not support WebSocket!");
          wsocket.onmessage = onMessage;
          document.getElementById("name").focus();
          document.getElementById("consolediv").style.visibility = 'hidden';
          console.log("connected");
      }
      /* Callback function for incoming messages
       * evt.data contains the message
       * Update the chat area, user's area and Websocket console */
      function onMessage(evt) {
          var line = "";
          /* Parse the message into a JavaScript object */
          var msg = JSON.parse(evt.data);
          if (msg.type === "chat") {
              line = msg.name + ": ";
              if (msg.target.length > 0)
                  line += "@" + msg.target + " ";
              line += msg.message + "\n";
              /* Update the chat area */
              textarea.value += "" + line;
          } else if (msg.type === "info") {
              line = "[--" + msg.info + "--]\n";
              /* Update the chat area */
              textarea.value += "" + line;
          } else if (msg.type === "users") {
              line = "Users:\n";
              for (var i=0; i < msg.userlist.length; i++)
                  line += "-" + msg.userlist[i] + "\n";
              /* Update the user list area */
              userlist.value = line;
          }
          textarea.scrollTop = 999999;
          /* Update the Websocket console area */
          wsconsole.value += "-> " +  evt.data + "\n";
          wsconsole.scrollTop = 999999;
      }
      /* Send a join message to the server */
      function sendJoin() {
          var input = document.getElementById("input");
          var name = document.getElementById("name");
          var join = document.getElementById("join");
          var jsonstr;
          if (name.value.length > 0) {
              /* Create a message as a JavaScript object */
              var joinMsg = {};
              joinMsg.type = "join";
              joinMsg.name = name.value;
              /* Convert the message to JSON */
              jsonstr = JSON.stringify(joinMsg);
              /* Send the JSON text */

              wsocket.send(jsonstr);
              /* Disable join controls */
              name.disabled = true;
              join.disabled = true;
              input.disabled = false;
              userName = name.value;
              /* Update the Websocket console area */
              wsconsole.value += "<- " + jsonstr + "\n";
              wsconsole.scrollTop = 999999;
          }
      }
      /* Send a chat message to the server (press ENTER on the input area) */
      function sendMessage(evt) {
          var input = document.getElementById("input");
          var jsonstr;
          var msgstr;
          if (evt.keyCode === 13 && input.value.length > 0) {
              /* Create a chat message as a JavaScript object */
              var chatMsg = {};
              chatMsg.type = "chat";
              chatMsg.name = userName;
              msgstr = input.value;
              chatMsg.target = getTarget(msgstr.replace(/,/g, ""));
              chatMsg.message = cleanTarget(msgstr);
              chatMsg.message = chatMsg.message.replace(/(\r\n|\n|\r)/gm,"");
              /* Convert the object to JSON */
              jsonstr = JSON.stringify(chatMsg);
              /* Send the message as JSON text */
              wsocket.send(jsonstr);
              input.value = "";
              /* Update the Websocket console area */
              wsconsole.value += "<- " + jsonstr + "\n";
              wsconsole.scrollTop = 999999;
          }
      }
      /* Send a join message if the user presses ENTER in the name area */
      function checkJoin(evt) {
          var name = document.getElementById("name");
          var input = document.getElementById("input");
          if (evt.keyCode === 13 && name.value.length > 0) {
              console.log("checked");
              sendJoin();
              input.focus();
          }
      }
      /* Get the @User (target) for a message */
      function getTarget(str) {
          var arr = str.split(" ");
          var target = "";
          for (var i=0; i<arr.length; i++) {
              if (arr[i].charAt(0) === '@') {
                  target = arr[i].substring(1,arr[i].length);
                  target = target.replace(/(\r\n|\n|\r)/gm,"");
              }
          }
          return target;
      }
      /* Remove the @User (target) from a message */
      function cleanTarget(str) {
          var arr = str.split(" ");
          var cleanstr = "";
          for (var i=0; i<arr.length; i++) {
              if (arr[i].charAt(0) !== '@')
                  cleanstr += arr[i] + " ";
          }
          return cleanstr.substring(0,cleanstr.length-1);
      }
      /* Show or hide the WebSocket console */
      function showHideConsole() {
          var chkbox = document.getElementById("showhideconsole");
          var consolediv = document.getElementById("consolediv");
          if (chkbox.checked)
              consolediv.style.visibility = 'visible';
          else
              consolediv.style.visibility = 'hidden';
      }
      /* Call connect() when the page first loads */
      window.addEventListener("load", connect, false);
  </script>
</head>
<body>
<div id="head">
    <p><h3 style="text-align: center">欢迎来到聊天室</h3>
    <p  style="margin-left: 850px;margin-bottom: 10px"> <a class="icono-document" href="userView" ></a>
        <a class="icono-heart" href="cartView" ></a>
        <a class="icono-signout" href="newBookList"></a>
    </p>
    <a href="newBookList" class="botton right"></a>
    </p>
</div>
<div id="container">

    <div id="basic">

        <label style="margin-left: 20px">昵称:</label> <input id="name" type="text" size="20" maxlength="20" onkeyup="checkJoin(event);"/>
        <input type="submit" id="join" value="加入!" onclick="sendJoin();"/><br/>
        <label style="margin-left: 20px"> 输入:</label><textarea id="input" cols="94" rows="1" disabled="true"
                                                               onkeyup="sendMessage(event);"></textarea><br/>
        <textarea style="margin-left: 20px" id="textarea" cols="100" rows="20" readonly="true"></textarea>
        <textarea id="userlist" cols="20" rows="20" readonly="true"></textarea>
        <br/><br/><br/>
        <input style="margin-left: 20px"id="showhideconsole" type="checkbox" onclick="showHideConsole();"/>
        <label style="margin-left: 20px"> 显示控制台</label><br/>
        <div id="consolediv"><textarea id="wsconsole" cols="80" rows="8" readonly="true"
                                       style="font-size:8pt;"></textarea></div>
    </div>
</div>



</body>
</html>