package com.cloudera.CachingTest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface CachedObject {
	public String getFirstName();
	public String getLastName();
	public String getEmail();
	public void setPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
	public void setHashedPassword(String password);
	public String getPassword();
	public boolean checkPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
	public int getAge();
	public String getKey();
}
