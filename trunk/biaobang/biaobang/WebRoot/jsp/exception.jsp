<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>������!</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body style="font-size:12px;">
    <span onclick="window.history.back();" style="cursor:hand;" >���� �ء� </span>   ҳ�������......
  <div id="tishi" style="cursor:hand;"  onclick="info.style.display='';tishi.style.display='none'"><br>��ϸ������Ϣ......</div>
  <div id="info" style="cursor:hand;display:none;width:100%;height:250px;border:1px #CCCCCC solid; OVERFLOW-y:auto">
		<div style="color:#900;">
		  <h3><s:property value="exception.message"/></h3>
		</div>
		<div style="color:red;">
		  <s:property value="exceptionStack"/>
		</div>
  </div>
  
  </body>
</html>
