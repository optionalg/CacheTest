package com.cloudera.CachingTest;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Person implements CachedObject {
	private final String firstName;
	private final String lastName;
	private String password;
	private final String email;
	private final int age;
	private final byte[] hash;
	
	public Person(String firstName, String lastName, int age, String email) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		
		// Generate hash used for lookups
		MessageDigest digester = MessageDigest.getInstance("MD5");
		this.hash = digester.digest((email).getBytes("UTF-8"));
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}
	
	public void setPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest digester = MessageDigest.getInstance("MD5");
		this.password = new String(Hex.encodeHex(digester.digest((password).getBytes("UTF-8"))));
	}
	
	public void setHashedPassword(String hash) {
		this.password = hash;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean checkPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest digester = MessageDigest.getInstance("MD5");
		String challenger = new String(Hex.encodeHex(digester.digest((password).getBytes("UTF-8"))));
		return this.password.equals(challenger);
	}

	public String getKey() {
		return new String(Hex.encodeHex(hash));
	}
	
	public static String getKey(String email) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return new Person(null, null, 0, email).getKey();
	}

}
