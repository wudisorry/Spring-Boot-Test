package com.arh.redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

	@Bean(name = "redisStandaloneConfiguration")
	@ConfigurationProperties("spring.redis")
	public RedisStandaloneConfiguration getRedisStandaloneConfiguration() {
		return new RedisStandaloneConfiguration();
	}

	@Bean(name = "redisConnectionFactory")
	public RedisConnectionFactory getRedisConnectionFactory(
			@Qualifier("redisStandaloneConfiguration") RedisStandaloneConfiguration config) {
		LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
		return factory;
	}

	// @Bean(name="redisTemplate")
	// public RedisTemplate<Object,Object>
	// getRedisTemplate(@Qualifier("redisConnectionFactory")RedisConnectionFactory
	// connectionFactory) {
	// RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
	// redisTemplate.setConnectionFactory(connectionFactory);
	// redisTemplate.afterPropertiesSet();
	// return redisTemplate;
	// }

	@Bean(name = "cacheManager")
	public CacheManager getCacheManager(@Qualifier("redisConnectionFactory") RedisConnectionFactory factory) {
		RedisCacheManagerBuilder builder = RedisCacheManagerBuilder.fromConnectionFactory(factory);
		return builder.build();
	}
}
