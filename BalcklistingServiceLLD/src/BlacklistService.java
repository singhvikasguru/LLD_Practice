import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BlacklistService {
    private Set<String> blacklist = new HashSet<>(); // In-memory store for blacklisted URLs

    // Load blacklist from a .txt file
    public void loadBlacklist(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String url;
            while ((url = br.readLine()) != null) {
                blacklist.add(url.trim());
            }
        }
    }

    // Check if the URL is blacklisted
    public boolean isBlacklisted(String url) {
        return blacklist.contains(url);
    }

    // Add a URL to the blacklist manually (for admin updates)
    public void addToBlacklist(String url) {
        blacklist.add(url);
    }

    // Remove a URL from the blacklist (optional)
    public void removeFromBlacklist(String url) {
        blacklist.remove(url);
    }
}
