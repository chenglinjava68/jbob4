<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="javax.sql.rowset.CachedRowSet"%>
<%@ page import="java.sql.ResultSetMetaData"%>

<%  
	CachedRowSet rs = null;
	ResultSetMetaData data = null;
%>

<html>
	<head>    
    <title>�㶫������Ʒ��ַ��ո���ϵͳ</title>
    <link href="../css/pagination.css" rel="stylesheet" type="text/css" media="all" />
	<link href="../css/home.css" type="text/css" rel="stylesheet"/>
	<style type="text/css"> 
	
	/*�ص㣺�̶���ͷ��ʽ*/
	.scrollColThead {position: relative;top: expression(this.parentElement.parentElement.parentElement.scrollTop);z-index:2;}
	
	</style> 
	<script type="text/javascript">
		function String.prototype.trim(){return this.replace(/(^\s*)|(\s*$)/g,"");}
	 		
 		onload=function(){
			selectObj=document.all('tableName');   
	        for (var i=0; i<selectObj.options.length; i++) {
	        	if(selectObj.options[i].value=="${tableName}"){
	            	selectObj.options[i].selected=true; 
	            }  
	        }   
		}
		
		function listover(obj){
			obj.style.backgroundColor = '#FED8D6';
		}
		
		function listout(obj){
			 obj.style.backgroundColor = '#FFFFFF';
		}
		
		function clickLink(ox){
		   if (ox==null)return;
		   //ȡTr��A�ؼ�����
		   var f=ox.getElementsByTagName("A");
		   win=window.open(f[0].href,'','w_workOrderDatail','fullscreen=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=1,titlebar=0');
		   win.focus();
		   win.moveTo(0,0);
		   win.resizeTo(screen.availWidth,screen.availHeight);
		   win.opener=null;
		   if(window!=win){
		     window.close() ;
		   }
		   return false;	
		}
 	</script>
	</head>	
	<body>
	
		<form name="MyForm" method="post" action="">
<table border="0" align="center" cellpadding="0" cellspacing="0" class="mtab mtabtop">
  <tr>
    <td width="14" class="mtopadr">&nbsp;</td>
    <td class="mtopadr2"><img src="../img/adr_tit.gif" width="17" height="14"><strong> �����ڵ�λ�ã�</strong>��ҳ &gt; ���չ���</td>
    <td width="14" class="mtopadr1">&nbsp;</td>
  </tr>
</table>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="mtab mtabtop">
  <tr>
    <td class="mtit"><img src="../img/pictit.gif" width="13" height="13" > ���ղ�ѯ</td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="sreata">
      <tr>
        <th class="sreabg">ʵʩʱ��</th>
        <td><input type="text" name="textfield"></td>
        <th class="sreabg">��Ŀ����</th>
        <td><input type="text" name="textfield2"></td>
        <th class="sreabg">��Ŀ����</th>
        <td><input type="text" name="textfield3"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="60" align="right"><img src="../img/sreach.gif">&nbsp;</td>
  </tr>
</table>
<table border="0" align="center" cellpadding="0" cellspacing="0" class="mtab mtabtop">
  <tr>
    <td class="mtit"><img src="../img/pictit.gif" width="13" height="13" > �����б�</td>
  </tr>
</table>
			<div style="width:95%;height:450px;overflow:auto;z-index=1;visibility:;position:absolute; display:;left: 30px; top: 130px;">
				<table align="left" border="0" cellpadding="0" cellspacing="0" style="background:#C1DAD7;">
        			<tr>
        				<td align="right">
        				&nbsp;���ã�
        				</td>
        				<td>
        				<select name="tableName" onchange="go(1)">
        					<option value="T_BUSI_STATIC_DATA">ҵ��̬����|T_BUSI_STATIC_DATA</option>
        					<option value="T_ZTEAG_ANALYSE_CFG">����ANALYSE|T_ZTEAG_ANALYSE_CFG</option>
        				</select>
        				</td>
        			</tr>
        		</table>
        		</div>
<div style="width:96%;height:300px;overflow:auto;z-index=1;visibility:;position:absolute; display:;left: 30px; top: 204px;">
    <table style="width:100%" border="0" cellspacing="0" cellpadding="0">
      <tr class="scrollColThead">
        <td align="right" class="tablistbg">&nbsp;&nbsp;</td>
      </tr>     
    </table>
</div> 		
<div style="width:96%;height:300px;overflow:auto;z-index=1;visibility:;position:absolute; display:;left: 30px; top: 230px;">
	 		
	<table id="tableData" cellspacing="0" class="listtab" width="100%">
	      <tr class="scrollColThead">
	    	<th class="listtabtit"><input type="checkbox" name="checkbox" value="checkbox"></th>				
			<%
			rs= (CachedRowSet)request.getAttribute("tableData");
			data= rs.getMetaData();
			for(int i = 2 ; i<= data.getColumnCount() ; i++){ 
	              //���ָ���е����� 
	            String columnName = data.getColumnName(i); 
	        %>
	        <th class="listtabtit"><%=columnName %></th>
	        <% 
	        }
		    %>
			<th class="listtabtit">����</th>
	      </tr>
         <% 
		 while(rs.next()){ 
	 	 %>
         	 <tr onmouseout="listout(this)" onmouseover="listover(this)" ondblclick="clickLink(this)">
         	 	<td nowrap><span class="listtabtit">
		          <input type="checkbox" name="checkbox2" value="checkbox">
		        </span></td>
	          <td nowrap><a href="../commonTable/toEditObject.action?whereColumnValue=<%=rs.getString(2) %>&tableName=${tableName}"><%=rs.getString(2) %></a></td>
          	
          	<% 
	            for(int i = 3 ; i<= data.getColumnCount() ; i++){ 
	            
	            //���ָ���е���ֵ 
	            String columnValue = rs.getString(i); 
            %>
	          	<td nowrap><%=columnValue %></td>
          	 <% 				          						           
              }
             %>
             <td nowrap><a href="#">�޸�</a> | <a href="#">ɾ��</a></td>
         	<tr>
         	<% 
           }
		 %>
	</table>
			
</div>
        		
		<!-- ҳ����Ϣ -->	
		<div align="right" style="width:96%;height:20px;margin-left:10px;z-index=7;visibility:;position:absolute; display:;left: 20px; top: 530px;">
			<table style="width:100%" border="0" cellspacing="0" cellpadding="0">
		      <tr class="scrollColThead">
		        <td align="right" class="tablistbg">
				ÿҳ��ʾ&nbsp;<input type="text" name="pageBean.rowCount" value="${pageBean.rowCount}" size="3" maxlength="3" style="border:solid 1px #7f9db9;padding: 1 2;"/>
				����¼&nbsp;&nbsp;&nbsp;
				��ǰ�ǵ�&nbsp;<font color='red'><span id="currPageIndex">${currPageIndex+1}</span></font>/<font color='red'><span id="allPageCount">${pageBean.allPageCount}</span></font> &nbsp;��
				<font color="red"><span id="recordCount">${pageBean.recordCount}</span></font>&nbsp;����¼,��ת
				<input type="text" size="3" id="pageNo" value="1" maxlength="3" style="border:solid 1px #7f9db9;padding: 1 2;" />
				<img src="../images/Next_Down.gif" title="��ת" onclick="goPage()" style="cursor: hand;" />
				<input type="button" id="first" value="��  ҳ" class="right_button_threeds" onClick="firstPage()" />
				<input type="button" id="previous" value="��һҳ" class="right_button_threeds" onClick="previousPage()" />
				<input type="button" id="next" value="��һҳ" class="right_button_three" onClick="nextPage()" />
				<input type="button" id="last" value="β  ҳ" class="right_button_three" onClick="lastPage()" />
				</td>
		      </tr>     
		    </table>
			
		</div>			
		<script type="text/javascript">
			var current=${currPageIndex+1};
			var rowCount=${pageBean.allPageCount};
			//ת����һҳ
			    function nextPage() {	
			    	if(current < rowCount){
			        	current = current + 1;
			        }else if(current == rowCount){     	
			        	return;
			        }
			        if(current == rowCount){	        	
			        	document.all.next.className="right_button_threeds";
			       		document.all.last.className="right_button_threeds";	       		
			        }
			       	document.all.first.className="right_button_three";
			       	document.all.previous.className="right_button_three";
			       	
			       	document.all.currPageIndex.innerHTML=current;
			       	go(current);
			    }
			//ת����һҳ    
			    function previousPage() {
			    	if(current > 1){
			        	current = current - 1;
			        }else{	        	
			        	return;
			        }
			    	if(current == 1){
			        	document.all.first.className="right_button_threeds";
			       		document.all.previous.className="right_button_threeds";
			        }
			        document.all.next.className="right_button_three";
			       	document.all.last.className="right_button_three";
			       	
			       	document.all.currPageIndex.innerHTML=current;
			       	go(current);
			    }
			//ת����һҳ
			    function firstPage() {
			    	if(current == 1){
			    		return;
			    	}else{
			    		current = 1;
			    	}	    	
			        document.all.first.className="right_button_threeds";
			       	document.all.previous.className="right_button_threeds";
			       	document.all.next.className="right_button_three";
			       	document.all.last.className="right_button_three";
			       	
			       	document.all.currPageIndex.innerHTML=current;
			       	go(current);
			    }
			//ת�����һҳ
			    function lastPage() {
			        if(current == rowCount){
			    		return;
			    	}else{
			    		current = rowCount;
			    	}	
			        document.all.first.className="right_button_three";
			       	document.all.previous.className="right_button_three";
			       	document.all.next.className="right_button_threeds";
			       	document.all.last.className="right_button_threeds";
			       	
			       	document.all.currPageIndex.innerHTML=current;
			       	go(current);
			    } 
			// ���������ҳ�ţ�pageNo������ҳ������������� 
			    function goPage() {
			        if (!isNumber(document.all.pageNo.value)){
				        alert("����ȷ������ת��ҳ�룡");
				        return;
			        }
			        var page = parseInt(document.all.pageNo.value);
			        if(page==NaN || page==undefined){
			        	count=1;
			        }else{
			        	current = page;
			        }				        
			        
			        if(current == 1){
			        	document.all.first.className="right_button_threeds";
			       		document.all.previous.className="right_button_threeds";
			       		document.all.next.className="right_button_three";
			       		document.all.last.className="right_button_three";
			        }else if(current == rowCount){
			        	document.all.first.className="right_button_three";
			       		document.all.previous.className="right_button_three";
			        	document.all.next.className="right_button_threeds";
			       		document.all.last.className="right_button_threeds";
			        }
			        document.all.currPageIndex.innerHTML=current;
			        go(current);
			    }
			//ת��ָ��ҳ
			    function go(current) {
			    	
			    	current = current - 1;
			    	document.MyForm.action="../commonTable/queryObject.action?currPageIndex="+current;
					document.MyForm.submit();
			    	
			    }
			    
			    //�ж��Ƿ�Ϊ���֣����򷵻�true,���򷵻�false
				function isNumber( s ){
					var regu = "^[0-9]+$";
					var re = new RegExp(regu);
					if (s.search(re) != -1) {
					    return true;
					} else {
					    return false;
					}
				}
				
				//���µ�ǰҳ������ҳ�����ܼ�¼��
				function setPageRecordCount(pageIndex,pageCount,recordCount){
					if(pageIndex != ''){
						document.all.currPageIndex.innerHTML=pageIndex;
					}
					if(pageCount != ''){
						document.all.allPageCount.innerHTML=pageCount;
					}
					if(recordCount != ''){
						document.all.recordCount.innerHTML=recordCount;
					}
					
				}							
				
			</script>			
		</form>		
		
	</body>
</html>







