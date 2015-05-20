package Proxy;

/**
 * Created by Lenovo on 15-4-27.
 */
public class Client {
    public static void main(String[] args) {

        BusinessHandler businessHandler = new BusinessHandler();
        UserManager userManager = (UserManager)businessHandler.newProxyInstance(new UserManagerImpl());


        String name = userManager.getUserId("xx");
        System.out.println("Client.main() --- " + name);
    }
}
