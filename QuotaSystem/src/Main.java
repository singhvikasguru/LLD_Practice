
// User request->verify-> valid or  not-> track of no request-> if threshold is breached-> send a message
// user can decide free and premium model
/*
    Components
        1. User-> account type....
        2. Verification service
        3. Usage
            Free
            Premium
         4. Indentity storage system
*
* */

public class Main {
    public static void main(String[] args) {
        Usage free= new FreeUsage();
        Usage premium= new PremiumUsage();
        free.setLimit(5);
        premium.setLimit(500);
        IdentitySystem identitySystemObj= new IdentitySystem();
        User u1= new User("Vikas", "1", "abc", free);
        identitySystemObj.addUser(u1);

        VerificationService verificationServiceObj= new VerificationService(identitySystemObj);
        verificationServiceObj.getCounter().put(u1.getId(), 0);
        System.out.println("Is access allowed: "+verificationServiceObj.verify(u1.getId(), u1.getPassword()));
        System.out.println("Is access allowed: "+verificationServiceObj.verify(u1.getId(), u1.getPassword()));
        System.out.println("Is access allowed: "+verificationServiceObj.verify(u1.getId(), u1.getPassword()));
        System.out.println("Is access allowed: "+verificationServiceObj.verify(u1.getId(), u1.getPassword()));
        System.out.println("Is access allowed: "+verificationServiceObj.verify(u1.getId(), u1.getPassword()));
        System.out.println("Is access allowed: "+verificationServiceObj.verify(u1.getId(), u1.getPassword()));




    }
}