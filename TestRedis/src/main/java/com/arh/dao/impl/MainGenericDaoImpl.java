package com.arh.dao.impl;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class MainGenericDaoImpl<T extends Serializable> extends GenericDaoImpl<T> {

	@Autowired
	@Override
	public void setSqlSessionFactory(@Qualifier("mainSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Autowired
	@Override
	public void setSqlSessionTemplate(@Qualifier("mainSqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
