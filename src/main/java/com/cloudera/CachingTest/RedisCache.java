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
			redis.set(object.getKey() + "-p", object.getPassword());
		} catch (JedisException e) {
			// This bad
			return false;
		}
		return true;
	}

	public CachedObject get(String email) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String key = Person.getKey(email);
		try {
			String f = redis.get(key + "-f");
			String l = redis.get(key + "-l");
			int a = Integer.parseInt(redis.get(key + "-a"));
			String e = redis.get(key + "-e");
			String p = redis.get(key + "-p");
			CachedObject obj = new Person(f, l, a, e);
			obj.setHashedPassword(p);
			return obj;
		} catch (JedisException e) {
			return null;
		}
	}
	
}