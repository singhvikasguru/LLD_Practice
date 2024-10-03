public class RequestHandler {
    private BlacklistService blacklistService;

    public RequestHandler(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    // Handle the request to access a URL
    public String handleRequest(String url) {
        if (blacklistService.isBlacklisted(url)) {
            return blockRequest(url);
        } else {
            return allowRequest(url);
        }
    }

    // If the URL is blacklisted, block it
    private String blockRequest(String url) {
        logRequest(url, true); // Log the blocked request
        return "403 Forbidden - URL is blacklisted";
    }

    // If the URL is not blacklisted, allow it
    private String allowRequest(String url) {
        logRequest(url, false); // Log the allowed request
        return "200 OK - URL is allowed";
    }

    // Log the request result
    private void logRequest(String url, boolean isBlocked) {
        String status = isBlocked ? "Blocked" : "Allowed";
        System.out.println("Request to URL: " + url + " - " + status);
    }
}
