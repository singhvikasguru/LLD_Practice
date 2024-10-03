public class Main {
    public static void main(String[] args) {
        BlacklistService blacklistService = new BlacklistService();
        AdminController adminController = new AdminController(blacklistService);
        RequestHandler requestHandler = new RequestHandler(blacklistService);

        // Admin uploads a blacklist file
        adminController.uploadBlacklistFile("blacklist.txt");

        // Admin manually adds a URL to the blacklist
        adminController.addUrlToBlacklist("http://example.com/bad");

        // Simulate user requests
        System.out.println(requestHandler.handleRequest("http://example.com"));
        System.out.println(requestHandler.handleRequest("http://example.com/bad"));
        System.out.println(requestHandler.handleRequest("http://google.com"));
    }
}
