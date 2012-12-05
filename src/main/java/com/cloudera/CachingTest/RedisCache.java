package com.cloudera.CachingTest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.*;

public class RedisCache implements CacheService {
	private Jedis redis;
	
	public RedisCache() {
		redis = new Jedis("192.168.2.105");
	}

	public boolean put(CachedObject object) {
		/*
		 * Attempt to connect to Redis database and store cached record
		 */
		try {
			redis.set(object.getKey() + "-f", object.getFirstName());
			redis.set(object.getKey() + "-l", object.getLastName());
			redis.set(object.getKey() + "-a", Integer.toString(object.getAge()));
			redis.set(object.getKey() + "-e", object.getEmail());
		} catch (JedisException e) {
			// This bad
			return false;
		}
		return true;
	}

	public CachedObject get(String firstName, String lastName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		CachedObject tmpObject = new Person(firstName, lastName, 0, null);
		try {
			String f = redis.get(tmpObject.getKey() + "-f");
			String l = redis.get(tmpObject.getKey() + "-l");
			int a = Integer.parseInt(redis.get(tmpObject.getKey() + "-a"));
			String e = redis.get(tmpObject.getKey() + "-e");
			return new Person(f, l, a, e);
		} catch (JedisException e) {
			return null;
		}
	}
	
}
