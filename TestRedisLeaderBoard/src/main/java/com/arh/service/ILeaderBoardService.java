package com.arh.service;

import java.util.List;

import com.arh.exception.ServiceException;
import com.arh.pojo.UserWrapper;

public interface ILeaderBoardService {

	void vote(Integer id) throws ServiceException;

	List<UserWrapper> getTopTen();

	long getUserCount();

	long getVoteCount();

	long getVoteByUser(Integer id) throws ServiceException;

}
