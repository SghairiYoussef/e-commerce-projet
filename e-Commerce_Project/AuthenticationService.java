import java.util.HashMap;
import java.util.Map;

public class AuthenticationService 
{
    private Map<String, User> users;

    public AuthenticationService() 
    {
        users = new HashMap<>();
    }

    public void addUser(User user) 
    {
        if (users.containsKey(user.getUsername()))
        {
            System.out.println("UserName Already Exists.. User Not Added.");
        }
        else
        {
            users.put(user.getUsername(), user);
            System.out.println(user.getRole() + " " + user.getUsername() + " added.");
        }
    }

    public User login(String username, String password) 
    {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) 
        {
            return user;
        } 
        else 
        {
            return null;
        }
    }
    public void logout(User user) 
    {
        user.setStatus(false);
        System.out.println("Logged out successfully.");
    }
}
