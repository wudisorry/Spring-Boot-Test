package com.arh.service;

import java.util.Set;

public interface ILeaderBoardService {
	
	void vote(String name);
	
	public Set<Object> getTopTen();
	
}
