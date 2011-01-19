package com.cy.erp.web.common;

import java.io.FileOutputStream;
import java.util.Date;

/**
 * ����������
 * @author chenchen
 *
 */
public class Util {
	
	/**
	 * �ж��Ƿ��ֵ--String<br>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean isNull(String content) {
		boolean isExist = false;
		if(content == null || content.equals("")){
			isExist = true;
		}
		return isExist;
	}
	
	/**
	 * �ж��Ƿ��ֵ--Double<br>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean isNull(Double content) {
		boolean isExist = false;
		if(content == null || content.equals("")){
			isExist = true;
		}
		return isExist;
	}
	
	/**
	 * �ж��Ƿ��ֵ--Date<br>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean isNull(Date content) {
		boolean isExist = false;
		if(content == null || content.equals("")){
			isExist = true;
		}
		return isExist;
	}
	
	/**
	 * ȡֵ--Double<br>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Double getDouble(Double content) {
		Double isExist = 0.00;
		if(content != null){
			isExist = content;
		}
		return isExist;
	}
	
	/**
	 * ������̬����json
	 * @param bean
	 */
	public static void createComboData(StringBuffer json,String fieldName) {
		try {
			if(json.length()>0){
				String str=json.toString();
				str="{\"id\":\"\",\"text\":\"��ֵ\"}"+str;
				str="["+str+"]";
				createField(str,fieldName);
			}else{
				String str="[{\"id\":\"\",\"text\":\"--����Ϊ��--\"}]";
				createField(str,fieldName);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ����json�ļ�
	 * @param str
	 * @param fieldName
	 */
	public static void createField(String str, String fieldName) {
		try {
			byte[] bytes = str.getBytes("UTF-8");
			
			FileOutputStream fileOutputStream = new FileOutputStream("../webapps/RTS/staticData/"+fieldName+".json");
			fileOutputStream.write(bytes);
			fileOutputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ������̬����json
	 * @param bean
	 */
	public static String createComboData(StringBuffer json) {
		try {
			if(json.length()>0){
				String str=json.toString();
				str="{\"id\":\"\",\"text\":\"��ֵ\"}"+str;
				str="["+str+"]";
				return str;
			}else{
				String str="[{\"id\":\"\",\"text\":\"--����Ϊ��--\"}]";
				return str;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
