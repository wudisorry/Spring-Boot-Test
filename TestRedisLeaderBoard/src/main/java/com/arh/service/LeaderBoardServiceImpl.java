package com.arh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import com.arh.applicationrunner.CreateUserApplicationRunner;
import com.arh.exception.ServiceException;
import com.arh.pojo.User;
import com.arh.pojo.UserWrapper;

@Service
public class LeaderBoardServiceImpl implements ILeaderBoardService {

	@Autowired
	private RedisTemplate<String, Object> rt;

	private static final String KEY = "LeaderBoard";

	public void vote(Integer id) throws ServiceException {
		Object obj = rt.opsForHash().get(CreateUserApplicationRunner.USERKEY, id);
		if (obj == null) {
			throw new ServiceException("User's id does not exist");
		}
		ZSetOperations<String, Object> op = rt.opsForZSet();
		op.incrementScore(KEY, id, 1);
	}

	public List<UserWrapper> getTopTen() {
		List<UserWrapper> uws = new ArrayList<UserWrapper>();
		ZSetOperations<String, Object> op = rt.opsForZSet();
		HashOperations<String, Object, Object> ho = rt.opsForHash();
		Set<ZSetOperations.TypedTuple<Object>> objs = op.reverseRangeWithScores(KEY, 0, 9);
		for (TypedTuple<Object> typedTuple : objs) {
			UserWrapper uw = new UserWrapper();
			uw.setUser((User) ho.get(CreateUserApplicationRunner.USERKEY, typedTuple.getValue()));
			uw.setScore(typedTuple.getScore());
			uws.add(uw);
		}
		return uws;
	}

	public long getUserCount() {
		ZSetOperations<String, Object> op = rt.opsForZSet();
		Long count = op.zCard(KEY);
		return count;
	}

	public long getVoteCount() {
		long voteCount = 0;
		ZSetOperations<String, Object> op = rt.opsForZSet();
		Long count = op.zCard(KEY);
		Set<ZSetOperations.TypedTuple<Object>> objs = op.rangeWithScores(KEY, 0, count - 1);
		for (TypedTuple<Object> typedTuple : objs) {
			voteCount += typedTuple.getScore();
		}
		return voteCount;
	}

	public long getVoteByUser(Integer id) throws ServiceException {
		Object obj = rt.opsForHash().get(CreateUserApplicationRunner.USERKEY, id);
		if (obj == null) {
			throw new ServiceException("User's id does not exist");
		}
		ZSetOperations<String, Object> op = rt.opsForZSet();
		double score;
		try {
			score = op.score(KEY, id);
			long result = (long) score;
			return result;
		} catch (Exception e) {
			return 0;
		}

	}
}
