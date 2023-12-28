import java.util.Scanner;

public class ConsoleApp 
{
    private static ProductManagement productManagement = new ProductManagement();
    private static AuthenticationService authenticationService = new AuthenticationService();
    
    public void MainMenu() 
    {
        // static users added.
        User admin=new Admin("admin","admin", authenticationService, productManagement);
        User client1=new Client("client", "123", authenticationService, productManagement);
        // static products added.
        Product product1= new ElectronicsProduct("ASUS", 399.99, 20, electronicsTYPE.valueOf("LAPTOP"));            
        Product product2= new ClothingProduct("HA SWEATER", 35.49, 20, clothingTYPE.valueOf("SWEATER"));
        Scanner scanner = new Scanner(System.in);
        authenticationService.addUser(admin);
        authenticationService.addUser(client1);
        productManagement.addProduct(product1);
        productManagement.addProduct(product2);
        while (true) {
            try
            {
                System.out.println("===== Welcome to Youssef Sghairi's Virtu-Cart! =====");
                System.out.println("1. Login");
                System.out.println("2. Signup");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        loginMenu(scanner);
                        break;
                    case 2:
                        signupMenu(scanner);
                        break;
                    case 3:
                        System.out.println("\n\nThanks For Your Visit!\n\t\tCome Again Soon!\n\n-===Exiting the application.===-");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("There has been a Problem with the input you've entered. Please make Sure to follow instructions.\n\t RESTARTING...");
                scanner.nextLine();
            }
        }
    }

    private static void loginMenu(Scanner scanner){
        while (true) {
            System.out.println("===== Login Menu =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Client Login");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            try
            {
            switch (choice) {
                case 1:
                    adminLogin(scanner);
                    break;
                case 2:
                    clientLogin(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println("There was a problem with the input you typed.. Please Verify and try again.");
        }
        }
    }

    private static void adminLogin(Scanner scanner) throws Exception{
        System.out.println("====Admin Login====");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User admin = authenticationService.login(username, password);

        if (admin != null && admin instanceof Admin) {
            System.out.println("Login successful as " + admin.getRole() + ".");
            admin.setStatus(true);
            adminMenu(scanner, (Admin) admin);
        } else {
            System.out.println("Invalid UserName/Password.");
        }
    }

    private static void clientLogin(Scanner scanner) throws Exception{
        System.out.println("====Client Login====");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User client = authenticationService.login(username, password);

        if (client != null && client instanceof Client) {
            System.out.println("Login successful as " + client.getRole() + ".");
            client.setStatus(true);
            clientMenu(scanner, (Client) client);
        } else {
            System.out.println("Invalid UserName/Password.");
        }
    }

    private static void adminMenu(Scanner scanner, Admin admin) throws Exception {
        while (true) {
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Add Product Quantity");
            System.out.println("4. Display Return Requests");
            System.out.println("5. Display Products");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.addProduct(scanner);
                    break;
                case 2:
                    admin.displayProducts();
                    admin.removeProduct(scanner);
                    break;
                case 3:
                    admin.displayProducts();
                    admin.addProductQuantity(scanner);
                    break;
                case 4:
                    admin.reviewReturnRequests(scanner);
                    break;
                case 5:
                    admin.displayProducts();
                    break;
                case 6:
                    authenticationService.logout(admin);
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void clientMenu(Scanner scanner, Client client) throws Exception{
        while (true) {
            System.out.println("===== Client Menu =====");
            System.out.println("1. Display Products");
            System.out.println("2. Display a Product's Reviews & add A Review");
            System.out.println("3. Display Cart");
            System.out.println("4. Add to Cart");
            System.out.println("5. Complete Order");
            System.out.println("6. Display Previous Orders");
            System.out.println("7. Request a Return");
            System.out.println("8. Your Wallet's Space");
            System.out.println("9. Check Your Messages");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    client.filterProductsByType(scanner);
                    break;
                case 2:
                    client.filterProductsByType(scanner);
                    client.consultProductReviews(scanner);
                    break;
                case 3:
                    client.displayCart();
                    break;
                case 4:
                    client.filterProductsByType(scanner);
                    client.addToCart(scanner);
                    break;
                case 5:
                    client.completeOrder(scanner);
                    break;
                case 6:
                    client.getClientOrder().displayTransactions();
                    break;
                case 7:
                    client.requestReturnForProduct(scanner);
                    break;
                case 8:
                    client.checkAccount();
                    System.out.println("Do You Want To recharge?\n1. Yes\n2. Maybe Later");
                    int recharge=scanner.nextInt();
                    scanner.nextLine();
                    if(recharge==1)
                    {
                        System.out.println("Enter the amount to recharge: ");
                        double sum=scanner.nextDouble();
                        scanner.nextLine();
                        client.recharge(sum);
                        client.checkAccount();
                    }
                    break;
                case 9:
                    client.displayMessages();
                    break;
                case 0:
                    authenticationService.logout(client);
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void signupMenu(Scanner scanner){
        while (true) {
            System.out.println("===== Signup Menu =====");
            System.out.println("1. Admin Signup");
            System.out.println("2. Client Signup");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            try{
                switch (choice) {
                    case 1:
                        adminSignup(scanner);
                        adminLogin(scanner);
                        break;
                    case 2:
                        clientSignup(scanner);
                        clientLogin(scanner);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("There Has Been A Problem Signing Up.. Please Try Again");
                scanner.nextLine();
            }
        }
    }

    private static void adminSignup(Scanner scanner)
    
    {
        try
        {
            System.out.println("===== Admin Signup =====");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User newAdmin = new Admin(username, password, authenticationService, productManagement);
            authenticationService.addUser(newAdmin);
        }
        catch (Exception e)
        {
            System.out.println("There has been a Problem Signing up");
            scanner.nextLine();
        }
    }

    private static void clientSignup(Scanner scanner) {
        try 
        {
            System.out.println("===== Client Signup =====");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            User newClient = new Client(username, password, authenticationService, productManagement);
            authenticationService.addUser(newClient);
            System.out.println("You have received $10 from Virtu-Cart as a Welcome!");
        }
        catch (Exception e)
        {
            System.out.println("There Has been a Problem Signing up..");
            scanner.nextLine();
        }
    }
}
