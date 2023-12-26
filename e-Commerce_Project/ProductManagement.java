import java.util.ArrayList;
import java.util.List;

public class ProductManagement 
{
    private List<Product> products;
    private List<ReturnRequest> returnRequests;

    public ProductManagement() 
    {
        products = new ArrayList<>();
        returnRequests = new ArrayList<>();
    }

    public List<ReturnRequest> getReturnRequests()
    {
        return returnRequests;
    }

    public void removeRequest(ReturnRequest request)
    {
        returnRequests.remove(request);
    }
    public void addReturnRequest(ReturnRequest request)
    {
        returnRequests.add(request);
    }

    public List<Product> getProducts()
    {
        return products;
    }

    public void addProduct(Product product) 
    {

        products.add(product);
        System.out.println("Product '" + product.getName() + "' added.");
    }
    
    public void removeProduct(Product product)
    {
        products.remove(product);
        System.out.println("Product '"+product.getName() +"' removed Successfully!");
    }

    public void addProductQuantity(Product product, int Quantity)
    {
        boolean v=false;
        for (Product p:products)
        {
            if (p.getName()==product.getName())
            {
                p.setQuantity(p.getQuantity()+Quantity);
                v=true;
            }
        }
        if (!v) System.out.println("Product Not Found!");
    }

    public void deductProductQuantity(Product product, int Quantity)
    {
        boolean v=false;
        for (Product p:products)
        {
            if (p.getName()==product.getName())
            {
                if (p.getQuantity()>=Quantity)
                    {
                        p.setQuantity(p.getQuantity()-Quantity);
                    }
                else
                {
                    System.out.println("Insufficent Quantity in Stock..");
                }
                v=true;
            }
        }
        if (!v) System.out.println("Product Not Found!");
    }

    public void displayProducts()
    {
        if (products.isEmpty()) 
        {
            System.out.println("No products available.");
        } 
        else 
        {
            System.out.println("Available Products:");
            for (Product product : products) 
            {
                System.out.println("Name: " + product.getName() +
                        ", Price: $" + product.getPrice() +
                        ", Quantity: " + product.getQuantity() + 
                        ", RATING: "+ product.getRating());
            }
        }
    }

    public Product findProductByName(String name) 
    {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> findProductByPartialName(String searchQuery) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) 
        {
            if (product.getName().toLowerCase().contains(searchQuery.toLowerCase())) 
            {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }
}