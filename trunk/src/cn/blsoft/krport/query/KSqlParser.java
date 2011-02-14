/**
 *Created on 2009-10-24
 */
package cn.blsoft.krport.query;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:  SqlStringFactory.java </p>  
 * <p>Description: SQL��乹����</p>
 * <p>�﷨��sqlһ��</p>
 * <p>${������}:������ǩ �����������Чsql�����в�����δ��ֵ����</p>
 * <p>#[sql�����]:���ڱ�ǩ ����ǩ����δ��ֵ����ʱ������ǩ��sql������Ϊ��Ч</p>
 * <p></p>
 * <p>Created in 2009-10-24</p>
 * <p>Company: Eshore Technology Co.Ltd.</p>
 *
 * @author wangzhiping 
 * @version v1.0
 */
public class KSqlParser {
	
	/*
	 * #[${}]��Ч����
	 */
	private final String INVALIDREGEX = "\\#\\[[^\\}\\]]*\\$\\{[^\\}\\]]+\\}[^\\]]*\\]";
	/*
	 * #[]��Ч����
	 */
	private final String VALIDREGEX = "\\#\\[([^\\]]*)\\]";
	
	/*
	 * ԭʼ��SQL��䣬��sql��������${������}��������벿��
	 */
	private String ksql;
	
	/*
	 * ����Map
	 */
	private Map<String,String> parameterMap = new HashMap<String,String>();

	public KSqlParser(){};
	
	public KSqlParser(String ksql){
		setKsql(ksql);
	}
	


	/**
	 * Description: ��ȡSQL
	 * @return SQL
	 */
	public String getSql() {
		String sqlString = ksql;
		for(String key:parameterMap.keySet()){
			sqlString = sqlString.replace("${" + key + "}", parameterMap.get(key));
		}
		Pattern validPattern = Pattern.compile(VALIDREGEX); //��Ч����
		Pattern inValidPattern = Pattern.compile(INVALIDREGEX);//��Ч����
		Matcher matcher = inValidPattern.matcher(sqlString);
		while(matcher.find()){//��Чȥ��#[]������
			sqlString = matcher.replaceAll("");
			matcher = inValidPattern.matcher(sqlString);
		}
		matcher = validPattern.matcher(sqlString);
		while(matcher.find()){//��Чȥ��#[]������
			matcher = matcher.reset();
			while(matcher.find()){
				sqlString = sqlString.replace(matcher.group(0), matcher.group(1));//��Чȥ��#[]��ǩ
			}
			matcher = validPattern.matcher(sqlString);
		}

		return sqlString;
	}

	/**
	 * Description: ����ԭʼ��SQL
	 * @param sql ԭʼ��SQL
	 */
	public void setKsql(String ksql) {
		this.ksql = ksql;
	}

	/**
	 * Description: ��ȡ�����ò���Map
	 * @return ����Map
	 */
	public Map<String, String> getParameterMap() {
		return parameterMap;
	}
    
	/**
	 * Description: �����ַ�������
	 * @param parameter ������
	 * @param string �ַ���
	 */
	public void setParam(String parameter,String value){
		if(null!=value && !"".equals(value.trim())){
			parameterMap.put(parameter, value);
		}
	}
			
	/**
	 * Description: �Ƴ�����
	 * @param parameter
	 */
	public void remove(String parameter) {
		parameterMap.remove(parameter);
	}
	
	/**
	 * Description: �Ƴ����в���
	 */
	public void removeAll() {
		parameterMap.clear();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return getSql();		
	}
}
