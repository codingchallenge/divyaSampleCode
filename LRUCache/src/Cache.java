import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/***
 * Cache implementation with LRU (Least Recently Used) eviction policy
 * @author mupdivya
 *
 * @param <V>
 */
public class Cache<V> {

	//Node definition used for doubly linked list
	private class Node<V> {
		V value;
		String key;
		Node<V> previous;
		Node<V> next;
		
		public Node(String key, V val) {
			value = val;
			this.key = key;
		}
	}
	
	private final HashMap<String,Node> hash;
	private final int size;
	private Node<V> queueFront;
	private Node<V> queueEnd;
	//Singleton instance is created, volatile is used to avoid issues with partial initialization
	private static volatile Cache cache;

	//Double Linked List implementation
	private void remove(Node<V> node) {
		if(node == null)
			return;
		
		Node<V> previous = node.previous;
		Node<V> next = node.next;
		if(previous != null)
			previous.next = next;
		else
			queueFront = next;
		
		if(next != null)
			next.previous = previous;
		else
			queueEnd = previous;
		
	}
	
	private void add(Node<V> node) {
		if(node == null)
			return;
		
		if(queueEnd != null) {
			queueEnd.next = node;
			node.previous = queueEnd;
			node.next = null;
			queueEnd = node;
		} else {
			queueEnd = node;
			queueEnd.next = null;
			queueEnd.previous = null;
			queueFront = node;
		}	
	}
	
	//Cache Implementation 
	private Cache(int size) {
		this.size = size;
		hash = new HashMap<String,Node>(size);
		queueFront = null;
		queueEnd = null;
	}
	
	//Creating singleton instance of cache using double checked locking
	public static Cache getInstance(int size) {
		
		if(cache == null) {
			synchronized(Cache.class) {
				if(cache == null) {
					cache = new Cache(size);
					return cache;
				} 
			}
		}
		return cache;
	}
	
	//If key is already contained, the LRU for the key is refreshed
	//If key is not present it is inserted into the queue and hash
	//If hash size limit is reached, least recently used value is evicted
	public void put(String key, V value ) {
		if(hash.containsKey(key)) {
			Node n = hash.get(key);
			remove(n);
			n.value = value;
			add(n);
		} else {
			if(hash.size() >= size) {
				Node<V> delNode = queueFront;
				remove(queueFront);
				hash.remove(delNode.key);
			}
			Node n = new Node(key,value);
			add(n);
			hash.put(key, n);
		}
	}
	
	//The value is returned from the hash. The LRU for the key is refreshed
	public V get(String key) {
		Node<V> n = hash.get(key);
		if(n != null) {
			remove(n);
			add(n);
			return n.value;
		}
		return null;
	}
	
}
