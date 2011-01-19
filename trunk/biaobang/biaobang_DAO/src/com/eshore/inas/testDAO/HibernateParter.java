/**
 * hibernate ʵ�塢���Բ�ѯ�������ֶ���������
 * 
 */

package com.eshore.inas.testDAO;

import java.util.Iterator;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Table;

public class HibernateParter {

 private static Configuration hibernateConf;

 private static Configuration getHibernateConf() {
  if (hibernateConf == null) {
   // return new Configuration().configure();
   return new Configuration();
  }
  return hibernateConf;
 }

 private static PersistentClass getPersistentClass(Class clazz) {

  synchronized (HibernateParter.class) {
   PersistentClass pc = getHibernateConf().getClassMapping(
     clazz.getName());

   if (pc == null) {
    hibernateConf = getHibernateConf().addClass(clazz);
    pc = getHibernateConf().getClassMapping(clazz.getName());
   }
   return pc;
  }
 }

 /**
  * ��ȡʵ���Ӧ�ı���
  * @param clazz ʵ����
  * @return ����
  */
 public static String getTableName(Class clazz) {
  return getPersistentClass(clazz).getTable().getName();
 }

 /**
  * ��ȡʵ���Ӧ��������ֶ�����
  * @param clazz ʵ����
  * @return �����ֶ�����
  */
 public static String getPkColumnName(Class clazz) {
  Table table = getPersistentClass(clazz).getTable();
  return table.getPrimaryKey().getColumn(0).getName();
 }

 /**
  * ͨ��ʵ��������ԣ���ȡʵ�������Զ�Ӧ�ı��ֶ�����
  * @param clazz ʵ����
  * @param propertyName ��������
  * @return �ֶ�����
  */
 public static String getColumnName(Class clazz, String propertyName) {
  PersistentClass persistentClass = getPersistentClass(clazz);
  Property property = persistentClass.getProperty(propertyName);
  Iterator it = property.getColumnIterator();
  if (it.hasNext()) {   
   Column column= (Column)it.next();   
   return column.getName();
  }
  return null;
 }
}

