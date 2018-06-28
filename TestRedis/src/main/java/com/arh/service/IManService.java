package com.arh.service;

import java.util.List;

import com.arh.exception.DaoException;
import com.arh.pojo.Man;

public interface IManService {

	public void saveMan(Man man) throws DaoException;

	public void saveManRollBack() throws DaoException;

	public List<Man> getManByInfo(String info) throws DaoException;

	public List<Man> clearCache(String info) throws DaoException;

}
