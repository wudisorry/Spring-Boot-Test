package com.arh.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arh.service.ILeaderBoardService;

@Controller
public class LeaderBoardController {

	@Autowired
	private ILeaderBoardService lbs;

	@RequestMapping(value = "/vote/{name}", method = RequestMethod.GET)
	public String test(@PathVariable("name") String name) {
		lbs.vote(name);
		return null;
	}

	@RequestMapping("getTopTen")
	public String testGetTopTen() {
		Set<Object> objs = lbs.getTopTen();
		for (Object object : objs) {
			System.out.println(object);
		}
		return null;
	}
}
