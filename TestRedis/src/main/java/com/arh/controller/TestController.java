package com.arh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arh.exception.DaoException;
import com.arh.pojo.Man;
import com.arh.service.IManService;

@RestController
public class TestController {

	@Autowired
	private IManService manService;

	@RequestMapping("saveman")
	public Man testSaveMan() {
		Man man = new Man();
		man.setMyDate(new Date());
		man.setInfo("hello wo");
		try {
			manService.saveMan(man);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return man;
	}

	@RequestMapping("savemanrb")
	public Man testSaveManRollBack() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName);
		try {
			manService.saveManRollBack();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("getmans")
	public List<Man> getMans() {
		List<Man> mans = null;
		try {
			mans = manService.getManByInfo("hello wo");
			for (Man man : mans) {
				System.out.println(man);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return mans;
	}

	@RequestMapping("clearmans")
	public Man clearMans() {
		try {
			manService.clearCache("hello wo");
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

}
