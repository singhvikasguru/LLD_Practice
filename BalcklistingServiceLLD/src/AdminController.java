import java.io.IOException;
import java.util.Scanner;

public class AdminController {
    private BlacklistService blacklistService;

    public AdminController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    // Admin can upload a blacklist file to update the in-memory blacklist
    public void uploadBlacklistFile(String filePath) {
        try {
            blacklistService.loadBlacklist(filePath);
            System.out.println("Blacklist updated successfully from file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error loading blacklist file: " + e.getMessage());
        }
    }

    // Admin can manually add a URL to the blacklist
    public void addUrlToBlacklist(String url) {
        blacklistService.addToBlacklist(url);
        System.out.println("URL added to blacklist: " + url);
    }
}
