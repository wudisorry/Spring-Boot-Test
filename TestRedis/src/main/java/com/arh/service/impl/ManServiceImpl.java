package com.arh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arh.dao.IManDao;
import com.arh.exception.DaoException;
import com.arh.pojo.Man;
import com.arh.service.IManService;

@Service
public class ManServiceImpl implements IManService {

	@Autowired
	private IManDao md;

	@Override
	@Transactional(value = "mainTransactionManager", rollbackFor = DaoException.class)
	public void saveMan(Man man) throws DaoException {
		md.insert(man);
	}

	@Override
	@Transactional(rollbackFor = DaoException.class)
	public void saveManRollBack() throws DaoException {
		Man man1 = new Man();
		man1.setInfo("man_temp1");
		man1.setMyDate(new Date());
		md.insert(man1);
		md.throwDaoException();
		Man man2 = new Man();
		man2.setInfo("man_temp2");
		man2.setMyDate(new Date());
		md.insert(man2);
	}

	@Override
	@Cacheable(cacheManager = "cacheManager", value = "mancache", key = "#info", unless = "#result eq null")
	public List<Man> getManByInfo(String info) throws DaoException {
		List<Man> mans = md.selectMansByInfo(info);
		return mans;
	}

	@Override
	@CacheEvict(cacheManager = "cacheManager", value = "mancache")
	public List<Man> clearCache(String info) throws DaoException {
		List<Man> mans = md.selectMansByInfo(info);
		return mans;
	}

}
