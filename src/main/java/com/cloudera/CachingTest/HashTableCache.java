package com.cloudera.CachingTest;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Map;
import java.security.NoSuchAlgorithmException;

public class HashTableCache implements CacheService {
	Map<String,CachedObject> hmap;
	
	public HashTableCache() {
		hmap = new Hashtable<String,CachedObject>();
	}
	
	public HashTableCache(int initialCapacity) {
		hmap = new Hashtable<String,CachedObject>(initialCapacity);
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
