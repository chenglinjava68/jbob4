/**
 * Created on 2010-5-16
 * @version v1.0
 *
 */
package cn.blsoft.krport.engine;

import cn.blsoft.krport.po.Config;
import cn.blsoft.krport.po.KReports;

/**
 * <p>Title:  Context.java</p>    
 * <p>Description: </p>
 *
 * @author wangzhiping
 */
public class KReportContext {

	public static String BASE;						//�����λ��
	
	public static String ROOTPATH;					//��·��
	
	public static KReports kReports;				//��������
	
	public static Config config;					//�������
	
	public static int pageMaxRows = 1000;			//ҳ���ѯ�������
	
	public static int exportMaxRows = 2000;		//�����������
	
}
