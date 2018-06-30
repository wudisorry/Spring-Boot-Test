package com.arh.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

@Configuration
public class RedisConfig {

	@Bean(name = "redisStandaloneConfiguration")
	@ConfigurationProperties("redis")
	public RedisStandaloneConfiguration getRedisStandaloneConfiguration() {
		return new RedisStandaloneConfiguration();
	}

	@Bean(name = "genericObjectPoolConfig")
	@ConfigurationProperties("redis.pool")
	public GenericObjectPoolConfig getGenericObjectPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		return poolConfig;
	}

	@Bean(name = "lettuceClientConfiguration")
	public LettuceClientConfiguration getLettuceClientConfiguration(
			@Qualifier("genericObjectPoolConfig") GenericObjectPoolConfig poolConfig) {
		LettucePoolingClientConfiguration poolingClientConfig = LettucePoolingClientConfiguration.builder()
				.poolConfig(poolConfig).build();
		return poolingClientConfig;
	}

	@Bean(name = "redisConnectionFactory")
	public RedisConnectionFactory getRedisConnectionFactory(
			@Qualifier("redisStandaloneConfiguration") RedisStandaloneConfiguration config,
			@Qualifier("lettuceClientConfiguration") LettuceClientConfiguration poolingClientConfig) {
		LettuceConnectionFactory factory = new LettuceConnectionFactory(config, poolingClientConfig);
		factory.setShareNativeConnection(false);
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
