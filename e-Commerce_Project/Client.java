import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends User
{
    private Cart currentCart;
    private Order clientOrder;
    private List<String> messages;
    private double account;

    public Client(String username, String password,AuthenticationService AS,ProductManagement products) 
    {
        super(username, password, AS, products);
        this.currentCart=new Cart(this,products);
        this.clientOrder=new Order(this);
        this.account=10.0;
        this.messages= new ArrayList<>();
    }

    public void recharge(double sum)
    {
        account+=sum;
        System.out.println("Success!");
    }

    public void checkAccount()
    {
        System.out.println("You have ---$"+account+"--- In Your Account.");
    }

    public boolean pay(double sum)
    {
        if (account>=sum)
        {
            account-=sum;
            return true;
        }
        System.out.println("Insufficent funds.");
        return false;
    }

    public double getAccount()
    {
        return account;
    }

    public void displayMessages()
    {
        if (messages.isEmpty())System.out.println("Feels kinda lonely in here, doesn't it? You have no New Messages!");
        else
        {
            System.out.println("You have "+ messages.size() + " Message(s) from our dear admins! :");
            for (String message:messages)
            {
                System.out.println(message);
            }
        }
    }

    public void addMessage(String message)
    {
        messages.add(message);
    }

    public Cart getCurrentCart() 
    {
        return currentCart;
    }

    public Order getClientOrder()
    {
        return clientOrder;
    }

    public String getRole()
    {
        return "Client";
    }
    public void displayProducts()
    {
        User.getProductManagement().displayProducts();
    }
    public void displayCart() {
        this.getCurrentCart().displayCart();
    }

    public void addToCart(Scanner scanner)
     {
        System.out.print("Enter the name of the product to add to cart: ");
        String productName = scanner.nextLine();
        try
        {
            Product productToAdd = Client.getProductManagement().findProductByName(productName);
            if (productToAdd != null) {
                this.getCurrentCart().addToCart(productToAdd);
            } else {
                System.out.println("Product not found.");
            }
        }
        catch (Exception e)
        {
            System.out.println("A problem while adding the product occured.. Please try again.");
        }
    }

    public void completeOrder(Scanner scanner) 
    {
        this.getCurrentCart().completeOrder(scanner); 
        currentCart=new Cart(this,getProductManagement());
    }

    public void filterProductsByType(Scanner scanner){
        System.out.println("Filter products by type:");
        System.out.println("1. Clothing");
        System.out.println("2. Electronics");
        System.out.println("3. Display All Products.");
        System.out.println("4. Filter By Name.");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                try {
                    filterClothingProducts(scanner);
                } catch (Exception e) {
                    System.out.println("There Has Been A problem Filtering the Products, Please Try again Later.");
                }
                break;
            case 2:
                try {
                    filterElectronicsProducts(scanner);
                } catch (Exception e) {
                    System.out.println("There Has Been A problem Filtering the Products, Please Try again Later.");
                }
                break;
            case 3:
                displayProducts();
                break;
            case 4:
                System.out.println("Enter The Product's Name: ");
                String name=scanner.nextLine();
                List<Product> matchingProducts= Client.getProductManagement().findProductByPartialName(name);
                if (matchingProducts.isEmpty()) {
                    System.out.println("No matching products found.");
                } else {
                    System.out.println("Matching Products:");
                    for (Product product : matchingProducts) {
                        System.out.println("Name: " + product.getName() + ", Price: $" + product.getPrice() +
                                ", Quantity: " + product.getQuantity() + ",RATING: "+ product.getRating());
                    }
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void filterClothingProducts(Scanner scanner){
        System.out.println("===== Clothing Products =====");
        System.out.println("Available Clothing Types:");
        for (clothingTYPE type : clothingTYPE.values()) {
            System.out.println(type.name());
        }
        System.out.print("Enter clothing type to filter: ");
        String inputType = scanner.nextLine();

        clothingTYPE selectedType;
        try {
            selectedType = clothingTYPE.valueOf(inputType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid clothing type.");
            return;
        }
        System.out.println("Filtered Clothing Products - " + selectedType.name());
        for (Product product : getProductManagement().getProducts()) {
            if (product instanceof ClothingProduct) {
                ClothingProduct clothingProduct = (ClothingProduct) product;
                if (clothingProduct.getType() == selectedType) {
                    System.out.println("Name: " + clothingProduct.getName() +
                            ", Price: $" + clothingProduct.getPrice() +
                            ", Quantity: " + clothingProduct.getQuantity() +
                            ", Type: " + clothingProduct.getType() +
                            ",RATING: "+ clothingProduct.getRating());
                }
            }
        }
    }

    private static void filterElectronicsProducts(Scanner scanner) throws Exception
    {
        System.out.println("===== Electronics Products =====");
        System.out.println("Available Electronics Types:");
        for (electronicsTYPE type : electronicsTYPE.values()) 
        {
            System.out.println(type.name());
        }
        System.out.print("Enter electronics type to filter: ");
        String inputType = scanner.nextLine();

        electronicsTYPE selectedType;
        try {
            selectedType = electronicsTYPE.valueOf(inputType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid electronics type.");
            return;
        }

        System.out.println("Filtered Electronics Products - " + selectedType.name());
        for (Product product : getProductManagement().getProducts()) {
            if (product instanceof ElectronicsProduct) {
                ElectronicsProduct electronicsProduct = (ElectronicsProduct) product;
                if (electronicsProduct.getType() == selectedType) {
                    System.out.println("Name: " + electronicsProduct.getName() +
                            ", Price: $" + electronicsProduct.getPrice() +
                            ", Quantity: " + electronicsProduct.getQuantity() +
                            ", Type: " + electronicsProduct.getType() +
                            ",RATING: "+ electronicsProduct.getRating());
                }
            }
        }
    }

    public void addReview(Scanner scanner, Product product)
    {
        System.out.println("Would You Like To Add a Review about the Product: "+product.getName()+"?");
        System.out.println("1. Yes.");
        System.out.println("2. Maybe Some Other Time.");
        int choice=scanner.nextInt();
        scanner.nextLine();
        boolean v=true;
        while (v)
        {
            switch (choice) 
            {
                case 1:
                    System.out.println("Please Write Your Review Here:");
                    String review=scanner.nextLine();
                    System.out.println("Please Rate the Product (0-5) : ");
                    int rating;
                    try
                    {
                        do
                        {
                            rating=scanner.nextInt();
                            scanner.nextLine();
                        }while (rating>5 || rating<0);
                        product.addReview(review, rating);
                        v=false;
                        System.out.println("Your Review Has Been Recorded Successfully! Thanks for The Contribution.");
                    }
                    catch (Exception e)
                    {
                        System.out.println("A problem occured..");
                        scanner.nextLine();
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Wrong Input");
                    break;
            }
        }
    }

    public void consultProductReviews(Scanner scanner)
    {
        try
        {
            System.out.println("Please Enter The name of the Product: ");
            System.out.println("Enter Your choice here:");
            String productName=scanner.nextLine();
            Product product=Client.getProductManagement().findProductByName(productName);
            product.getReviews();
            if (getClientOrder().verifyProductAvailability(product))
                addReview(scanner, product);
            else 
                System.out.println("You Can't Review A product You didn't buy..");
        }
        catch(Exception e)
        {
            System.out.println("There has Been A problem consulting The Reviews.. It's mainly a Problem in your input.. Please Try again.");
            scanner.nextLine();
        }
    }

    public void requestReturnForProduct(Scanner scanner) {
        System.out.print("Enter the name of the product to request return: ");
        String productName=scanner.nextLine();
        Product productToReturn=Client.getProductManagement().findProductByName(productName);
        
        if (productToReturn != null) {
            if (getClientOrder().verifyProductAvailability(productToReturn)) {
                System.out.println("Requesting return for: " + productName);
                System.out.println("Please Enter Your reason: ");
                String reason=scanner.nextLine();
                ReturnRequest request= new ReturnRequest(this, productToReturn, reason);
                getProductManagement().addReturnRequest(request);
            } else {
                System.out.println("You cannot request a return for a product you didn't buy.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }
}