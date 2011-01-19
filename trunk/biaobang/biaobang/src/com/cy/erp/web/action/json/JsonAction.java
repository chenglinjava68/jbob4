package com.cy.erp.web.action.json;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.cy.erp.web.common.BaseAction;

	
public class JsonAction extends BaseAction {
	private static final long serialVersionUID = -2407572876245484007L;

	private JsonService jsonService;
	
	private InputStream inputStream;
	
	private String rId;
	
	/**
	 * ��ȡ��Ŀ��Ϣ��json
	 * @return
	 */
	public String getPjInfo() {
		try {
			String json=jsonService.getPjInfo();
			inputStream = new ByteArrayInputStream(json.getBytes("GBK"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * ��ȡ���ն���������Ϣ��json
	 * @return
	 */
	public String getRiskLevelInfo2() {
		try {
			String json=jsonService.getRiskLevelInfo2(rId);
			inputStream = new ByteArrayInputStream(json.getBytes("GBK"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * ��ȡ��������������Ϣ��json
	 * @return
	 */
	public String getRiskLevelInfo3() {
		try {
			String json=jsonService.getRiskLevelInfo3(rId);
			inputStream = new ByteArrayInputStream(json.getBytes("GBK"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public JsonService getJsonService() {
		return jsonService;
	}


	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}

	public String getRId() {
		return rId;
	}

	public void setRId(String id) {
		rId = id;
	}
}
