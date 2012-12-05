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
        storage.put(ricky);
        System.out.println("Record Saved => " + ricky.getKey());
        CachedObject newRicky = storage.get("Ricky", "Saltzer");
        System.out.println("I got " + newRicky.getFirstName() + " Back!");
    }
}
