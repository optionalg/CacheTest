package com.cloudera.CachingTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NoSuchAlgorithmException, IOException
    {
        CacheService storage = new MemCache();
        CachedObject ricky = new Person("Ricky", "Saltzer", 24, "rickysaltzer@gmail.com");
        ricky.setPassword("abc123");
        storage.put(ricky);
        System.out.println("Record Saved => " + ricky.getKey());
        CachedObject newRicky = storage.get("rickysaltzer@gmail.com");
        System.out.println("I got " + newRicky.getFirstName() + " Back!");
        if (newRicky.checkPassword("abc123")) {
        	System.out.println("Auth Good");
        }
        else {
        	System.out.println("Auth Bad");
        }
    }
}