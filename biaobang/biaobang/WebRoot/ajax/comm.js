/**
 * ����������JS
 */
 
//ȥ�����ҿո�
//function String.prototype.trim(){
//	return this.replace(/(^\s*)|(\s*$)/g,"");
//}
 
//��ȡ�����Ͳ�������
function send(url){
	if(window.ActiveXObject){//΢�������
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHTTPRequest){ //���������
		xmlHttp=new XMLHTTPRequest();
	}
	xmlHttp.open("get",url,false);
	xmlHttp.onreadystatechange = callback;
	xmlHttp.send(null);
}

//readyState��ʾXMLHttpRequest����Ĵ���״̬��
//0:XMLHttpRequest����û����ɳ�ʼ����
//1:XMLHttpRequest����ʼ��������
//2:XMLHttpRequest�������������ɡ�
//3:XMLHttpRequest����ʼ��ȡ����������Ӧ��
//4:XMLHttpRequest�����ȡ��������Ӧ������
function callback(){
	if(xmlHttp.readyState ==4){
		if(xmlHttp.status == 200){
			strText=xmlHttp.responseText;
		}
	}
}
	