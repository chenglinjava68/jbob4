<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="javax.sql.rowset.CachedRowSet"%>
<%@ page import="java.sql.ResultSetMetaData"%>
<% 
	//����ҳ�治����   
    request.setAttribute("decorator","none");   
    response.setHeader("Cache-Control","no-cache");   
    response.setHeader("Pragma","no-cache");   
    response.setDateHeader("Expires",0);   
%>
<%  
	CachedRowSet rs = null;
	ResultSetMetaData data = null;
%>
<html>
	<head>    
    <title>ҵ�����ϵͳ</title>
    <link href="../../css/comm.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.2/themes/icon.css">
	<script type="text/javascript" src="../../jquery-easyui-1.2/jquery-1.4.4.js"></script>
	<script type="text/javascript" src="../../jquery-easyui-1.2/jquery.easyui.min.js"></script>
</head>

<body>
<script>
	onload=function(){
		$('#pjSources').combobox({
				url:'../../staticData/pjSourceInfo.json',
				missingMessage : '����',
				valueField:'id',
				textField:'text',
				onLoadSuccess:function(){
					$('#pjSources').combobox('setValue', '${bean.pjSources}');
				}
			});
			
		$('#pjCategory').combobox({
				url:'../../staticData/pjStatusInfo.json',
				missingMessage : '����',
				valueField:'id',
				textField:'text',
				onLoadSuccess:function(){
					$('#pjCategory').combobox('setValue', '${bean.pjCategory}');
				}
			});
	   $('#pjDepartment').combobox({
				url:'../../staticData/organizationInfo.json',
				missingMessage : '����',
				valueField:'id',
				textField:'text',
				onLoadSuccess:function(){
					$('#pjDepartment').combobox('setValue', '${bean.pjDepartment}');
				}
		    });
	    $('#pjManager').combobox({
				url:'../../staticData/pjAuditorsInfo.json',
				missingMessage : '����',
				valueField:'id',
				textField:'text',
				onLoadSuccess:function(){
					$('#pjManager').combobox('setValue', '${bean.pjManager}');
				}
		    });
		$('#pjName').validatebox({
				missingMessage : '����.ע��20����������'
			});
		$.extend($.fn.validatebox.defaults.rules, { 
			    minLength: { 
			        validator: function(value, param){ 
			            return value.length >= param[0]; 
			        }, 
			        message: 'Please enter at least {0} characters.' 
			    },
			    maxLength: { 
			        validator: function(value, param){ 
			            return value.length <= param[0]; 
			        }, 
			        message: 'Please enter at least {0} characters.' 
			    }
			});
	}
		
		/*  
		 ��Date/String����,����ΪString����.  
		 ����String����,���Ƚ���ΪDate����  
		 ����ȷ��Date,���� ''  
		 ���ʱ�䲿��Ϊ0,�����,ֻ�������ڲ���.  
		 */
		function formatDate(v) {
			if (v instanceof Date) {
				var y = v.getFullYear();
				var m = v.getMonth() + 1;
				var d = v.getDate();
				var h = v.getHours();
				var i = v.getMinutes();
				var s = v.getSeconds();
				var ms = v.getMilliseconds();
				if (ms > 0)
					return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s
							+ '.' + ms;
				if (h > 0 || i > 0 || s > 0)
					return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;
				return y + '-' + m + '-' + d;
			}
			return '';
		}
	
		$( function() {
			$('#pjItime').datebox( {
				currentText : '����',
				closeText : '�ر�',
				disabled : false,
				required : true,
				missingMessage : '����',
				formatter : formatDate
	
			});
		});
		
		function quxiao(){
		   parent.document.all.addObjectDIV.style.display="none";
		}
		
		function pjInfoSaveEdit(){
			document.MyForm.action="../../pjInfo/saveObjectEdit.action";
			document.MyForm.submit();
			parent.document.all.addObjectDIV.style.display="none";
			
			alert("�����ɹ�!");
			parent.pjInfoQuery();
		}
		
		function addAttRow(str){
			var k=document.all.attList.rows.length;
			newRow=document.all.attList.insertRow(k);	
			newRow.ln=k;
			newRow.id=k;
			newRow.align="left"; 
			newRow.style.backgroundColor="#e8e6e7"; 
			newRow.style.height="26px";
			
			c1=newRow.insertCell(0);
			c1.innerHTML="&nbsp;<img src='../../img/fujian1.gif' />����"+"-"+str;
			
		}
		
		function addFile(){
			var k=document.all.attFileList.rows.length;
			newRow=document.all.attFileList.insertRow(k);	
			newRow.ln=k;
			newRow.id=k;
			
			c1=newRow.insertCell(0);
			c1.innerHTML="<img src='../../img/fujian.gif'/><input type='file' name='upload' id='upload' style='display:;' onchange='addAttRow(this.value)' />&nbsp;<a href='#' onclick='addFile()'>���</a>&nbsp;&nbsp;&nbsp;&nbsp;";
			
		}
		
		function downAtt(id,name){
			document.MyForm.action="../../attachment/downAtt.action?id="+id+"&downFileName="+name;
			document.MyForm.submit();
		}
	</script>
	<form name="MyForm" method="post" action="" enctype="multipart/form-data">
	<input type="hidden" id="pjNo" name="bean.pjNo" value="${bean.pjNo}" />
	<input type="hidden" id="pjCtime" name="bean.pjCtime" value="${bean.pjCtime}" />
	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="divtopleft">&nbsp;</td>
        <td class="divtopmid">�޸���Ŀ</td>
        <td class="divtopright">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" style="border-left:solid 1px #6890b0; border-right:solid 1px #6890b0;">
    <table width="100%" border="0" cellspacing="8" cellpadding="0" style="margin:12px 0;">
    	<tr>
    		<td style="vertical-align:top;">
			    <table width="100%">
			      <tr>
			        <td align="right">��Ŀ��Դ��</td>
			        <td>
			        	<select id="pjSources" class="easyui-combobox" name="bean.pjSources" required="true">
			        	</select>
			       	</td>
			      </tr>
			      <tr>
			        <td align="right">��Ŀ���</td>
			        <td>
						<select id="pjCategory" class="easyui-combobox" name="bean.pjCategory" required="true">
			        	</select>
					</td>
			       </tr>
			      <tr>
			        <td align="right">��Ŀ���ƣ�</td>
			        <td><input id="pjName" name="bean.pjName" class="easyui-validatebox" value="${bean.pjName}" validType="maxLength[20]" invalidMessage="ע��20����������" required="true" />
			        </td>
			      </tr>
			      <tr>
			        <td align="right">ʵʩʱ�䣺</td>
			        <td><input id="pjItime" class="easyui-datebox" name="bean.pjItime" value="${bean.pjItime}" required="true" readonly="true"></input></td>
			      </tr>
			      <tr>
			        <td align="right">����Ƶ�λ��</td>
			        <td><select id="pjDepartment" class="easyui-combobox" name="bean.pjDepartment" required="true">
			        </select></td>
			      </tr>
			      <tr>
			        <td align="right">��Ŀ������Ա��</td>
			        <td><select id="pjManager" class="easyui-combobox" name="bean.pjManager" required="true">
			        </select></td>
			      </tr>
			      </table>
      </td>
      <td rowspan="6" style="vertical-align:top;">
      	<div style="width:100%;height:140px;overflow:auto;display:;">
		<table id="attFileList">
			<tr>
				<td>
					<img src="../../img/fujian.gif"/>
					<input type="file" name="upload" id="upload" style="display:;" onchange="addAttRow(this.value)" />&nbsp;
					<a href="#" onclick="addFile()">���</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		</div>
      </td>
      </tr>
      <tr>
        <td colspan="4" align="right">
        <div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="attList">
			      <% 
		       	rs= (CachedRowSet)request.getAttribute("tableDataPjInfoAtt");
				data= rs.getMetaData();
				 while(rs.next()){
			 	 %>
			 	 	<tr>
			 	 		<td height="26" align="left" bgcolor="#e8e6e7">
			 	 			&nbsp;<img src='../../img/fujian1.gif' />����-<a href="#" onclick="downAtt('<%=rs.getString(1) %>','<%=rs.getString(4) %>')"><%=rs.getString(4) %></a>
			 	 		</td>
			 	 	</tr>
			 	 <% 
		           }
		           rs.close();
				 %>
	    	</table>
    	</div>
		</td>
      </tr>
    </table></td>
  </tr>
   <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="divtopleft01">&nbsp;</td>
        <td align="right" class="divtopmid01">
        <font style="color:red;">ע�⣺������С���ܳ���20M.</font>&nbsp;&nbsp;
        <img src="../../img/bot_queding.gif" style="cursor:hand;" onclick="pjInfoSaveEdit()"/> 
        <img src="../../img/bot_quxiao.gif" style="cursor:hand;" onclick="quxiao();"/></td>
        <td class="divtopright01">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
