package com.cy.erp.web.action.json;

import java.util.List;

import com.cy.erp.web.common.Util;
import com.cy.erp.web.dataGate.Globe;
import com.eshore.inas.daolayer.base.PjInformation;
import com.eshore.inas.daolayer.base.RiskLevelInfo;

public class JsonService {
	
	/**
	 * ��ȡ��Ŀ��Ϣ��json
	 * @param rId
	 * @return
	 */
	public String getPjInfo(){
		try {
			//��Ŀ��Ϣ
			List<PjInformation> pjInfoList = (List<PjInformation>) Globe.dao.findAll("PjInformation order by pjName");
			
			//����json����
			StringBuffer json = new StringBuffer();
			for (PjInformation bean : pjInfoList) {
				
				json.append(",{");
				json.append("\"id\":\"");
				json.append(bean.getPjNo());
				json.append("\",\"text\":\"");
				json.append(bean.getPjName());
				json.append("\"}");
			}
			//������̬����ComboData
			String str = Util.createComboData(json);
			return str;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��ȡ���ն���������Ϣ��json
	 * @param rId
	 * @return
	 */
	public String getRiskLevelInfo2(String rId){
		try {
			//���շ���2
			List<RiskLevelInfo> riskLevelInfoList2 = (List<RiskLevelInfo>) Globe.dao.findAll("RiskLevelInfo where riskLevel='2' and relativeId='"+rId+"' order by riskLevelName");
			
			//����json����
			StringBuffer json = new StringBuffer();
			for (RiskLevelInfo bean : riskLevelInfoList2) {
				
				json.append(",{");
				json.append("\"id\":\"");
				json.append(bean.getId());
				json.append("\",\"text\":\"");
				json.append(bean.getRiskLevelName());
				json.append("\"}");
			}
			//������̬����ComboData
			String str = Util.createComboData(json);
			return str;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��ȡ��������������Ϣ��json
	 * @param rId
	 * @return
	 */
	public String getRiskLevelInfo3(String rId){
		try {
			//���շ���2
			List<RiskLevelInfo> riskLevelInfoList3 = (List<RiskLevelInfo>) Globe.dao.findAll("RiskLevelInfo where riskLevel='3' and relativeId='"+rId+"' order by riskLevelName");
			
			//����json����
			StringBuffer json = new StringBuffer();
			for (RiskLevelInfo bean : riskLevelInfoList3) {
				
				json.append(",{");
				json.append("\"id\":\"");
				json.append(bean.getId());
				json.append("\",\"text\":\"");
				json.append(bean.getRiskLevelName());
				json.append("\"}");
			}
			//������̬����ComboData
			String str = Util.createComboData(json);
			return str;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
