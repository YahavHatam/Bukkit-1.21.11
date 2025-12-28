package it.unimi.dsi.fastutil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * FastUtil implementation for Bukkit 1.21.11
 * Provides high-performance collections and utilities
 */
public class FastUtil {
    
    /**
     * Creates a fast object map
     * @param <K> Key type
     * @param <V> Value type
     * @return Fast object map
     */
    public static <K, V> Object2ObjectMap<K, V> createObjectMap() {
        return new Object2ObjectHashMap<>();
    }
    
    /**
     * Creates a fast object map with initial capacity
     * @param <K> Key type
     * @param <V> Value type
     * @param capacity Initial capacity
     * @return Fast object map
     */
    public static <K, V> Object2ObjectMap<K, V> createObjectMap(int capacity) {
        return new Object2ObjectHashMap<>(capacity);
    }
    
    /**
     * Creates a fast object set
     * @param <T> Element type
     * @return Fast object set
     */
    public static <T> ObjectSet<T> createObjectSet() {
        return new ObjectHashSet<>();
    }
    
    /**
     * Creates a fast object set with initial capacity
     * @param <T> Element type
     * @param capacity Initial capacity
     * @return Fast object set
     */
    public static <T> ObjectSet<T> createObjectSet(int capacity) {
        return new ObjectHashSet<>(capacity);
    }
    
    /**
     * Fast object map interface
     */
    public interface Object2ObjectMap<K, V> extends Map<K, V> {
        
        /**
         * Puts a key-value pair
         * @param key Key
         * @param value Value
         * @return Previous value
         */
        V put(K key, V value);
        
        /**
         * Gets a value by key
         * @param key Key
         * @return Value
         */
        V get(Object key);
        
        /**
         * Removes a key-value pair
         * @param key Key
         * @return Removed value
         */
        V remove(Object key);
        
        /**
         * Checks if the map contains a key
         * @param key Key
         * @return True if contains key
         */
        boolean containsKey(Object key);
        
        /**
         * Gets the size of the map
         * @return Map size
         */
        int size();
        
        /**
         * Checks if the map is empty
         * @return True if empty
         */
        boolean isEmpty();
    }
    
    /**
     * Fast object set interface
     */
    public interface ObjectSet<T> extends Collection<T> {
        
        /**
         * Adds an element
         * @param element Element to add
         * @return True if added
         */
        boolean add(T element);
        
        /**
         * Removes an element
         * @param element Element to remove
         * @return True if removed
         */
        boolean remove(Object element);
        
        /**
         * Checks if the set contains an element
         * @param element Element to check
         * @return True if contains element
         */
        boolean contains(Object element);
        
        /**
         * Gets the size of the set
         * @return Set size
         */
        int size();
        
        /**
         * Checks if the set is empty
         * @return True if empty
         */
        boolean isEmpty();
    }
    
    /**
     * Implementation of Object2ObjectMap
     */
    private static class Object2ObjectHashMap<K, V> extends HashMap<K, V> implements Object2ObjectMap<K, V> {
        
        public Object2ObjectHashMap() {
            super();
        }
        
        public Object2ObjectHashMap(int capacity) {
            super(capacity);
        }
    }
    
    /**
     * Implementation of ObjectSet
     */
    private static class ObjectHashSet<T> extends java.util.HashSet<T> implements ObjectSet<T> {
        
        public ObjectHashSet() {
            super();
        }
        
        public ObjectHashSet(int capacity) {
            super(capacity);
        }
    }
}