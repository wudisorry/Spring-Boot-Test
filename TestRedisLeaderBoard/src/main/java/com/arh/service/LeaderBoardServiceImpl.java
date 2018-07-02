package com.arh.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class LeaderBoardServiceImpl implements ILeaderBoardService {

	@Autowired
	private RedisTemplate<String, Object> rt;
	
	private static final String KEY = "LeaderBoard";
	
	@Override
	public void vote(String name) {
		ZSetOperations<String, Object> op = rt.opsForZSet();
		op.incrementScore(KEY, name, 1);
	}
	
	public Set<Object> getTopTen(){
		ZSetOperations<String, Object> op = rt.opsForZSet();
		Set<Object> objs = op.range(KEY, 0, 9);
		return objs;
	}

}
