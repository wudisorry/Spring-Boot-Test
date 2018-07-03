package com.arh.applicationrunner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.arh.pojo.User;

@Component
public class CreateUserApplicationRunner implements ApplicationRunner {

	@Autowired
	private RedisTemplate<String, Object> rt;

	public static final String USERKEY = "user";

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (rt.opsForHash().size(USERKEY) > 0) {
			return;
		}
		List<User> lists = new ArrayList<>();
		char c = 'a';
		for (int i = 1; i < 16; i++) {
			String name = String.valueOf(c);
			String info = name + i;
			User user = new User(i, name, info);
			c = (char) (c + 1);
			lists.add(user);
		}

		HashOperations<String, Object, Object> ho = rt.opsForHash();
		for (User user : lists) {
			ho.put(USERKEY, user.getId(), user);
		}
	}

}
