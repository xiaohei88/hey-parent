package org.heyframework.provider;

import org.heyframework.common.cache.CacheStringRedisTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class RedisConfiguration {

	// 可以自定义redis连接工厂，也可以使用默认spring加载的
	// @Bean
	// public RedisConnectionFactory redisConnectionFactory() {
	// return new JedisConnectionFactory();
	// }

	@Bean
	public CacheStringRedisTemplate redisTemplate(RedisConnectionFactory factory) {
		CacheStringRedisTemplate redisTemplate = new CacheStringRedisTemplate();
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}
}
