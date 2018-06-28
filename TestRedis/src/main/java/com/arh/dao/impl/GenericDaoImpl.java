package com.arh.dao.impl;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import com.arh.dao.IGenericDao;
import com.arh.exception.DaoException;

public abstract class GenericDaoImpl<T extends Serializable> implements IGenericDao<T> {

	protected SqlSessionFactory sqlSessionFactory;

	protected SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public abstract void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);

	public abstract void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate);

	public abstract String nameSpaceForSqlId();

	public String sqlId(String sqlId) {
		return nameSpaceForSqlId() + "." + sqlId;
	}

	@Override
	public T insert(T t) throws DaoException {
		// SqlSession session = sqlSessionFactory.openSession();
		// session.insert("", arg1)
		try {
			System.out.println(sqlSessionTemplate);
			sqlSessionTemplate.insert(sqlId("insert"), t);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return t;
	}

	@Override
	public T selectById(Serializable id) throws DaoException {
		return null;
	}

}
