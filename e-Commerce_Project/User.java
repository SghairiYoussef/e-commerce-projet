public abstract class User
{
    private String username;
    private String password;
    private AuthenticationService AS;
    private boolean IsLoggedIn;
    private static ProductManagement Products;


    public User(String username, String password, AuthenticationService AS,ProductManagement products) {
        this.username = username;
        this.password = password;
        this.IsLoggedIn=false;
        this.AS=AS;
        User.Products=products;
    }

    public String getUsername() 
    {
        return username;
    }

    public static ProductManagement getProductManagement()
    {
        return Products;
    }

    public AuthenticationService getService()
    {
        return AS;
    }

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
    
    public void setStatus(boolean IsLoggedIn)
    {
        this.IsLoggedIn=IsLoggedIn;
    }

    public boolean getStatus()
    {
        return IsLoggedIn;
    }

    public abstract String getRole();
    public abstract void displayProducts();
}