/**
 * Created on 2010-4-27
 * @version v1.0
 *
 */
package cn.blsoft.krport.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.blsoft.krport.engine.KReportContext;
import cn.blsoft.krport.exception.DBConnectFailException;
import cn.blsoft.krport.exception.DBQueryFailException;
import cn.blsoft.krport.po.Condition;
import cn.blsoft.krport.po.DataSource;
import cn.blsoft.krport.po.KReport;
import cn.blsoft.krport.po.PageData;
import cn.blsoft.krport.returntype.ReturnType;

/**
 * <p>Title:  SqlQuery.java</p>    
 * <p>Description: </p>
 *
 * @author wangzhiping
 */
public class SqlQuery implements Query {

	/**
	 * Description: ���ݲ�ѯ
	 * @param kReport	����
	 * @param param  ����
	 * @return �������ݶ���
	 */
	public static PageData Query(KReport kReport, Map<String, Object> param, int maxRows){
		KSqlParser kSqlParser = createKSqlParser(kReport, param);
		String kSql = kSqlParser.getSql();
		return Query(kReport.getDataSource(), kSql, maxRows);
	}

	/**
	 * Description: ���ݲ�ѯ
	 * @param kReport ����
	 * @param param ����
	 * @param pageable �Ƿ��ҳ
	 * @return �������ݶ���
	 */
	public static PageData Query(KReport kReport, Map<String, Object> param, boolean pageable){
		if(pageable){
			KSqlParser kSqlParser = createKSqlParser(kReport, param);
			String kSql = kSqlParser.getSql();
			return Query(kReport.getDataSource(), kSql, Integer.parseInt((String)param.get("_page")),kReport.getPageSize(), KReportContext.pageMaxRows);			
		}else{
			return Query(kReport, param, KReportContext.pageMaxRows);
		}		
	}
	
	/**
	 * Description: ���ݲ�ѯ
	 * @param kReport	����
	 * @param param  ����
	 * @return �������ݶ���
	 */
	public static PageData Query(KReport kReport, Map<String, Object> param, String kSql, int maxRows){
		KSqlParser kSqlParser = createKSqlParser(kReport, param, kSql);
		return Query(kReport.getDataSource(), kSqlParser.getSql(), maxRows);
	}


	/**
	 * Description: ���ݲ�ѯ
	 * @param dataSource�����ݿ�����
	 * @param sql����ѯ���
	 * @return  �������ݶ���
	 */
	public static PageData Query(String dataSource, String sql, int maxRows) {
		PageData pageData = null;
		Connection conn = getConnection(dataSource);
		try {
			Statement statement = conn.createStatement();
			if(maxRows>0){
				statement.setMaxRows(maxRows);
			}
			ResultSet rs = statement.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			String[] head = new String[rsmd.getColumnCount()+1]; 
			int[] headType = new int[rsmd.getColumnCount()+1]; 
			
			Object[] foot = new Object[rsmd.getColumnCount()+1];
			
			head[0] = "���";
			headType[0] = 4 ;//����
			foot[0] = "";
			
			for(int i=1;i<= rsmd.getColumnCount();i++){
				head[i] = rsmd.getColumnName(i);
				headType[i] = rsmd.getColumnType(i);
				foot[i]=getCountSum(rsmd.getColumnType(i));
			}
			
			List<String[]> bodys = new ArrayList<String[]>();
			int index = 0;
			while(rs.next()){
				String[] body = new String[rsmd.getColumnCount()+1]; 
				body[0] = ++index + "";
				for(int i=1;i <= rsmd.getColumnCount();i++){
					body[i] = rs.getObject(i)==null?"":rs.getObject(i).toString();
					
					Object obj = rs.getObject(i);
					if(obj!=null && foot[i] instanceof Long){
						foot[i] = (Long)foot[i]+((Number)obj).longValue();
					}else if(obj!=null && foot[i] instanceof Double){
						foot[i] = (Double)foot[i]+((Number)obj).doubleValue();
					}					
				}
				bodys.add(body);
			}
			pageData = new PageData(1,bodys.size(),bodys.size(),head,headType,bodys,foot);
			
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new DBQueryFailException(e.getMessage()+sql);
		}
		return pageData;
	}

	/**
	 * Description: 
	 * @param type
	 * @return
	 */
	private static Object getCountSum(int type) {
		Object sum;
		switch (type) {
		case Types.TINYINT:
		case Types.SMALLINT:
		case Types.INTEGER:
		case Types.BIGINT:
			sum=new Long(0);
			break;
		case Types.REAL:
		case Types.NUMERIC:
		case Types.FLOAT:
		case Types.DOUBLE:
		case Types.DECIMAL:
			sum=new Double(0);
			break;
		default:
			sum="";
			break;
		}
		return sum;
	}

	/**
	 * Description: ���ݷ�ҳ��ѯ
	 * @param dataSource�����ݿ�����
	 * @param sql����ѯ���
	 * @param page ��ǰҳ
	 * @param pageSize ҳ��¼��
	 * @return �������ݶ���
	 */
	public static PageData Query(String dataSource, String sql, int page, int pageSize, int maxRows){
		PageData pageData = null;
		Connection conn = getConnection(dataSource);
		try {
			Statement statement = conn.createStatement();
			if(maxRows>0){
				statement.setMaxRows(maxRows);
			}
			ResultSet countRs = statement.executeQuery(getCountSql(sql));
			countRs.next();
			int count = countRs.getBigDecimal(1).intValue();
			page = getRealPage(page,pageSize,count);
			
			ResultSet rs = statement.executeQuery(KReportContext.config.getDialect(KReportContext.kReports.getDataSource(dataSource).getDriver()).getPageableSql(sql,page,pageSize));
			ResultSetMetaData rsmd = rs.getMetaData();
			
			String[] head = new String[rsmd.getColumnCount()]; 
			int[] headType = new int[rsmd.getColumnCount()+1]; 

			Object[] foot = new Object[rsmd.getColumnCount()];
			
			head[0] = "���";
			headType[0]=4; //����
			foot[0] = "";
			
			for(int i=1;i< rsmd.getColumnCount();i++){
				head[i] = rsmd.getColumnName(i);
				headType[i] = rsmd.getColumnType(i);
				foot[i]=getCountSum(rsmd.getColumnType(i));
			}
			
			List<String[]> bodys = new ArrayList<String[]>();
			int index = 0;
			while(rs.next()){
				String[] body = new String[rsmd.getColumnCount()]; 
				body[0] = ++index + "";
				for(int i=1;i < rsmd.getColumnCount();i++){
					body[i] = rs.getObject(i)==null?"":rs.getObject(i).toString();
					
					Object obj = rs.getObject(i);
					if(obj!=null && foot[i] instanceof Long){
						foot[i] = (Long)foot[i]+((Number)obj).longValue();
					}else if(obj!=null && foot[i] instanceof Double){
						foot[i] = (Double)foot[i]+((Number)obj).doubleValue();
					}
				}
				bodys.add(body);
			}
			pageData = new PageData(page,pageSize,count,head,headType,bodys,foot);
			
			countRs.close();
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new DBQueryFailException(e.getMessage()+"(��ҳ)"+sql);
		}
		return pageData;
	}

	
	/**
	 * Description: ȡʵ��ҳ
	 * @param page ��ǰҳ
	 * @param pageSize ҳ��¼��
	 * @param count �ܼ�¼��
	 * @return
	 */
	private static int getRealPage(int page,int pageSize,int count){
		int countPage = (count+pageSize-1)/pageSize;
		if(page>countPage)page=countPage;
		if(page<=1) page=1;
		return page;
	}
	
	/**
	 * Description: �����¼��sql
	 * @param sql ԭsql
	 * @return ��¼��sql
	 */
	private static String getCountSql(String sql){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) from(").append("\n");
		sqlBuffer.append(sql).append("\n");
		sqlBuffer.append(") countsql");
		return sqlBuffer.toString();
	}
	
	

	/**
	 * Description: ����sql���
	 * @param kReport ����
	 * @param param  ����
	 * @return sql���
	 */
	public static KSqlParser createKSqlParser(KReport kReport, Map<String, Object> param) {
		KSqlParser kSqlParser = new KSqlParser(kReport.getkSql());
		Map<String, Condition> conditions = kReport.getConditions();
		for(String name:param.keySet()){
			Object obj = param.get(name);
			if(conditions.containsKey(name)){
				Condition condition = conditions.get(name);
				ReturnType returnType = KReportContext.config.getReturnType(condition.getReturnType());
				if(obj instanceof String){
					kSqlParser.setParam(name, returnType.getKsqlString(condition, (String)obj));
				}else{
					kSqlParser.setParam(name, returnType.getKsqlString(condition, (String[])obj));
				}				
			}else{
				if(obj instanceof String){
					kSqlParser.setParam(name, (String)obj);
				}else{
					kSqlParser.setParam(name, ((String[])obj)[0]);
				}
			}
		}
		return kSqlParser;
	}
	
	/**
	 * Description: ����sql���
	 * @param kReport ����
	 * @param param  ����
	 * @return sql���
	 */
	public static KSqlParser createKSqlParser(KReport kReport, Map<String, Object> param, String kSql) {
		KSqlParser kSqlParser = new KSqlParser(kSql);
		Map<String, Condition> conditions = kReport.getConditions();
		for(String name:param.keySet()){
			Object obj = param.get(name);
			if(conditions.containsKey(name)){				
				Condition condition = conditions.get(name);
				ReturnType returnType = KReportContext.config.getReturnType(condition.getReturnType());
				if(obj instanceof String){
					kSqlParser.setParam(name, returnType.getKsqlString(condition, (String)obj));
				}else{
					kSqlParser.setParam(name, returnType.getKsqlString(condition, (String[])obj));
				}				
			}else{
				kSqlParser.setParam(name, (String)obj);
			}
		}
		return kSqlParser;
	}
	
	/**
	 * Description: ��ȡ���ݿ�����
	 * @param dataSourceName ���ݿ����Ӳ���������
	 * @return ���ݿ�����
	 */
	public static Connection getConnection(String dataSourceName){
		DataSource dataSource = KReportContext.kReports.getDataSource(dataSourceName);
		Connection conn = null;
		try {
			Class.forName(dataSource.getDriver());
			conn = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
		} catch (Exception e) {
			throw new DBConnectFailException(dataSource.getUrl());
		}
		return conn;
	}

}
