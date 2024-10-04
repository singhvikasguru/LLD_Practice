import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdentitySystem {
    Map<String, String > usersCredentialMap;
    List<User> userList;

    public IdentitySystem() {
        this.usersCredentialMap = new HashMap<>();
        userList= new ArrayList<>();
    }



    public User getUser(String userId)
    {
        for(User u:userList)
            if(u.getId().equals(userId))
                return u;
        System.out.println("User doesn't exist");
        return null;
    }

    public void addUser(User u)
    {
        userList.add(u);
        addUser(u.getId(), u.getPassword());
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Map<String, String> getUsersCredentialMap() {
        return usersCredentialMap;
    }

    public void setUsersCredentialMap(Map<String, String> usersCredentialMap) {
        this.usersCredentialMap = usersCredentialMap;
    }

    public void addUser(String userId, String password)
    {
        usersCredentialMap.put(userId, password);
    }
    public  void updatePassword(String userId, String password)
    {
        usersCredentialMap.put(userId, password);
    }
}
