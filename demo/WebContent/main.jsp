<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

  </head>
  
  <body>
  <form action="InputInformation" method=post>
  <textarea  cols="105" rows="25" name="show_textarea"><%=request.getAttribute("input_textarea")%></textarea>
  <br>
  <textarea  cols="105" rows="5"  name="input_textarea"></textarea><br>
  <input type="submit" value="发送" name="button_one" 
   style="width: 100px; height: 40px;font-size: 25px;"><br>
  </form>
  </body>
</html>