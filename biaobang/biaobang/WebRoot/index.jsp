<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.cy.erp.web.model.LoginData;"%>
<%
LoginData loginData = (LoginData) request.getSession().getAttribute("loginData");
if (loginData == null) {
%>
	<script>window.location.href='login.jsp';</script>
<%	
}
%>
<html>
<head>
	<title>���ϴ����ƷERPϵͳ</title>
	<link href="css/comm.css" type="text/css" rel="stylesheet" />
	<script src="ajax/comm.js"></script>
	
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.2/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.2/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.2/jquery.easyui.min.js"></script>
	<script>
		function relogin(){
			send('login/logout.action');
			window.location.reload();
		}
		
		var indexNo=0;
		function addTab(nameTabs,actionURL){
			indexNo++;
			$('#mainTabs').tabs('add',{
				title:nameTabs+indexNo,
				content:'<iframe scrolling="yes" frameborder="0"  src="'+actionURL+'" style="width:100%;height:100%;"></iframe>',
				closable:true
			});
		}
	</script>
</head>
<body class="easyui-layout">
	<!-- div id="mymenu" style="width:150px;">
		<div>item1</div>
		<div>item2</div>
	</div-->
		<div region="north" border="false" style="height:59px;padding:0px;">
			<table width="100%" height="59" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td width="918"><img src="img/toptit.gif" width="918" /></td>
			    <td align="right" valign="bottom" class="topbg"><a href="#" onclick="relogin()"><img src="img/tuichu.png" border="0" /></a></td>
			  </tr>
			</table>
		</div>
		<!-- div region="south" title="South Title" split="true" style="height:100px;padding:10px;background:#efefef;">
			<div class="easyui-layout" fit="true" style="background:#ccc;">
				<div region="center">sub center</div>
				<div region="east" split="true" style="width:200px;">sub center</div>
			</div>
		</div-->
		<!-- div region="east" iconCls="icon-reload" title="Tree Menu" split="true" style="width:180px;">
			<ul class="easyui-tree" url="tree_data.json"></ul>
		</div-->
		<div region="west" split="true" title="ϵͳ�˵�" style="width:168px;padding1:1px;overflow:hidden;">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="��������" selected="true" style="overflow:auto;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="topline">
				      <tr>
				        <td align="center"><a href="#" onclick="addTab('������䵥','jsp/commodityOrder/commodityOrderAdd.jsp');"><img src="img/bot_xiangmu.gif" border="0" /><br/>������䵥</a></td>
				      </tr>
				      <tr>
				        <td align="center"><a href="#" onclick="addTab('�������ѯ','jsp/commodityOrder/commodityOrderQuery.jsp');"><img src="img/bot_shengji.gif" border="0" /><br/>�������ѯ</a></td>
				      </tr>
				    </table>
				</div>
				<div title="ͳ�Ʊ���" style="overflow:auto;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="topline">
				      <tr>
				        <td align="center" ><a href="#" onclick="addTab('���۶�̬','jsp/statanalyse/1.jsp');">���۶�̬</a></td>
				      </tr>
				      <tr>
				        <td align="center" ><a href="#" onclick="addTab('��������״��','jsp/statanalyse/2.jsp');">��������״��</a></td>
				      </tr>
				      <tr>
				        <td align="center" ><a href="#" onclick="addTab('�ͻ��ȼ���������״��','jsp/statanalyse/3.jsp');">�ͻ��ȼ���������״��</a></td>
				      </tr>
				      <tr>
				        <td align="center" ><a href="#" onclick="addTab('��Ʒ��������״��','jsp/statanalyse/4.jsp');">��Ʒ��������״��</a></td>
				      </tr>
				      <tr>
				        <td align="center" ><a href="#" onclick="addTab('�ͻ�������״��','jsp/statanalyse/5.jsp');">�ͻ�������״��</a></td>
				      </tr>
				      <tr>
				        <td align="center" ><a href="#" onclick="addTab('��Ʒ���������','jsp/statanalyse/6.jsp');">��Ʒ���������</a></td>
				      </tr>
				      
				    </table>
				</div>
				<div title="ҵ������ά��">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="topline">
				      <tr>
				        <td align="center"><a href="#" onclick="addTab('�ͻ���Ϣ','jsp/customerInfo/customerInfoQuery.jsp');">�ͻ���Ϣ</a></td>
				      </tr>
				      <tr>
				        <td align="center"><a href="#" onclick="addTab('��Ʒ��Ϣ','jsp/productInfo/productInfoQuery.jsp');">��Ʒ��Ϣ</a></td>
				      </tr>
				      <tr>
				        <td align="center"><a href="#" onclick="addTab('��Ʒ�۸���Ϣ','jsp/commodityPrice/commodityPriceQuery.jsp');">��Ʒ�۸���Ϣ</a></td>
				      </tr>
				    </table>
				</div>
				<div title="ϵͳ����ά��" style="">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="topline">
				      <tr>
				        <td align="center"><a href="#">�û���Ϣ</a></td>
				      </tr>
				      <tr>
				        <td align="center"><a href="#">ϵͳ��ɫ</a></td>
				      </tr>
				      <tr>
				        <td align="center"><a href="#">���ݱ���</a></td>
				      </tr>
				    </table>
				</div>
			</div>
		</div>
		<!-- �ɲ���title="������"  -->
		<div region="center" style="overflow:hidden;">
			<div id="mainTabs"  class="easyui-tabs" fit="true" border="false">
				<div title="������" style="padding:20px;overflow:hidden;"> 
					
					<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" >
				      <tr>
				        <td valign="bottom" align="right"><font>�����д�Ҽ���������޹�˾ ��Ȩ����</font></td>
				      </tr>
				    </table>
					
				</div>
			</div>
		</div>
</body>
</html>

