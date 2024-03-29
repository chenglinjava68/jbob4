package com.jbob.core.batis;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.util.Assert;

import com.jbob.core.batis.cache.BatisEhCache;

public class SqlSessionFactoryFactoryBean implements FactoryBean<SqlSessionFactory>,InitializingBean, DisposableBean {
	Log logger = LogFactory.getLog(SqlSessionFactoryFactoryBean.class);
	private Resource cacheLocation;
	private Resource configLocation;
	private Resource[] mapperLocations;
	private DataSource dataSource;
	private boolean useTransactionAwareDataSource = true;
	private SqlSessionFactory sqlSessionFactory;
	private CacheManager cacheManager;
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(configLocation,"configLocation must be not null");
		if (this.cacheLocation != null) {
			this.cacheManager = new CacheManager(this.cacheLocation.getInputStream());
		}
		sqlSessionFactory = createSqlSessionFactory();
	}

	private SqlSessionFactory createSqlSessionFactory() throws IOException {
		Reader reader = new InputStreamReader(getConfigLocation().getInputStream());
		try {
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			Configuration conf = sqlSessionFactory.getConfiguration();
			if(dataSource != null) {
				DataSource dataSourceToUse = this.dataSource; 
				if (this.useTransactionAwareDataSource  && !(this.dataSource instanceof TransactionAwareDataSourceProxy)) {  
		            dataSourceToUse = new TransactionAwareDataSourceProxy(this.dataSource);  
		        }
				conf.setEnvironment(new Environment("development",new ManagedTransactionFactory(),dataSourceToUse));
				
				if(cacheManager != null){
					String[] cacheNames= cacheManager.getCacheNames();
					
					System.out.println(Arrays.toString(cacheNames));
					for (String id : cacheNames) {
						conf.addCache(new BatisEhCache(cacheManager.getEhcache(id)));
					}
				}
				//sqlSessionFactory = new SqlSessionFactoryBuilder().build(conf);
			}
			
			if(mapperLocations != null) {
				Map<String, XNode> sqlFragments = new HashMap<String, XNode>();
				for(Resource r : mapperLocations) {
					//logger.info("Loading iBatis3 mapper xml from file["+r.getFile().getAbsolutePath()+"]");
					
					Reader mapperReader = new InputStreamReader(r.getInputStream());
					try {
						XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(mapperReader,conf,r.getFilename(),sqlFragments);
						mapperBuilder.parse();
					}finally {
						mapperReader.close();
					}
				}
			}
			return sqlSessionFactory;
		}finally {
			reader.close();
		}
	}
	
	public SqlSessionFactory getObject() throws Exception {
		return sqlSessionFactory;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Class<SqlSessionFactory> getObjectType() {
		return SqlSessionFactory.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Resource getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(Resource configurationFile) {
		this.configLocation = configurationFile;
	}

	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public Resource getCacheLocation() {
		return cacheLocation;
	}

	public void setCacheLocation(Resource cacheLocation) {
		this.cacheLocation = cacheLocation;
	}
	
	public void destroy() {
		logger.info("Shutting down EHCache CacheManager");
		//this.cacheManager.shutdown();
	}


}
