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
		return true;
	}

	public CachedObject get(String firstName, String lastName)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		CachedObject tmpObject = new Person(firstName, lastName, 0, null);
		String f = memcached.get(tmpObject.getKey() + "-f").toString();
		String l = memcached.get(tmpObject.getKey() + "-l").toString();
		int a = Integer.parseInt(memcached.get(tmpObject.getKey() + "-a").toString());
		String e = memcached.get(tmpObject.getKey() + "-e").toString();
		return new Person(f, l, a, e);
	}

}
