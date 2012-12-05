package com.cloudera.CachingTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testRedis() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	CacheService storage = new RedisCache();
        CachedObject testobj = new Person("Ricky", "Saltzer", 24, "rickysaltzer@gmail.com");
        storage.put(testobj);
        CachedObject newobj = storage.get("Ricky", "Saltzer");
        assertEquals(testobj.getKey(), newobj.getKey());
    }
    
    public void testMemached() throws NoSuchAlgorithmException, IOException {
    	CacheService storage = new MemCache();
        CachedObject testobj = new Person("Ricky", "Saltzer", 24, "rickysaltzer@gmail.com");
        storage.put(testobj);
        CachedObject newobj = storage.get("Ricky", "Saltzer");
        assertEquals(testobj.getKey(), newobj.getKey());
    }
}
