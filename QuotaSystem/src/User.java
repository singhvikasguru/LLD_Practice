public class User {
    String name;
    String id;
    String password;

    Usage usageType;

    public User(String name, String id, String password, Usage usage) {
        this.name=name;
        this.id=id;
        this.password=password;
        this.usageType=usage;
    }

    public Usage getUsageType() {
        return usageType;
    }

    public void setUsageType(Usage usageType) {
        this.usageType = usageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
