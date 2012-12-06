package com.cloudera.CachingTest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.security.NoSuchAlgorithmException;

public class HashCache implements CacheService {
	Map<String,CachedObject> hmap;
	
	public HashCache() {
		hmap = new HashMap<String,CachedObject>();
	}
	
	public HashCache(int initialCapacity) {
		hmap = new HashMap<String,CachedObject>(initialCapacity);
	}

	public boolean put(CachedObject object) {
		hmap.put(object.getKey(), object);
		return true;
	}

	public CachedObject get(String email) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		return hmap.get(Person.getKey(email));
	}
}
