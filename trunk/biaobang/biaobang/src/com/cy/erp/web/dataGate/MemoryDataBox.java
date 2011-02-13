package com.cy.erp.web.dataGate;

import javax.servlet.http.HttpServlet;
import javax.sql.rowset.CachedRowSet;

import com.cy.erp.daolayer.dao.DaoImpl;

/*
 * �ڴ�����
 */
public class MemoryDataBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ��ʼ��ȫ��ʹ�õľ�̬����
	 * 
	 * @param sc
	 */
	@SuppressWarnings("unchecked")
	public void init() {
		
		try {
			super.init();
			String path = this.getServletContext().getRealPath("");	
			Globe.realContextPath.clear();
			Globe.realContextPath.put("realContextPath", path);
			Globe.context = this.getServletContext();
			if (Globe.dao == null)
				Globe.dao = new DaoImpl();
			this.loadData();

		} catch (Exception e) {
			Globe.logger.error("ϵͳ���ؾ�̬����ʧ�ܡ�", e);
		}
	}

	@SuppressWarnings( { "unused", "unchecked" })
	public synchronized void loadData() {
		CachedRowSet rowset = null;

		try {
			
			
			Globe.logger.info("ϵͳ���ؾ�̬���ݳɹ�!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//��������ӣ�����Щ���ùر�
		}
	}
}
