<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath=request.getContextPath();
	//登录成功后，需要把该用户显示至在线用户
	//AppUtil.addOnlineUser(request.getSession().getId(), ContextUtil.getCurrentUser());
	//if(ContextUtil.getCurrentUser().getRights().contains("__ALL")){
		request.setAttribute("IS_MANAGER",true);
	//}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Map"%>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="msthemecompatible" content="no">
		<title>jBob－－企业信息化系统</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/extjs/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/extjs/resources/css/ext-patch.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/extjs/ux/css/Portal.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/extjs/ux/css/Ext.ux.UploadDialog.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/extjs/ux/css/DateTimePicker.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css"/>
		<!-- 
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/loading.css"/> -->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/extjs/ux/css/ux-all.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/extjs/ux/caltask/calendar.css" />
		
		<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/adapter/ext/ext-base.gzjs"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ext-all.gzjs"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ScriptMgr.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/Ext.ux.IconCombob.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/App.import.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/AppUtil.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/print/Printer-all.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/print/renderers/GridPanel.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/export/Exporter-all.js"></script>

		<script type="text/javascript" src="<%=basePath%>/extjs/ux/RowEditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/Fckeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/XmlTreeLoader.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/FileUploadField.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/UploadDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/CheckColumn.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/Portal.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/PortalColumn.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/Portlet.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/Toast.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/SystemCalendar.js"></script>

		<script type="text/javascript" src="<%=basePath%>/js/core/TreeSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/system/FileAttachDetail.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/UserSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/UserSubSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/DepSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/RoleSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/extjs/ux/DateTimeField.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/MessageDetail.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/flow/ProcessNextForm.js"></script>
	
		<script type="text/javascript" src="<%=basePath%>/js/selector/GoodsSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/CarSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/CustomerSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/date.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/OnlineUserSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/BookSelector.js"></script>	
		
		<script type="text/javascript" src="<%=basePath%>/js/info/MessageWin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/MessageReplyWin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/ProjectSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/ProviderSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/personal/DutyView.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/personal/DutyForm.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/sound/soundmanager2.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/search/SearchForm.js"></script>
	    <script type="text/javascript">
	       var __companyName="jBob";
	    </script>
	    <script type="text/javascript" src="<%=basePath%>/js/IndexPage.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/js/App.home.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/App.js"></script>
	</head>
	<body oncontextmenu="return false">
		<div id="loading">
             <div  class="loading-indicator">
                  <img src="<%=basePath%>/images/loading.gif" alt="" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>
         正在加载,请稍候......
             </div>
         </div>
         <div id="loading-mask">
         </div>
		<div id="app-header">
			<div style="float:left;max-width:350px;height:50px;width: auto;"><img id ="CompanyLogo" src="" height="50" style="max-width:230px;"/><img src="<%=basePath%>/images/ht-oa.png" height="50"/></div>
			<div id="welcomeMsg">欢迎您，<security:authentication property="principal.fullname"/>，[<a href="<%=basePath%>/j_logout.do">注销</a>]</div>
			<div id="currentTime"><span id="nowTime"></span><span id="nowTime2"></span></div>
			<div id="setting">
				<a href="<%=basePath%>/help/20091225001.zip" target="blank">帮助</a>
				<c:if test="${IS_MANAGER ==true}">
					|<a href="#" onclick="App.clickTopTab('SysConfigView')">设置</a>
				</c:if>
			</div>
			<div style="height:0px;clear:both"></div>
			<div style="float:left;width:600px;">
				<ul id="header-topnav">
					<li class="activeli"><a href="#" onclick="App.MyDesktopClick()">我的桌面</a></li>
					<li class="commonli"><a href="#" onclick="App.clickTopTab('PersonalMailBoxView')">邮件</a></li>
					<li class="commonli"><a href="#" onclick="App.clickTopTab('CalendarPlanView')">任务</a></li>
					<li class="commonli"><a href="#" onclick="App.clickTopTab('WorkPlanView')">计划</a></li>
					<li class="commonli"><a href="#" onclick="App.clickTopTab('PersonalDocumentView')">文档</a></li>
				</ul>
			</div>
			<div id="searchFormDisplay" style="height:18px;width:280px;float:right;">&nbsp;</div>
		</div>
	</body>
</html>