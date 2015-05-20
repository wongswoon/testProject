package Proxy;

/**
 * Created by Lenovo on 15-4-27.
 */
public class UserManagerImpl implements UserManager {
    private String userId = "007";

    @Override
    public String getUserId(String userId) {
        return userId;
    }
}
