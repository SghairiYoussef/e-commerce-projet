import java.util.ArrayList;
import java.util.List;

public class ElectronicsProduct implements Product
{
    private String name;
    private double price;
    private int quantity;
    private electronicsTYPE type;
    List<String> Reviews;
    double Rating=-1;

    public ElectronicsProduct(String name, double price, int quantity, electronicsTYPE type) 
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.Reviews= new ArrayList<String>();
    }

    public boolean equals(Product p)
    {
        return (this.name==p.getName());
    }

    public void addReview(String review,double Rating)
    {
        if (this.Rating==-1)this.Rating=Rating;
        this.Reviews.add(review);
        this.Rating+=Rating;
        this.Rating/=2;
    }

    public double getRating()
    {
        if (Rating==-1)
        {
            System.out.println("This Product hasn't been rated yet!");
            return 0;
        }
        return Rating;
    }

    public void getReviews()
    {
        if (Reviews.isEmpty())
        {
            System.out.println("NO REVIEWS WERE ENTERED YET.");
            return;
        }
        for (String e: Reviews)
        {
            System.out.println(e+" ,\n");
        }
        System.out.println("OVERALL RATING: "+ getRating());
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public electronicsTYPE getType() {
        return type;
    }
}

