/*
 * $Id: BusinessAccessException.java,v 1.2 2010/01/18 02:58:06 chenbing Exp $
 * 
 * �ļ����ƣ�BusinessAccessException.java
 * 
 * �������ڣ�2006-9-7
 * 
 * ��Ȩ���У��㶫��Ѹ�Ƽ����޹�˾
 */

package com.jbob.core;

/**
 * ����ҵ������쳣�ṹ��ϵ�ĸ���������һ����������û����Ը�����Ҫʵ�־����ҵ������쳣
 * 
 * �������������null���󣬵ȵȡ�����ط�������ǵķ������ҵ���߼����󣬻��׳�BusinessAccessException�쳣
 * ����spring��ܵ�DataAccessException���ǲ�������װ��ֱ�������׳�
 * 
 * ����쳣��ϵҲ������ʱ�쳣���û�����ѡ���ñ��벶����Щ�쳣
 *
 * @author chenjpu
 *
 */
public class BusinessAccessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7750201126168763208L;

	public BusinessAccessException(String msg) {
		super(msg);
	}

	public BusinessAccessException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
