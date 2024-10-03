import java.util.*;

// EvictionStrategy interface for implementing different eviction strategies
interface EvictionStrategy {
    String evict(LinkedHashMap<String, String> cache, Map<String, Integer> freq);
}

// LFU (Least Frequently Used) eviction strategy implementation
class LFUEvictionStrategy implements EvictionStrategy {
    @Override
    public String evict(LinkedHashMap<String, String> cache, Map<String, Integer> freq) {
        // Find the least frequently used key
        String lfuKey = Collections.min(freq.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        cache.remove(lfuKey);
        freq.remove(lfuKey);
        return lfuKey;
    }
}

// CacheLevel class that represents a single cache level with an eviction strategy
class CacheLevel {
    int capacity;
    private LinkedHashMap<String, String> cache;
    private Map<String, Integer> freq;
    EvictionStrategy evictionStrategy;

    public CacheLevel(int capacity, EvictionStrategy evictionStrategy) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true); // Maintain access order
        this.freq = new HashMap<>();
        this.evictionStrategy = evictionStrategy;
    }

    // Read method to get value by key and update frequency
    public String read(String key) {
        if (cache.containsKey(key)) {
            freq.put(key, freq.getOrDefault(key, 0) + 1); // Update frequency
            return cache.get(key);
        }
        return null;
    }

    // Write method to store key-value pair, updating frequency or evicting if full
    public void write(String key, String value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            freq.put(key, freq.getOrDefault(key, 0) + 1); // Update frequency
        } else {
            if (cache.size() >= capacity) {
                evict();
            }
            cache.put(key, value);
            freq.put(key, 1); // Set initial frequency
        }
    }

    // Delete a key from the cache
    public void delete(String key) {
        cache.remove(key);
        freq.remove(key);
    }

    // Evict the least frequently used key using the strategy
    private void evict() {
        evictionStrategy.evict(cache, freq); // Use the strategy to evict the least frequently used key
    }

    // Check if the cache is full
    public boolean isFull() {
        return cache.size() >= capacity;
    }

    // Evict and return the evicted key for cascading evictions
    public String evictAndReturnKey() {
        return evictionStrategy.evict(cache, freq); // Evict and return the evicted key
    }
}

// MultiLevelCache class that manages multiple cache levels
class MultiLevelCache {
    private int maxLevels;
    private List<CacheLevel> levels;

    public MultiLevelCache(int maxLevels, int[] capacities, EvictionStrategy evictionStrategy) {
        this.maxLevels = maxLevels;
        this.levels = new ArrayList<>();
        for (int capacity : capacities) {
            levels.add(new CacheLevel(capacity, evictionStrategy)); // Add levels with the same eviction strategy
        }
    }

    // Read operation, starting from L1 and moving to lower levels if not found
    public String read(String key) {
        for (CacheLevel level : levels) {
            String value = level.read(key);
            if (value != null) {
                // If found, move the value to L1
                write(key, value);
                return value;
            }
        }
        return null; // Key not found in any level
    }

    // Write operation, writing to L1 and cascading evictions if needed
    public void write(String key, String value) {
        boolean evicted = cascadeWrite(0, key, value);
        if (evicted && levels.size() < maxLevels) {
            addNewLevel();
        }
    }

    // Cascade evictions down the levels if necessary
    private boolean cascadeWrite(int levelIndex, String key, String value) {
        if (levelIndex >= levels.size()) {
            return true;
        }
        CacheLevel currentLevel = levels.get(levelIndex);
        if (currentLevel.isFull()) {
            String evictedKey = currentLevel.evictAndReturnKey();
            String evictedValue = currentLevel.read(evictedKey);
            boolean evictedFromNextLevel = cascadeWrite(levelIndex + 1, evictedKey, evictedValue);
            currentLevel.write(key, value);
            return evictedFromNextLevel;
        } else {
            currentLevel.write(key, value);
            return false;
        }
    }

    // Add a new level if eviction reaches the last level and max levels are not reached
    private void addNewLevel() {
        if (levels.size() < maxLevels) {
            CacheLevel lastLevel = levels.get(levels.size() - 1);
            levels.add(new CacheLevel(lastLevel.isFull() ? lastLevel.capacity : 2, lastLevel.evictionStrategy));
        }
    }

    // Delete a key from all levels
    public void delete(String key) {
        for (CacheLevel level : levels) {
            level.delete(key);
        }
    }
}

// Test class for the multilevel cache
public class Main {
    public static void main(String[] args) {
        int maxLevels = 3;
        int[] capacities = {2, 2, 2}; // Each level has a capacity of 2

        // Create a multilevel cache with LFU eviction strategy
        MultiLevelCache cache = new MultiLevelCache(maxLevels, capacities, new LFUEvictionStrategy());

        // Writing data to the cache
        cache.write("a", "apple");
        cache.write("b", "banana");
        cache.write("c", "cherry"); // This will cause eviction from L1 to L2

        // Reading data from the cache
        System.out.println("Read a: " + cache.read("a")); // Should return "apple"
        System.out.println("Read b: " + cache.read("b")); // Should return "banana"
        System.out.println("Read d: " + cache.read("d")); // Should return null (not present)

        // Deleting a key from the cache
        cache.delete("a");
        System.out.println("Read a after delete: " + cache.read("a")); // Should return null
    }
}
