package com.cloudera.CachingTest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface CacheService {
	public boolean put(CachedObject object);
	public CachedObject get(String email) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}