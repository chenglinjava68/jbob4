<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
	//����ҳ�治����   
    request.setAttribute("decorator","none");   
    response.setHeader("Cache-Control","no-cache");   
    response.setHeader("Pragma","no-cache");   
    response.setDateHeader("Expires",0);   
%> 
<html>
	<head>    
    <title>���ϴ����ƷERPϵͳ</title>
    <link href="../../css/pagination.css" rel="stylesheet" type="text/css" media="all" />
	<link href="../../css/comm.css" type="text/css" rel="stylesheet"/>
	<script src="../../ajax/comm.js"></script>
	<style type="text/css"> 
	<!--
	.STYLE1 {
		font-size: 28px;
		font-family: "����";
		color: #0066CC;
	}
	-->
	</style> 
	<script type="text/javascript">
		
		function listover(obj){
			obj.style.backgroundColor = '#FED8D6';
		}
		
		function listout(obj){
			 obj.style.backgroundColor = '#FFFFFF';
		}
		
 	</script>
	</head>	
	<body>
	<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3"><div align="right">
    <input type="button" name="button" id="button" value="�����ļ�" onclick="specificationChange(1)" />
    </div></td>
  </tr>
  <tr>
    <td colspan="3"><div align="center"><u class="STYLE1">��Ʒ��������״��</u></div></td>
  </tr>
  <tr>
    <td colspan="3"><div align="center">&nbsp;</div></td>
  </tr>
  <tr>
    <td colspan="3" style="border-top:solid 1px #0066CC;border-right:solid 1px #0066CC;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="listtab">
      <tr height="22">
        <td height="22" align="right">�·�</td>
        <td align="center">������</td>
        <td align="center">������</td>
        <td align="center">��ϴ��</td>
        <td align="center">ˮ����</td>
        <td align="center">��ǿ��</td>
      </tr>
      <tr height="22">
        <td height="22" align="right">1</td>
        <td align="right">5,589 </td>
        <td align="right">2,589 </td>
        <td align="right">46,600 </td>
        <td align="right">2,584 </td>
        <td align="right">5,665 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">2</td>
        <td align="right">8,722 </td>
        <td align="right">7,192 </td>
        <td align="right">71,581 </td>
        <td align="right">2,523 </td>
        <td align="right">9,618 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">3</td>
        <td align="right">8,803 </td>
        <td align="right">5,023 </td>
        <td align="right">87,030 </td>
        <td align="right">2,322 </td>
        <td align="right">10,948 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">4</td>
        <td align="right">9,726 </td>
        <td align="right">7,631 </td>
        <td align="right">75,811 </td>
        <td align="right">2,009 </td>
        <td align="right">9,765 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">5</td>
        <td align="right">8,838 </td>
        <td align="right">10,223 </td>
        <td align="right">73,314 </td>
        <td align="right">1,986 </td>
        <td align="right">8,677 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">6</td>
        <td align="right">9,456 </td>
        <td align="right">7,109 </td>
        <td align="right">82,462 </td>
        <td align="right">2,735 </td>
        <td align="right">8,492 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">7</td>
        <td align="right">6,879 </td>
        <td align="right">7,876 </td>
        <td align="right">72,098 </td>
        <td align="right">3,532 </td>
        <td align="right">8,202 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">8</td>
        <td align="right">8,917 </td>
        <td align="right">6,435 </td>
        <td align="right">77,224 </td>
        <td align="right">8,091 </td>
        <td align="right">9,530 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">9</td>
        <td align="right">10,732 </td>
        <td align="right">7,832 </td>
        <td align="right">97,520 </td>
        <td align="right">6,487 </td>
        <td align="right">10,859 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">10</td>
        <td align="right">9,726 </td>
        <td align="right">8,160 </td>
        <td align="right">73,823 </td>
        <td align="right">8,572 </td>
        <td align="right">9,960 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">11</td>
        <td align="right">8,918 </td>
        <td align="right">5,175 </td>
        <td align="right">84,392 </td>
        <td align="right">13,853 </td>
        <td align="right">14,532 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">12</td>
        <td align="right">10,950 </td>
        <td align="right">5,414 </td>
        <td align="right">91,545 </td>
        <td align="right">8,546 </td>
        <td align="right">12,388 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">�ϼ�</td>
        <td align="right">107,257 </td>
        <td align="right">80,659 </td>
        <td align="right">933,400 </td>
        <td align="right">63,241 </td>
        <td align="right">118,636 </td>
      </tr>
      <tr height="22">
        <td height="22" align="right">�¾�</td>
        <td align="right">8,938 </td>
        <td align="right">6,722 </td>
        <td align="right">77,783 </td>
        <td align="right">5,270 </td>
        <td align="right">9,886 </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
	</body>
</html>







