public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlacklistService blacklistService = new BlacklistService();
        AdminController adminController = new AdminController(blacklistService);
        RequestHandler requestHandler = new RequestHandler(blacklistService);

        Thread adminThread= new Thread(()-> adminController.uploadBlacklistFile("src/blacklist.txt"));
        Thread userThread1= new Thread(()-> requestHandler.handleRequest("http://example.com"));
        Thread userThread2= new Thread(()-> requestHandler.handleRequest("http://example.com/bad"));
        Thread userThread3= new Thread(()-> requestHandler.handleRequest("http://google.com"));
        adminThread.start();
        userThread1.start();
        userThread2.start();
        userThread3.start();

        adminThread.join();
        userThread1.join();
        userThread2.join();
        userThread3.join();
//        adminController.uploadBlacklistFile("blacklist.txt");
//
//        // Admin manually adds a URL to the blacklist
//        adminController.addUrlToBlacklist("http://example.com/bad");
//
//        // Simulate user requests
//        System.out.println(requestHandler.handleRequest("http://example.com"));
//        System.out.println(requestHandler.handleRequest("http://example.com/bad"));
//        System.out.println(requestHandler.handleRequest("http://google.com"));
    }
}
