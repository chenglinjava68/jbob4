package com.eshore.inas.daolayer.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.lob.SerializableBlob;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eshore.inas.daolayer.base.AttachmentInfo;
import com.sun.rowset.CachedRowSetImpl;

/**
 * ʵ����DAO�ӿڵ�ʵ���࣬�����Ｏ��Ϊ���е�pojo�ṩͨ�õ�����ɾ���飬�ĵķ�����
 */
public class DaoImpl extends HibernateDaoSupport {

	private static HibernateTemplate hibernateTemplate;

	private static SessionFactory sessionFactory = null;

	public static Logger logger = Logger.getLogger(DaoImpl.class);

	public static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	static {
		try {
			sessionFactory = DaoInit.init();
			hibernateTemplate = new DaoImpl()
					.createHibernateTemplate(sessionFactory);
		} catch (Exception e) {
			logger.error("spring����hibernate�쳣,ϵͳ��ִֹ��", e);
			System.exit(-1);
		}
	}

	/**
	 * Ĭ�ϵĹ��췽������ʼ��HibernateTemplate��ʹ�õ���Ĭ�ϵ������ļ���etc/tipsDao.xml
	 */
	public DaoImpl() throws Exception {
		super.setHibernateTemplate(hibernateTemplate);
	}

	/**
	 * ������������һ����¼
	 * 
	 * @param pk������
	 *            className:��Ҫ�����ı����
	 * @return Object:һ�������
	 */
	public Object findByKey(Serializable pk, Class<?> className)
			throws Exception {
		Object obj = null;
		try {
			obj = getHibernateTemplate().get(className, pk);
			logger.info("����������ѯ" + className
					+ "����ɹ���DaoImlp.java->findByKey(pk,class)");
			if (obj == null) {
				logger.info("���ݿ�û�з��������ļ�¼��[" + className.getName()
						+ "]DaoImlp.java->findByKey(pk,class)");
				return null;
			}
		} catch (Exception e) {
			logger.error("����������ѯ" + className
					+ "����ʧ�ܣ�DaoImlp.java->findByKey(pk,class)");
			throw e;
		}
		return obj;
	}

	/**
	 * �������еļ�¼
	 * 
	 * @param className:��Ҫ�����ı����
	 * @return List:���еı��¼
	 */
	public List<?> findAll(String className) throws Exception {
		List<?> list = null;
		try {
			list = getHibernateTemplate().find("from " + className);
			logger.info("��ѯ���е�" + className
					+ "����ɹ���DaoImlp->findAll(className)");
			if (list.isEmpty()) {
				logger.info("���ݿ�û�з��������ļ�¼��[from " + className
						+ "]DaoImlp.java->findAll(className)");
			}
		} catch (Exception e) {
			logger.error("��ѯ" + className + "����ʧ�ܣ�DaoImlp->findAll(className)");
			throw e;
		}
		return list;
	}

	/**
	 * ��ҳ��ѯ �������HQL��ѯ��䣬���Ը������������磺<br>
	 * from TNode t order by t.id asc<br>
	 * TNode�Ƕ���t�Ǳ�����order by����������t.id��Ҫ��������id������asc������desc�ǽ���<br>
	 * �÷�������ʾ����<br>
	 * List list=dao.findPage("from TNode t order by t.id asc",2,5);<br>
	 * ע���������һ���ǲ�ѯ��䣻"2"��"5"��ʾ�Ǵӵڶ�����¼��ʼ����5����¼����.
	 * 
	 * @param HQL:������Ĳ�ѯ���
	 *            first:��ҳ��ʼ���к� count��ÿҳ��¼��
	 * @return List:���з�ҳ�ı��¼
	 */
	public List<?> findPage(String HQL, int first, int count) throws Exception {
		List<?> list = null;

		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query q = session.createQuery(HQL);
			q.setFirstResult(first);
			q.setMaxResults(count);
			list = q.list();

			logger.info("��ҳ��ѯ�ɹ���[" + HQL + "," + first + "," + count
					+ "]DaoImlp.java->findPage(HQL,first,count)");
		} catch (Exception e) {
			logger.error("��ҳ��ѯʧ�ܣ�[" + HQL + "," + first + "," + count
					+ "]DaoImlp.java->findPage(HQL,first,count)");
			throw e;
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}
		return list;
	}

	/**
	 * ͨ��ĳһ�ֶ������Ҽ�¼
	 * 
	 * @param property:����ֵ
	 *            propertyType�������� className:��Ҫ�����ı����
	 * @return List�����з��������ı��¼
	 */
	public List<?> findByProperty(Object property, String propertyName,
			String ClassName) throws Exception {
		List<?> list = null;
		try {
			if (property instanceof String) {
				list = getHibernateTemplate().find(
						"from " + ClassName + " as t where t." + propertyName
								+ " ='" + property + "'");
			} else {
				list = getHibernateTemplate().find(
						"from " + ClassName + " as t where t." + propertyName
								+ " =" + property);
			}
			logger.info("��ѯ���з���������" + property + "����" + ClassName
					+ "����ɹ���DaoImlp.java->findByProperty(...)");
		} catch (Exception e) {
			logger.error("��ѯ���з���������" + property + "����" + ClassName
					+ "����ʧ�ܣ�DaoImlp.java->findByProperty(...)");
			throw e;
		}
		return list;
	}

	/**
	 * ����һ����¼
	 * 
	 * @param ojb:��Ҫ���ӵĶ���
	 * @return boolean ���ɹ��򷵻�true�����򷵻�false
	 */
	public Serializable save(Object ojb) throws Exception {
		try {
			Serializable srl = getHibernateTemplate().save(ojb);
			logger.info("�������" + ojb + "���ɹ���DaoImlp.java->save()");
			return srl;
		} catch (Exception e) {
			if ((e.getMessage().indexOf(
					"Explicit value specified for identity field in table") != -1)
					&& (e.getMessage().indexOf("is OFF") != -1)) {
				logger
						.error("mapping error�����ݿ�����Ϊ�������У�������PK���óɱ��봦���ô�������ϵDAO����Ա��DaoImlp.java->save(ojb)");
			}
			if (e instanceof IdentifierGenerationException) {
				logger
						.error("����ȱ�������������ݿ�����Ϊ����������ʱ��Ҫ�����������������ɣ�DaoImlp.java->save("
								+ ojb.getClass().getName() + ")");
			}
			logger.error("�������" + ojb + "��ʧ�ܣ�DaoImlp.java->save("
					+ ojb.getClass().getName() + ")");
			throw e;

		}
	}

	/**
	 * �޸�һ����¼��ֻ��Ҫ�޸Ķ�������ԣ�hibernate���Զ�ͬ�������ݿ�
	 * 
	 * @param obj:���޸ĺõĶ���
	 * @return boolean �Ƿ�ɹ�
	 */
	public boolean update(Object obj) throws Exception {

		try {
			getHibernateTemplate().update(obj);
			logger.info("�޸Ķ���" + obj.getClass().getName()
					+ "���ɹ���DaoImlp.java->update(obj)");
			return true;
		} catch (Exception e) {
			if (e.getMessage().indexOf("count: 0") != -1)
				logger.error("���ݿ�û�и�����¼��[" + obj.getClass().getName()
						+ "]DaoImlp.java->update(obj)");
			logger.error("�޸Ķ���" + obj.getClass().getName()
					+ "��ʧ�ܣ�DaoImlp.java->update(obj)");

			throw e;

		}
	}

	/**
	 * ɾ��һ����¼
	 * 
	 * @param ojb:��Ҫɾ���Ķ���
	 * @return boolean �Ƿ�ɹ�
	 */
	public boolean delete(Object ojb) throws Exception {
		try {
			getHibernateTemplate().delete(ojb);
			logger.info("ɾ������" + ojb.getClass().getName()
					+ "���ɹ���DaoImlp.java->delete(ojb)");
			return true;
		} catch (Exception e) {
			if (e.getMessage().indexOf("count: 0") != -1)
				logger.error("���ݿ�û�и�����¼��[" + ojb.getClass().getName()
						+ "]DaoImlp.java->delete(ojb)");
			logger.error("ɾ������" + ojb.getClass().getName()
					+ "��ʧ�ܣ�DaoImlp.java->delete(ojb)");
			throw e;

		}
	}

	/**
	 * ִ��ͨ�õĲ�ѯ
	 * 
	 * @param HQL�����Ƶ�HQL��ѯ���
	 * @return list ���з��������ļ�¼
	 */
	public List<?> pubFind(String HQL) throws Exception {
		List<?> list = null;
		try {
			list = getHibernateTemplate().find(HQL);
			logger.info("ִ��HQL��ѯ�ɹ���[" + HQL + "]DaoImlp.java->findPage");
			if (list.isEmpty()) {
				logger.info("���ݿ�û�з��������ļ�¼��[" + HQL
						+ "]DaoImlp.java->pubFind(HQL)");
			}
		} catch (Exception e) {
			logger.error("ִ��HQL��ѯʧ�ܣ�[" + HQL
					+ "].java->DaoImlp.java->pubFind(HQL)");

			throw e;
		}
		return list;
	}

	/**
	 * ִ��ͨ�õ�����ɾ���Ĳ���
	 * 
	 * @param HQL�����Ƶ�HQL���
	 * @return boolean �ɹ����
	 */
	public boolean pubExe(String HQL) throws Exception {

		Session session = null;
		Transaction transaction = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query q = session.createQuery(HQL);
			q.executeUpdate();
			transaction.commit();

			logger.info("ִ��ͨ��HQL�ɹ���[" + HQL + "]");
			return true;
		} catch (Exception e) {
			logger.error("ִ��ͨ��HQL�쳣[" + HQL + "]����������", e);
			if (transaction != null) {
				try {
					transaction.rollback();
				} catch (Exception ex1) {
					logger.error("���������쳣", ex1);
				}
			}
			throw e;
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}
	}

	/**
	 * ����(��)����IDɾ����¼. ����ֻ��Ϊ8�ֻ������ͺ�String��
	 * 
	 * @param PK:����
	 *            className��������������
	 * @return boolean �Ƿ�ɹ�
	 */
	public boolean delByKey(Serializable PK, Class<?> className)
			throws Exception {

		Session session = null;
		Transaction transaction = null;
		try {
			Object obj = this.findByKey(PK, className);
			if (obj == null) {
				logger.info("��������ɾ����¼ʧ�ܣ�����û�иü�¼��[" + PK.getClass().getName()
						+ "," + className.getName()
						+ "]DaoImlp.java->delByKey(PK,className)");
				return false;
			}
			String hql = null;
			if (PK instanceof String) {
				hql = "delete " + className.getName() + " where id='" + PK
						+ "'";
			} else {
				hql = "delete " + className.getName() + " where id=" + PK;
			}

			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query q = session.createQuery(hql);
			q.executeUpdate();
			transaction.commit();

			logger.info("��������ɾ����¼ok![" + hql
					+ "]DaoImlphqldelByKey(PK,className)");
			return true;
		} catch (Exception e) {
			logger.error("��������ɾ����¼�쳣[" + PK.getClass().getName() + ","
					+ className.getName() + "]��������...", e);
			if (transaction != null) {
				try {
					transaction.rollback();
				} catch (Exception ex1) {
					logger.error("���������쳣", ex1);
				}
			}
			throw e;
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}
	}

	/**
	 * ���ݸ���������Ķ������ɾ����¼. ���������������Ե�������
	 * 
	 * @param PK:������һ������
	 *            pkName:���Ե����� className��������������
	 * @return boolean �Ƿ�ɹ�
	 */
	public boolean delByKey(Serializable PK1, String pk1Name, Serializable PK2,
			String pk2Name, String className) throws Exception {

		Session session = null;
		Transaction transaction = null;
		try {
			String hql = null;
			String key1 = PK1.toString();
			String key2 = PK2.toString();
			if (PK1 instanceof String && PK2 instanceof String) {
				hql = "delete " + className + " where id." + pk1Name + "='"
						+ key1 + "' and id." + pk2Name + "='" + key2 + "'";
			} else if ((!(PK1 instanceof String)) && (!(PK2 instanceof String))) {
				hql = "delete " + className + " where id." + pk1Name + "="
						+ key1 + " and id." + pk2Name + "=" + key2;
			} else if (PK2 instanceof String) {
				hql = "delete " + className + " where id." + pk1Name + "="
						+ key1 + " and id." + pk2Name + "='" + key2 + "'";
			} else if (PK1 instanceof String) {
				hql = "delete " + className + " where id." + pk1Name + "='"
						+ key1 + "' and id." + pk2Name + "=" + key2;
			} else {
				logger
						.error("���ݸ�������ɾ����¼ʧ�ܣ����ܲ������Ͳ�ƥ�䣡DaoImlp.java->delByKey(...)");
				return false;
			}
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query q = session.createQuery(hql);
			q.executeUpdate();
			transaction.commit();

			logger.info("���ݸ�������ɾ����¼ok!DaoImlp.java->delByKey(...)");
			return true;
		} catch (Exception e) {
			logger.error("���ݸ�������ɾ����¼�쳣", e);
			if (transaction != null) {
				try {
					transaction.rollback();
				} catch (Exception ex1) {
					logger.error("����ع�ʧ��", ex1);
				}
			}
			throw e;
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}
	}

	/**
	 * ���ݴ����SQL�����в�ѯ<br>
	 * ���磺<br>
	 * DaoImpl dao=new DaoImpl();<br>
	 * CachedRowSet rs=dao.findBySQL("select * from t_workorder_status");<br>
	 * try {<br>
	 * while(rs.next()){<br>
	 * Object obj1=rs.getObject(1);<br>
	 * Object obj2=rs.getObject(2);<br>
	 * Object obj3=rs.getObject(3);<br>
	 * Object obj4=rs.getObject(4);<br>
	 * System.out.println(obj1.toString()+obj2+obj3+obj4);<br> }<br> } catch
	 * (SQLException e) {<br>
	 * e.printStackTrace();<br> }<br> }<br>
	 * ���ڸ÷������뷵�ؽ�������ʲ���ִ����ɾ����䣻<br>
	 * 
	 * @param sql
	 *            SQL���
	 * @return CachedRowSet���󣬿��������ResulSetһ���������������˶Ͽ����ݿ��Ľ������
	 */
	public CachedRowSet findBySQL(String sql) throws Exception {

		Session session = null;
		Connection conn = null;
		CachedRowSet empl = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			conn = session.connection();
			empl = new OracleCachedRowSet();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			empl.populate(rs);
			logger.info("����SQL��ѯok![" + sql + "]DaoImlp.java->findBySQL(sql)");
			return empl;
		} catch (Exception e) {
			logger.info("����SQL��ѯʧ��![" + sql + "]DaoImlpf.java->findBySQL(sql)");

			throw e;

		} finally {
			// if (empl != null) {
			// try {
			// empl.close();
			// } catch (Exception ex) {
			// logger.error(ex, ex.getMessage());
			// }
			// }
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
			if (sta != null) {
				try {
					sta.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}
	}

	/**
	 * ֱ�Ӵ���SQL���ִ����ɾ�Ĳ������޽�������أ��ʲ��ɽ��в�ѯ����
	 * 
	 * @param sql
	 *            SQL���
	 * @return �ɹ����
	 */
	public boolean executeBySQL(String sql){

		Session session = null;
		Connection conn = null;
		Statement sta = null;
		Transaction transaction = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			conn = session.connection();
			sta = conn.createStatement();
			sta.execute(sql);
			transaction.commit();

			logger.info("ִ��SQL���ɹ�![" + sql + "]DaoImlp.java->executeBySQL(String sql)");
			return true;
		} catch (Exception e) {
			logger.info("ִ��SQL[" + sql + "]����쳣����������...", e);
			if (transaction != null) {
				try {
					transaction.rollback();
				} catch (Exception ex1) {
					logger.error("���������쳣", ex1);
				}
			}
			e.printStackTrace();
		} finally {
			if (sta != null) {
				try {
					sta.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}
		return false;
	}

	/**
	 * ���ݴ����SQL�����в�ѯ<br>
	 * ���磺<br>
	 * DaoImpl dao=new DaoImpl();<br>
	 * CachedRowSet rs=dao.findBySQL("select * from t_workorder_status");<br>
	 * try {<br>
	 * while(rs.next()){<br>
	 * Object obj1=rs.getObject(1);<br>
	 * Object obj2=rs.getObject(2);<br>
	 * Object obj3=rs.getObject(3);<br>
	 * Object obj4=rs.getObject(4);<br>
	 * System.out.println(obj1.toString()+obj2+obj3+obj4);<br> }<br> } catch
	 * (SQLException e) {<br>
	 * e.printStackTrace();<br> }<br> }<br>
	 * ���ڸ÷������뷵�ؽ�������ʲ���ִ����ɾ����䣻<br>
	 * 
	 * @param sql
	 *            SQL���
	 * @return CachedRowSet���󣬿��������ResulSetһ���������������˶Ͽ����ݿ��Ľ������
	 */
	public CachedRowSet oracleFindBySQL(String sql) throws Exception {

		Session session = null;
		Connection conn = null;
		CachedRowSet empl = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();

			conn = session.connection();
			empl = new CachedRowSetImpl();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);

			empl.populate(new ResultSetWrapper(rs));
			logger.info("����SQL��ѯok![" + sql + "]DaoImlp.java->findBySQL(sql)");
			return empl;
		} catch (Exception e) {
			logger.info("����SQL��ѯʧ��![" + sql + "]DaoImlp.java->findBySQL(sql)");

			throw e;

		} finally {
			// if (empl != null) {
			// try {
			// empl.close();
			// } catch (Exception ex) {
			// logger.error(ex, ex.getMessage());
			// }
			// }
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
			if (sta != null) {
				try {
					sta.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}
	}

	/**
	 * ͬ�������ϴ�����
	 * @param file
	 * @param fileName
	 * @param rId
	 * @return
	 */
	public String saveAtt(FileInputStream file, String fileName, String rId, String relativeType) {
		Session session = null;
		Transaction transaction = null;
		byte[] buffer = new byte[1];
		buffer[0] = 1;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			AttachmentInfo attInfo = new AttachmentInfo();

			attInfo.setId(df.format(new Date()));
			attInfo.setRelativeId(rId);
			attInfo.setRelativeType(relativeType);
			attInfo.setName(fileName);
			attInfo.setContent(Hibernate.createBlob(buffer));
			session.save(attInfo);
			session.flush();
			session.refresh(attInfo, LockMode.UPGRADE);
			
			SerializableBlob sb = (SerializableBlob)attInfo.getContent();
			java.sql.Blob wrapBlob = sb.getWrappedBlob();   
			// ͨ����weblogic����������Դ������ӵ����   
			if(wrapBlob instanceof oracle.sql.BLOB){   
				oracle.sql.BLOB blob = (oracle.sql.BLOB) wrapBlob;   
				OutputStream out = blob.getBinaryOutputStream(); 
				
				byte[] data = new byte[(int) file.available()];
				file.read(data);
				out.write(data);   
				out.close();   
			}
			
			session.flush();

			transaction.commit();
			
			session.close();
			return attInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if (session != null){
				try {
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
		return null;
	}
	
	/**
	 * ���ظ���
	 * @param file
	 * @param fileName
	 * @param rId
	 * @return
	 */
	public InputStream downAtt(String id) {
		Transaction transaction = null;
		Session session = null;
		Connection conn = null;
		ResultSet cr = null;
		PreparedStatement pstmt = null;
		oracle.sql.BLOB blob = null;
		InputStream iStream=null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			conn = session.connection();
			String sql="select CONTENT from ATTACHMENT_INFO where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id); 
			cr = pstmt.executeQuery();  
			if (cr.next()){
				blob = (oracle.sql.BLOB)cr.getBlob(1);
			}
			iStream = blob.getBinaryStream();
			transaction.commit();
			
			cr.close();
			pstmt.close();
			session.close();
			return iStream;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if (cr != null) {
				try {
					cr.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}
		return null;
	}
}
