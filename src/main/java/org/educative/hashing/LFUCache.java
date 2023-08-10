package org.educative.hashing;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LFUCache {
    // key: original key, value: frequency and original value.
    Map<Integer, int[]> cache;
    // key: frequency, value: All keys that have the same frequency.
    Map<Integer, LinkedHashSet<Integer>> frequencies;

    int minFreq;

    int capacity;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.frequencies = new HashMap<>();
        this.minFreq = 0;
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(3,1);
        cache.put(3,1);
        cache.put(3,1);
        cache.put(3,1);
        cache.put(2,1);
        cache.put(2,1);
        cache.put(2,1);
        cache.put(1,1);
    }

    public int get(int key) {
        if(!cache.containsKey(key)) return -1;

        int[] frequencyAndValues = cache.get(key);
        int frequency = frequencyAndValues[0];

        updateFrequency(key, frequency);

        int value = frequencyAndValues[1];
        insert(key, frequency + 1, value);
        return value;
    }

    private void updateFrequency(int key, int frequency) {
        Set<Integer> keys = frequencies.get(frequency);
        keys.remove(key);
        if(keys.isEmpty()){
            frequencies.remove(frequency);

            // If minFreq is pointing to this frequency, we already removed that set
            if(minFreq == frequency)
                minFreq++;
        }
    }

    public void put(int key, int value) {
        // 1. If the key already exists
        if(cache.containsKey(key)) {
            int[] frequencyAndValues = cache.get(key);
            cache.put(key, new int[]{frequencyAndValues[0], value});
            get(key); // as updating the frequency and moving it to the correct set is handled by get method
            return;
        }
        // 2. Cache size reached its upper limit
        if(cache.size() == capacity){
            LinkedHashSet<Integer> keys = frequencies.get(minFreq);
            Integer keyToRemove = keys.iterator().next();
            keys.remove(keyToRemove);
            if(keys.isEmpty()){
                frequencies.remove(minFreq);
            }

            cache.remove(keyToRemove);
        }
        minFreq = 1;
        insert(key, 1, value);
    }

    /**
     * Insert an entry for the given key in a cache.
     * Also, put it in the right LinkedHashSet for similar frequencies
     * @param key
     * @param frequency
     * @param value
     */
    private void insert(int key, int frequency, int value){
        cache.put(key, new int[]{frequency, value});
        frequencies.putIfAbsent(frequency, new LinkedHashSet<>());
        frequencies.get(frequency).add(key);
    }
}
