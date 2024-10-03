import java.util.List;

public interface UserRepository {
    void addUser(User user);
    User getUser(String userId);
    List<User> getAllUsers();
}
