package com.cy.erp.testDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cy.erp.daolayer.base.Trackorder;
import com.cy.erp.daolayer.dao.DaoImpl;
import com.cy.erp.daolayer.dao.DaoInit;


public class testDAO extends HibernateDaoSupport {
	private static HibernateTemplate hibernateTemplate;

	private static SessionFactory sessionFactory = null;


	
	static{
		try {
			sessionFactory = DaoInit.init();
			hibernateTemplate = new testDAO().createHibernateTemplate(sessionFactory);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Ĭ�ϵĹ��췽������ʼ��HibernateTemplate��ʹ�õ���Ĭ�ϵ������ļ���etc/tipsDao.xml
	 */
	public testDAO() throws Exception {
		super.setHibernateTemplate(hibernateTemplate);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		try {
			DaoImpl dao=new DaoImpl();
			List list=dao.pubFind("from Trackorder");
			Trackorder bean=(Trackorder)list.get(0);
			System.out.println(bean.getXtime());
//			Trackorder bean=new Trackorder();
//			bean.setId("sadf");
//			bean.setOrderNo("as");
//			dao.save(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static CachedRowSet findBySQL(String sql) throws Exception {
		Connection connection = null;
        PreparedStatement pstmt = null;
        sql="select * from T_BUSI_STATIC_DATA";
        try {
            //oracle����
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url="jdbc:oracle:thin:@132.110.40.5:1521:inasfs";
            connection=DriverManager.getConnection(url,"inasfs","inasfs");

            pstmt = connection.prepareStatement(sql); 
            ResultSet rs=pstmt.executeQuery(sql); 
            ResultSetMetaData data=rs.getMetaData(); 
            while(rs.next()){ 
	            for(int i = 1 ; i<= data.getColumnCount() ; i++){ 
	            //��������е���Ŀ��ʵ������ 
	            int columnCount=data.getColumnCount(); 
	            //���ָ���е����� 
	            String columnName = data.getColumnName(i); 
	            //���ָ���е���ֵ 
	            String columnValue = rs.getString(i); 
	            //���ָ���е��������� 
	            int columnType=data.getColumnType(i); 
	            //���ָ���е����������� 
	            String columnTypeName=data.getColumnTypeName(i); 
	            //���ڵ�Catalog���� 
	            String catalogName=data.getCatalogName(i); 
	            //��Ӧ�������͵��� 
	            String columnClassName=data.getColumnClassName(i); 
	            //�����ݿ������͵�����ַ����� 
	            int columnDisplaySize=data.getColumnDisplaySize(i); 
	            //Ĭ�ϵ��еı��� 
	            String columnLabel=data.getColumnLabel(i); 
	            //����е�ģʽ 
	            String schemaName=data.getSchemaName(i); 
	            //ĳ�����͵ľ�ȷ��(���͵ĳ���) 
	            int precision= data.getPrecision(i); 
	            //С������λ�� 
	            int scale=data.getScale(i); 
	            //��ȡĳ�ж�Ӧ�ı��� 
	            String tableName=data.getTableName(i); 
	            // �Ƿ��Զ����� 
	            boolean isAutoInctement=data.isAutoIncrement(i); 
	            //�����ݿ����Ƿ�Ϊ������ 
	            boolean isCurrency=data.isCurrency(i); 
	            //�Ƿ�Ϊ�� 
	            int isNullable=data.isNullable(i); 
	            //�Ƿ�Ϊֻ�� 
	            boolean isReadOnly=data.isReadOnly(i); 
	            //�ܷ������where�� 
	            boolean isSearchable=data.isSearchable(i); 
	            System.out.println(columnCount); 
	            System.out.println("�����"+i+"���ֶ�����:"+columnName); 
	            System.out.println("�����"+i+"���ֶ�ֵ:"+columnValue); 
	            System.out.println("�����"+i+"������,����SqlType�еı��:"+columnType); 
	            System.out.println("�����"+i+"������������:"+columnTypeName); 
	            System.out.println("�����"+i+"���ڵ�Catalog����:"+catalogName); 
	                System.out.println("�����"+i+"��Ӧ�������͵���:"+columnClassName); 
	                System.out.println("�����"+i+"�����ݿ������͵�����ַ�����:"+columnDisplaySize); 
	                System.out.println("�����"+i+"��Ĭ�ϵ��еı���:"+columnLabel); 
	                System.out.println("�����"+i+"��ģʽ:"+schemaName); 
	                System.out.println("�����"+i+"���͵ľ�ȷ��(���͵ĳ���):"+precision); 
	                System.out.println("�����"+i+"С������λ��:"+scale); 
	                System.out.println("�����"+i+"��Ӧ�ı���:" + tableName); 
	                System.out.println("�����"+i+"�Ƿ��Զ�����:"+isAutoInctement); 
	                System.out.println("�����"+i+"�����ݿ����Ƿ�Ϊ������:"+isCurrency); 
	                System.out.println("�����"+i+"�Ƿ�Ϊ��:"+isNullable); 
	                System.out.println("�����"+i+"�Ƿ�Ϊֻ��:"+isReadOnly); 
	                System.out.println("�����"+i+"�ܷ������where��:"+isSearchable);  
	            } 
            } 
        }
        catch (ClassNotFoundException cnfex) {
            cnfex.printStackTrace();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
		return null;

	}

}
