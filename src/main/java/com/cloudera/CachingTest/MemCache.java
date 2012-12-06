package com.cloudera.CachingTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import net.spy.memcached.MemcachedClient;

public class MemCache implements CacheService {
	private MemcachedClient memcached;
	
	public MemCache() throws IOException {
		try {
			this.memcached = new MemcachedClient(new InetSocketAddress("192.168.2.105", 11211));
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	public boolean put(CachedObject object) {
		memcached.set(object.getKey() + "-f", 3600, object.getFirstName());
		memcached.set(object.getKey() + "-l", 3600, object.getLastName());
		memcached.set(object.getKey() + "-a", 3600, object.getAge());
		memcached.set(object.getKey() + "-e", 3600, object.getEmail());
		memcached.set(object.getKey() + "-p", 3600, object.getPassword());
		return true;
	}

	public CachedObject get(String email)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String key = Person.getKey(email);
		String f = memcached.get(key + "-f").toString();
		String l = memcached.get(key + "-l").toString();
		int a = Integer.parseInt(memcached.get(key + "-a").toString());
		String e = memcached.get(key + "-e").toString();
		String p = memcached.get(key + "-p").toString();
		CachedObject obj = new Person(f, l, a, e);
		obj.setHashedPassword(p);
		return obj;
	}

}
