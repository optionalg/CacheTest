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
	
	private final int StressAmount = 1000;
	private final String password = "unittest";
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
        testobj.setPassword(password);
        storage.put(testobj);
        CachedObject newobj = storage.get("rickysaltzer@gmail.com");
        assertEquals(testobj.getKey(), newobj.getKey());
        assertTrue(testobj.checkPassword(password));
    }
    
    public void testMemached() throws NoSuchAlgorithmException, IOException {
    	CacheService storage = new MemCache();
        CachedObject testobj = new Person("Ricky", "Saltzer", 24, "rickysaltzer@gmail.com");
        testobj.setPassword(password);
        storage.put(testobj);
        CachedObject newobj = storage.get("rickysaltzer@gmail.com");
        assertEquals(testobj.getKey(), newobj.getKey());
        assertTrue(testobj.checkPassword(password));
    }
    
    private void stressTest(CacheService storage, int amount) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	for (int i = 0; i <= amount; i++) {
    		Person tester = new Person("john", "doe", 24, "johnny" + Integer.toString(i) + "@gmail.com");
    		tester.setPassword(password);
    		assertTrue(storage.put(tester));
    	}
    	
    	for (int i = 0; i <= amount; i++) {
    		CachedObject person = storage.get("johnny" + Integer.toString(i) + "@gmail.com");
    		assertEquals(person.getEmail(), "johnny" + Integer.toString(i) + "@gmail.com");
    		assertTrue(person.checkPassword(password));
    	}
    }
    
    public void testStressMemached() throws NoSuchAlgorithmException, IOException {
    	CacheService memcached = new MemCache();
    	stressTest(memcached, StressAmount);
    }
    
    public void testStressRedis() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	CacheService redis = new RedisCache();
    	stressTest(redis, StressAmount);
    }
    
    public void testStressHashMap() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	CacheService hashmap = new HashCache(10000);
    	stressTest(hashmap, StressAmount);
    }
    
    public void testStressHTable() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	CacheService hashmap = new HashTableCache(10000);
    	stressTest(hashmap, StressAmount);
    }
}
