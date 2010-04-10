package com.jbob.core;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author chenjpu
 * ����dao�ĸ��ӿ�,����ʵ��ͨ�������ݿ����
 * 
 * ע��::����ӿڸĽ�,��Ҫ����jdk1.5������
 * ���÷�������������.
 * @version 1.2
 */
public interface GenericDao<T extends Entity,PK extends Serializable> {
	/**
	 * �����ݿ��ѯ���������Ķ���,�������ǲ������Ҳ�����������,ӦΪ����ҵ��Ҫ��
	 * ���ݿ����Ƿ�����������¼
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	T select(PK id);

	/**
	 * ����ĳ����������ݿ��еļ�¼��,��ҳ�õıȽ϶�
	 * @return
	 * @throws DataAccessException
	 */
	int count();

	/**
	 * �������ݿ��д����¼����������,��id����
	 * @return
	 * @throws DataAccessException
	 */
	List<T> select();

	/**
	 * �������ݿ��д����¼������,�����ѯ�߽�.��Ҫ����iBatis��,���ڻ�û���о�hibernate�����������ôʵ��
	 * @param skip
	 * @param pageSize
	 * @return
	 * @throws DataAccessException
	 */
	List<T> select(int skip, int max);

	/**
	 * �������ݼ�¼,����id�Ƿ�Ϊ�����ж��Ƿ�����¼�¼���Ǹ��²���
	 * @param newObject
	 * @throws DataAccessException
	 */
	T insert(T entity);

	/**
	 * �������ݼ�¼
	 * @param Object
	 * @throws DataAccessException
	 */
	void update(T entity);
	
	void merge(T entity);
	
	/**
	 * ɾ����¼
	 * @param id
	 * @throws DataAccessException
	 */
	void delete(PK id);
	void delete(T entity);

}
