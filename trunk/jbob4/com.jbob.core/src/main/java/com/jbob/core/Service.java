/*
 * $Id: Service.java,v 1.2 2010/01/16 11:14:02 huangzhihui Exp $
 * 
 * �ļ����ƣ�Manager.java
 * 
 * �������ڣ�2006-9-7
 * 
 * ��Ȩ���У��㶫��Ѹ�Ƽ����޹�˾
 */

package com.jbob.core;

import java.io.Serializable;
import java.util.List;



/**
 * ����ӿ���Ҫʵ�ֵĹ��ܺ�Dao�ӿ�һ����ͨ�õĹ��ܾ�û�бȽ�ÿ������ӿ����Լ�ʵ����
 * ��Ҳ��һ���Ƚ���Ҫ�Ļ����ṹ��Ψһ���ý������getObject()�������ص���Object��
 * ��ҪѰ��һ���ȽϺõİ취��ʵ�֣�����ط�����ֻ�ܷ���Object���������û�ǿ��ת��
 * 
 * 
 * ���action����ʵ������Ҫת������������������Ҫ��ʱ�����û��Լ�������
 * 
 * @author chenjpu
 * 
 */
public interface Service<T extends Entity,PK extends Serializable> {

	/**
	 * ����id�Ż�ö��󣬿��Է��ء��������null��������Ӧ���쳣����
	 * 
	 * @param id
	 * @return
	 */

	T get(Serializable id) throws BusinessAccessException;

	/**
	 * ����id�Ż�ö���һ�㲻Ӧ�÷���null���������null��Ӧ������Ӧ���쳣����
	 * 
	 * @param id
	 * @return
	 * @throws BusinessAccessException
	 */

	T find(PK id) throws BusinessAccessException;
	
	List<T> findByIds(String ids) throws BusinessAccessException;

	/**
	 * ��ö��������
	 * 
	 * @return
	 */
	int getAllSize() throws BusinessAccessException;

	/**
	 * ���ָ����Χ�ڵĶ��󼯺�
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws BusinessAccessException
	 */
	Pageable<T> getAll(int page, int pageSize) throws BusinessAccessException;

	List<T> getAll() throws BusinessAccessException;

	/**
	 * �洢���� ע��:����ط�Ӧ�û��м���Ƿ���ڴ˼�¼��ҵ���߼�
	 * 
	 * @param newObject
	 * @throws BusinessAccessException
	 */
	Serializable save(T o) throws BusinessAccessException;

	void update(T o) throws BusinessAccessException;

	/**
	 * ɾ����¼
	 * 
	 * @param id
	 * @throws BusinessAccessException
	 */
	void remove(PK id) throws BusinessAccessException;

	void remove(T o) throws BusinessAccessException;
}
