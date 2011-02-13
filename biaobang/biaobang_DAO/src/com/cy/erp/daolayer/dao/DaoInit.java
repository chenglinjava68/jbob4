/**
 *com.eshore.tips.daolayer.dao.DaoInit
 *���ߣ�ZhangYong
 *�汾��1.0
 *�޸����ڣ�2005-12-15  21:01:24
 */
package com.cy.erp.daolayer.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * ��ʼ��spring��DAO���
 */
public class DaoInit {
	private static SessionFactory sessionFactory = null;
	private static BeanFactory beanFactory = null;
	private static Logger logger = Logger.getLogger(DaoInit.class);

	/**
	 * ��ʼ��SessionFactory Ĭ�ϵ�spring�����ļ�ȫ·���� etc/RTS_DAO.xml
	 */
	static{
		Resource rs = null;
		SessionFactory tmpFactory = null;
		// ��ȡ�����ļ� RTS_DAO.xml
		try {
			rs = new FileSystemResource("etc/HF_DAO.xml");
		} catch (Exception e) {
			logger.error("��ʼ��Ĭ�������ļ�[RTS_DAO.xml]ʧ��,�����ļ�·��,ϵͳ��ִֹ��",e);
			System.exit(-1);
		}
		// ����bean����
		try {
			beanFactory = new XmlBeanFactory(rs);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("����spring��bean����ʧ��,�����ļ�·��,ϵͳ��ִֹ��", e);
			System.exit(-1);
		}
		
		// ��ȡsession����
		try {
			tmpFactory = (SessionFactory) beanFactory.getBean("sessionFactory");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("��ȡhibernate��session����ʧ��,�����ļ�·��,ϵͳ��ִֹ��", e);
			System.exit(-1);
		}
		
		sessionFactory = tmpFactory;
	}
	
	public static SessionFactory init() throws Exception {
		return sessionFactory;
	}
}
