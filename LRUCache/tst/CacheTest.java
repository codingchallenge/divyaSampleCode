import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;


public class CacheTest {

	/***
	 * Test to get the value from cache
	 */
	@Test
	public void testCacheGet() {
		Cache<Integer> cache = Cache.getInstance(5);
		cache.put("hello", 1);
		cache.put("he", 6);
		int val = cache.get("he");
		assertEquals(6,val);
		
	}

	/***
	 * Test to verify the older values are evicted when the size limit is reached
	 */
	@Test
	public void testCacheEvictionPolicy() {
		Cache<Integer> cache = Cache.getInstance(5);
		cache.put("hello", 1);
		cache.put("h", 2);
		cache.put("hel", 3);
		cache.put("hel2", 4);
		cache.put("hell", 5);
		cache.put("he", 6);
		Integer val2 = cache.get("hello");
		assertNull(val2);
	}
	
	/***
	 * Test to verify least recently used key is refreshed when a get call is performed
	 */
	@Test
	public void testCacheEvictionPolicyWithReuse() {
		Cache<Integer> cache = Cache.getInstance(5);
		cache.put("hello", 1);
		cache.put("h", 2);
		cache.put("hel", 3);
		cache.put("hel2", 4);
		cache.get("hello");
		cache.put("hell", 5);
		cache.put("he", 6);
		int val1 = cache.get("hello");
		Integer val2 = cache.get("h");
		assertNull(val2);
		assertEquals(1, val1);
		
	}
	
	/***
	 * Test to verify least recently used key is refreshed when a get call is performed
	 */
	@Test
	public void testCacheEvictionPolicyWithSet() {
		Cache<Integer> cache = Cache.getInstance(5);
		cache.put("hello", 1);
		cache.put("h", 2);
		cache.put("hel", 3);
		cache.put("hel2", 4);
		cache.put("hello",7);
		cache.put("hell", 5);
		cache.put("he", 6);
		int val1 = cache.get("hello");
		Integer val2 = cache.get("h");
		assertNull(val2);
		assertEquals(7, val1);
		
	}
	
	/***
	 * Test to verify the Set operation of the Cache
	 */
	@Test
	public void testCacheSet() {
		Cache<Integer> cache = Cache.getInstance(5);
		cache.put("hello", 1);
		int val = cache.get("hello");
		assertEquals(1, val);
	}
	
	/***
	 * Test to verify get operation on non existing value
	 */
	@Test
	public void testCacheNonExistingKey() {
		Cache<Integer> cache = Cache.getInstance(5);
		
		Integer val = cache.get("h");
		assertNull(val);
		
	}
	
	/***
	 * Test to change the value of existing key
	 */
	@Test
	public void testCacheSetChangeValue() {
		Cache<Integer> cache = Cache.getInstance(5);
		cache.put("hello", 6);
		cache.put("hello", 5);
		cache.put("hello", 1);
		
		int val = cache.get("hello");
		assertEquals(1, val);
	}
	
}
