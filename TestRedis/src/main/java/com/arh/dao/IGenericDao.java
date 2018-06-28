package com.arh.dao;

import java.io.Serializable;

import com.arh.exception.DaoException;

public interface IGenericDao<T extends Serializable> {
	T insert(T t) throws DaoException;

	T selectById(Serializable id) throws DaoException;

	void throwDaoException() throws DaoException;
}
