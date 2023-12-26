import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private List<Product> cartItems;
    private boolean isCompleted;
    private ProductManagement Products;
    private User client;

    public Cart(User client, ProductManagement products) {
        cartItems = new ArrayList<>();
        isCompleted = false;
        this.client=client;
        this.Products=products;
    }

    public void addToCart(Product product) {
        if (product.getQuantity()>0)
        {
            cartItems.add(product);
            System.out.println("Added '" + product.getName() + "' to the cart.");
            product.setQuantity(product.getQuantity()-1);
        }
        else System.out.println("Product unavailable.");

    }

    public void removeFromCart(Product product)
    {
        cartItems.remove(product);
        product.setQuantity(product.getQuantity()+1);
    }

    public void emptyCart()
    {
        cartItems.removeAll(cartItems);
    }

    public List<Product> getCartItems()
    {
        return cartItems;
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            double totalPrice=0;
            System.out.println("Cart Items:");
            for (Product product : cartItems) {
                System.out.println("Name: " + product.getName() +", Price: $" + product.getPrice());
                totalPrice+=product.getPrice();   
            }
            System.out.println("-----------------\n TOTAL= "+totalPrice);
        }
    }

    public void completeOrder(Scanner scanner)
    {
        try
        {
            ((Client) client).getClientOrder().addTransaction(this,scanner);
        }
        catch (Exception e)
        {
            System.out.println("There has been A Problem..");
        }
    }

    public Cart clone()
    {
        Cart clone=new Cart(client, Products);
        for (Product product:cartItems)
        {
            clone.cartItems.add(product);
        }
        return clone;
    }

    public double calculatePrice()
    {
        double price=0;
        for (Product p:cartItems)price+=p.getPrice();
        return price;
    }
    public boolean isCompleted() 
    {
        return isCompleted;
    }

    public void setState(boolean isCompleted)
    {
        this.isCompleted=isCompleted;
    }

    public boolean isEmpty()
    {
        return cartItems.isEmpty();
    }
}
