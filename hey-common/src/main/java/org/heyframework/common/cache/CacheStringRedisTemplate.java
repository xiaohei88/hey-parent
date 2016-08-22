package org.heyframework.common.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class CacheStringRedisTemplate extends StringRedisTemplate {

	/**
	 * Set {@code value} for {@code key}, only if {@code key} does not exist.
	 * <p>
	 * See http://redis.io/commands/setnx
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setNX(final String key, final String value) {
		boolean result = execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				return connection.setNX(serializer.serialize(key), serializer.serialize(value));
			}
		});
		return result;
	}

	/**
	 * Set {@code value} for {@code key}.
	 * <p>
	 * See http://redis.io/commands/set
	 * 
	 * @param key
	 * @param value
	 */
	public void set(final String key, final String value) {
		execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				return null;
			}
		});
	}

	/**
	 * Increment value of {@code key} by 1.
	 * <p>
	 * See http://redis.io/commands/incr
	 * 
	 * @param key
	 * @return
	 */
	public Long incr(final String key) {
		Long result = execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.incr(getRedisSerializer().serialize(key));
			}
		});
		return result;
	}

	/**
	 * Increment value of {@code key} by {@code value}.
	 * <p>
	 * See http://redis.io/commands/incrby
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long incrBy(final String key, final long value) {
		Long result = execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				return connection.incrBy(serializer.serialize(key), value);
			}
		});
		return result;
	}

	/**
	 * Decrement value of {@code key} by 1.
	 * <p>
	 * See http://redis.io/commands/decr
	 * 
	 * @param key
	 * @return
	 */
	public Long decr(final String key) {
		Long result = execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.decr(getRedisSerializer().serialize(key));
			}
		});
		return result;
	}

	/**
	 * Increment value of {@code key} by {@code value}.
	 * <p>
	 * See http://redis.io/commands/decrby
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long decrBy(final String key, final long value) {
		Long result = execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				return connection.decrBy(serializer.serialize(key), value);
			}
		});
		return result;
	}

	/**
	 * Set multiple hash fields to multiple values using data provided in
	 * {@code hashes}
	 * 
	 * @see http://redis.io/commands/hmset
	 * @param key
	 * @param hashes
	 */
	public void hMSet(final String key, final Map<String, String> map) {
		execute(new RedisCallback<Object>() {

			public Object doInRedis(RedisConnection connection) {
				RedisSerializer<String> serializer = getRedisSerializer();
				Map<byte[], byte[]> hashes = new HashMap<byte[], byte[]>(map.size());
				for (Entry<String, String> set : map.entrySet()) {
					hashes.put(serializer.serialize(set.getKey()), serializer.serialize(set.getValue()));
				}
				connection.hMSet(serializer.serialize(key), hashes);
				return null;
			}
		});
	}

	/**
	 * Set the {@code value} of a hash {@code field}.
	 * 
	 * @see http://redis.io/commands/hset
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Boolean hSet(final String key, final String field, final String value) {
		boolean result = execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				return connection.hSet(serializer.serialize(key), serializer.serialize(field), serializer.serialize(value));
			}
		});
		return result;
	}

	/**
	 * Get the value of {@code key}.
	 * <p>
	 * See http://redis.io/commands/get
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		String result = execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				if (connection.exists(serializer.serialize(key))) {
					byte[] value = connection.get(serializer.serialize(key));
					return serializer.deserialize(value);
				}
				return null;
			}
		});
		return result;
	}

	/**
	 * Get entire hash stored at {@code key}.
	 * 
	 * @see http://redis.io/commands/hgetall
	 * @param key
	 * @return
	 */
	public Map<String, String> hGetAll(final String key) {
		Map<String, String> result = execute(new RedisCallback<Map<String, String>>() {

			@Override
			public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				if (connection.exists(serializer.serialize(key))) {
					Map<byte[], byte[]> hashes = connection.hGetAll(serializer.serialize(key));
					Map<String, String> map = new HashMap<String, String>(hashes.size());
					for (Entry<byte[], byte[]> set : hashes.entrySet()) {
						map.put(serializer.deserialize(set.getKey()), serializer.deserialize(set.getValue()));
					}
					return map;
				}
				return null;
			}
		});
		return result;
	}

	/**
	 * Get value for given {@code field} from hash at {@code key}.
	 * 
	 * @see http://redis.io/commands/hget
	 * @param key
	 * @param field
	 * @return
	 */
	public String hGet(final String key, final String field) {
		String result = execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				if (connection.exists(serializer.serialize(key))) {
					return serializer.deserialize(connection.hGet(serializer.serialize(key), serializer.serialize(field)));
				}
				return null;
			}
		});
		return result;
	}

	/**
	 * Delete given {@code keys}.
	 * <p>
	 * See http://redis.io/commands/del
	 * 
	 * @param keys
	 * @return The number of keys that were removed.
	 */
	public void del(final String key) {
		execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.del(getRedisSerializer().serialize(key));
				return null;
			}
		});
	}

	/**
	 * Determine if given {@code key} exists.
	 * <p>
	 * See http://redis.io/commands/exists
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(final String key) {
		boolean result = execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				connection.exists(serializer.serialize(key));
				return true;
			}
		});
		return result;
	}

	private RedisSerializer<String> getRedisSerializer() {
		return getStringSerializer();
	}
}
