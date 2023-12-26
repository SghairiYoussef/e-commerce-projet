import java.util.Scanner;

public class Admin extends User
{
    public Admin(String username, String password, AuthenticationService AS, ProductManagement products) 
    {
        super(username, password, AS, products);
    }

    public String getRole() 
    {
        return "Admin";
    }

    public void addProduct(Scanner scanner) throws Exception
    {
        Product newProduct;
        Product produit=null;
        System.out.println("Enter product details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Category: ");
        System.out.println("1. Clothing");
        System.out.println("2. Electronics");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        while (true)
        {
            boolean verif=true;
            switch (choice) {
                case 1:
                    for (clothingTYPE type : clothingTYPE.values()) 
                    {
                        System.out.println(type.name());
                    }
                    System.out.print("Enter clothing type to filter: ");
                    String inputType = scanner.nextLine();
                    clothingTYPE selectedType;
                    selectedType = clothingTYPE.valueOf(inputType.toUpperCase());
                    produit = new ClothingProduct(name, price, quantity, selectedType);
                    break;
                case 2:
                    for (electronicsTYPE type : electronicsTYPE.values()) {
                        System.out.println(type.name());
                    }
                    System.out.print("Enter electronics type to filter: ");
                    String inputEType = scanner.nextLine();
                    electronicsTYPE selectedEType;
                    selectedEType = electronicsTYPE.valueOf(inputEType.toUpperCase());
                    produit = new ElectronicsProduct(name, price, quantity, selectedEType);
                    break;
            
                default:
                    System.out.println("Wrong Input.");
                    verif=false;
                    break;
            }
            if (verif)break;
        }
        if (produit!=null)
        {
            newProduct=produit;
            Admin.getProductManagement().addProduct(newProduct); 
        }
        else
        {
            System.out.println("There has been a problem adding the product.");
        } 
    }

    public void removeProduct(Scanner scanner) throws Exception
    {
        System.out.print("Enter the name of the product to remove: ");
        String productName = scanner.nextLine();
        Product productToRemove = getProductManagement().findProductByName(productName);
        if (productToRemove != null) {
            User.getProductManagement().removeProduct(productToRemove);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void addProductQuantity(Scanner scanner) throws Exception
    {
        System.out.print("Enter the name of the product: ");
        String productName = scanner.nextLine();
        System.out.print("Enter quantity to add: ");
        int quantityToAdd = scanner.nextInt();
        scanner.nextLine();

        Product product = getProductManagement().findProductByName(productName);
        if (product != null) {
            Admin.getProductManagement().addProductQuantity(product, quantityToAdd);
        } else {
            System.out.println("Product not found.");
        }
        
    }

    public void displayProducts()
    {
        Admin.getProductManagement().displayProducts();
    }

    public void reviewReturnRequests(Scanner scanner)
    {
        if (getProductManagement().getReturnRequests().isEmpty())
        {
            System.out.println("NO REQUESTS AVAILABLE FOR NOW..");
        }
        else
        {
            for (ReturnRequest request : getProductManagement().getReturnRequests()) 
            {
                if (!request.isApproved()) 
                {
                    try
                    {
                        System.out.println(request.toString());
                        System.out.println("Will You Approve This Request?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        System.out.println("Enter Your choice: ");
                        int choice=scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) 
                        {
                            case 1:
                                request.getProduct().setQuantity(request.getProduct().getQuantity()+1);
                                request.setApproved(true);
                                String message="Your Request to return "+ request.getProduct().getName() + " was Approved.\n an amount of "+ request.getProduct().getPrice()+" has been sent to your account.";
                                request.getClient().addMessage(message);
                                request.getClient().recharge(request.getProduct().getPrice());
                                break;
                            case 2:
                                getProductManagement().removeRequest(request);
                                request.getClient().addMessage("Your Request to return "+ request.getProduct().getName() + " was denied.");
                                return;
                            default:
                                System.out.println("Wrong Input.");
                                break;
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Input ERROR."+e.getMessage());
                        scanner.nextLine();
                    }
                }
            }
        }
        
    }
}