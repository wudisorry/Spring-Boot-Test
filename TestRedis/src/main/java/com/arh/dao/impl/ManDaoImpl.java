package com.arh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arh.dao.IManDao;
import com.arh.exception.DaoException;
import com.arh.pojo.Man;

@Repository
public class ManDaoImpl extends MainGenericDaoImpl<Man> implements IManDao {

	@Override
	public List<Man> selectMansByInfo(String info) throws DaoException {
		List<Man> list = sqlSessionTemplate.<Man>selectList(sqlId("selectManByInfo"), info);
		return list;
	}

	@Override
	public String nameSpaceForSqlId() {
		return "com.arh.dao.IManDao";
	}

	@Override
	public void throwDaoException() throws DaoException {
		throw new DaoException("Artificially throwing an exception");
	}

}
