/**
 * Created on 2010-6-18
 * @version v1.0
 *
 */
package cn.blsoft.krport.dialect;

import java.util.Date;

/**
 * <p>Title:  PageSqlParser.java</p>    
 * <p>Description: </p>
 *
 * @author wangzhiping
 */
public interface Dialect {

	/**
	 * Description: �����ҳ��ѯ��䣬���ص�sql����ԭsql��һ���ֶΣ����ڸ�����ҳ����������ֶΣ������һ�����ֶΡ�
	 * @param sql ԭsql
	 * @param page ��ѯҳ
	 * @param pageSize ҳ��¼��
	 * @return ��ҳsql
	 */
	String getPageableSql(String sql,int page,int pageSize);
	
	String getDateString(Date date, String format);
	
}
