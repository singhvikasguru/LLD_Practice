import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BlacklistService {
    private Set<String> blacklist = new HashSet<>(); // In-memory blacklist storage
    private ReadWriteLock lock = new ReentrantReadWriteLock(); // Lock to handle concurrency
//    private ReadWriteLock RWLOCK= new ReentrantReadWriteLock();
    // Load blacklist from a .txt file (admin operation)
    public void loadBlacklist(String filePath) throws IOException {
        lock.writeLock().lock(); // Acquire the write lock for updating the blacklist
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Blacklist file does not exist at path: " + file.getAbsolutePath());
            lock.writeLock().unlock(); // Ensure lock is released
            return;
        } else {
            System.out.println("Loading blacklist from file: " + file.getAbsolutePath());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            blacklist.clear(); // Clear the existing blacklist
            String url;
            while ((url = br.readLine()) != null) {
                blacklist.add(url.trim());
            }
            System.out.println("Blacklist loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock(); // Release the write lock
        }
    }

    // Check if a URL is blacklisted (user request)
    public boolean isBlacklisted(String url) {
        lock.readLock().lock(); // Acquire the read lock
        try {
            return blacklist.contains(url);
        } finally {
            lock.readLock().unlock(); // Release the read lock
        }
    }

    // Add a URL to the blacklist manually (admin operation)
    public void addToBlacklist(String url) {
        lock.writeLock().lock(); // Acquire the write lock for modifying the blacklist
        try {
            blacklist.add(url.trim());
            System.out.println("URL added to blacklist: " + url);
        } finally {
            lock.writeLock().unlock(); // Release the write lock
        }
    }

    // Remove a URL from the blacklist manually (admin operation)
    public void removeFromBlacklist(String url) {
        lock.writeLock().lock(); // Acquire the write lock
        try {
            blacklist.remove(url.trim());
            System.out.println("URL removed from blacklist: " + url);
        } finally {
            lock.writeLock().unlock(); // Release the write lock
        }
    }
}
