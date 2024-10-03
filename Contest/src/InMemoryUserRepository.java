import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> userMap;

    public InMemoryUserRepository() {
        this.userMap = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        userMap.put(user.getId(), user);
//        if (!userMap.containsKey(user.getId())) {
//            userMap.put(user.getId(), user);
//        } else {
//            System.out.println("User with id " + user.getId() + " already exists.");
//        }
    }

    @Override
    public User getUser(String userId) {
        return userMap.get(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }
}
