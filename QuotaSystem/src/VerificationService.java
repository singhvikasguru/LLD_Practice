import java.util.HashMap;
import java.util.Map;

public class VerificationService {
    Map<String, Integer> counter;
    IdentitySystem identitySystemObj;

    public VerificationService(IdentitySystem identitySystemObj) {
        this.counter = new HashMap<>();
        this.identitySystemObj= identitySystemObj;
    }

    public boolean verify(String userId, String password)
    {
        Map<String, String> mapping=identitySystemObj.getUsersCredentialMap();
        if(mapping.containsKey(userId))
        {
            if(mapping.get(userId).equals(password))
            {
                //long timeInMillis=System.currentTimeMillis();
                if( counter.get(userId)<identitySystemObj.getUser(userId).getUsageType().getLimit())
                {
                    counter.put(userId, counter.get(userId)+1);
                    return true;
                }
                else
                {
                    System.out.println("Limit exceeded");
                    return false;
                }
            }
            else
            {
                System.out.println("Incorrect credentials");
                return false;
            }

        }
        else
            return false;
    }
    public void addUser(String name, String id, String password, Usage usage)
    {
        User u = new User(name, id, password, usage);
        identitySystemObj.addUser(u);

    }

    public Map<String, Integer> getCounter() {
        return counter;
    }

    public void setCounter(Map<String, Integer> counter) {
        this.counter = counter;
    }

    public IdentitySystem getIdentitySystemObj() {
        return identitySystemObj;
    }

    public void setIdentitySystemObj(IdentitySystem identitySystemObj) {
        this.identitySystemObj = identitySystemObj;
    }
}
