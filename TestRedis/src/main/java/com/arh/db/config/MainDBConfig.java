package com.arh.db.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class MainDBConfig {

	@Value("${mybatis.config-location}")
	private String mybatisConfigLocation;

	@Autowired
	private MainDBConfigInfo mainDBConfigInfo;

	@Bean(name = "mainDataSource")
	@ConfigurationProperties("datasource.main")
	public DataSource getMainDataSource() {
		BasicDataSource ds = DataSourceBuilder.create().type(BasicDataSource.class).build();
		ds.setMaxTotal(mainDBConfigInfo.getMaxTotal());
		ds.setMaxIdle(mainDBConfigInfo.getMaxIdle());
		ds.setMinIdle(mainDBConfigInfo.getMinIdle());
		ds.setInitialSize(mainDBConfigInfo.getInitialSize());
		ds.setValidationQuery(mainDBConfigInfo.getValidationQuery());
		ds.setTestWhileIdle(mainDBConfigInfo.isTestWhileIdle());
		ds.setTestOnBorrow(mainDBConfigInfo.isTestOnBorrow());
		ds.setTestOnReturn(mainDBConfigInfo.isTestOnReturn());
		ds.setMaxWaitMillis(mainDBConfigInfo.getMaxWaitMillis());
		ds.setDefaultAutoCommit(false);
		return ds;
	}

	@Bean(name = "mainSqlSessionFactory")
	public SqlSessionFactory getMainSqlSessionFactory(@Qualifier("mainDataSource") DataSource ds) throws Exception {
		String path = mybatisConfigLocation.replace("classpath:", "/");
		ClassPathResource resource = new ClassPathResource(path);
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(ds);
		factory.setConfigLocation(resource);
		return factory.getObject();
	}

	@Bean(name = "mainTransactionManager")
	public DataSourceTransactionManager getMainDataSourceTransactionManager(
			@Qualifier("mainDataSource") DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}

	@Bean(name = "mainSqlSessionTemplate")
	public SqlSessionTemplate getMainSqlSessionTemplate(@Qualifier("mainSqlSessionFactory") SqlSessionFactory sf) {
		return new SqlSessionTemplate(sf);
	}
}
