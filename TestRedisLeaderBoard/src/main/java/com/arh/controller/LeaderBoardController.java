package com.arh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arh.exception.ServiceException;
import com.arh.pojo.UserWrapper;
import com.arh.service.ILeaderBoardService;

@RestController
public class LeaderBoardController {

	@Autowired
	private ILeaderBoardService lbs;

	@RequestMapping(value = "vote/{id}", method = RequestMethod.GET)
	public String vote(@PathVariable("id") Integer id) {
		try {
			lbs.vote(id);
		} catch (ServiceException e) {
			return e.getMessage();
		}
		return "ok";
	}

	@RequestMapping("getTopTen")
	public List<UserWrapper> getTopTen() {
		List<UserWrapper> objs = lbs.getTopTen();
		return objs;
	}

	@RequestMapping("getUserCount")
	public long getUserCount() {
		return lbs.getUserCount();
	}

	@RequestMapping("getVoteCount")
	public long getVoteCount() {
		return lbs.getVoteCount();
	}

	@RequestMapping(value = "getVote/{id}", method = RequestMethod.GET)
	public String getUserVote(@PathVariable("id") Integer id) {
		try {
			return String.valueOf(lbs.getVoteByUser(id));
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}
}
