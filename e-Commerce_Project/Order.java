import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order 
{
    private User client;
    private List<Cart> Transactions;
    
    public Order(User user)
    {
        client=user;
        Transactions=new ArrayList<Cart>();
    }

    public List<Cart> getTransactions()
    {
        return Transactions;
    }

    public void displayTransactions()
    {
        for (Cart cart:Transactions)
        {
            cart.displayCart();
        }
    }

    public User getUser()
    {
        return client;
    }

    public void addTransaction(Cart completedOrder,Scanner scanner)
    {
        if (completedOrder.isEmpty())System.out.println("No Items Were Added To The Cart!");
        else
        {
            System.out.println("===== Payment =====");
            System.out.println("Total amount to pay: $"+completedOrder.calculatePrice());

            System.out.print("Enter the amount to pay: $");
            double paymentAmount = scanner.nextDouble();
            scanner.nextLine();
            try{
                double totalAmountToPay = completedOrder.calculatePrice();
                if (paymentAmount >= totalAmountToPay) {
                    double change = paymentAmount - totalAmountToPay;
                    if (((Client)client).pay(paymentAmount)==true)
                    {
                        Transactions.add(completedOrder);
                        completedOrder.setState(true);
                        System.out.println("Order completed. Thank you for shopping with us!");
                        ((Client)client).recharge(change);
                        System.out.println("Payment successful!");
                        if (change > 0) 
                        {
                            System.out.println("Change: $" + change);
                        }
                    }
                } 
                else {
                    System.out.println("Insufficient payment. Please enter the correct amount.");
                }
            }
            catch (Exception e)
            {
                System.out.println("There has been a problem.");
            }
        }
    }

    public boolean verifyProductAvailability(Product product)
    {
        for (Cart c:Transactions)
        {
            for (Product p:c.getCartItems())
            {
                if (product.equals(p))return true;
            }
        }
        return false;
    }
}
