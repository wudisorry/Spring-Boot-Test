package com.arh.dao;

import java.util.List;

import com.arh.exception.DaoException;
import com.arh.pojo.Man;

public interface IManDao extends IGenericDao<Man>{
	
	List<Man> selectMansByInfo(String info) throws DaoException;
}
