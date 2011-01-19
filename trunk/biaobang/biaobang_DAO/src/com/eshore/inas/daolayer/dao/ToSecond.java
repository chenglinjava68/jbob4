package com.eshore.inas.daolayer.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

public class ToSecond implements SQLFunction {

	public Type getReturnType(Type arg0, Mapping arg1) throws QueryException {
		// TODO �Զ����ɷ������
		return Hibernate.INTEGER;
	}

	public boolean hasArguments() {
		// TODO �Զ����ɷ������
		return true;
	}

	public boolean hasParenthesesIfNoArguments() {
		// TODO �Զ����ɷ������
		return true;
	}

	public String render(List list, SessionFactoryImplementor arg1) throws QueryException {
		// TODO �Զ����ɷ������
		if (list.size() != 1) {
            throw new IllegalArgumentException("����ֻ����1������!"); 
        }
		return list.get(0)+" * 60 ";

	}	

}
