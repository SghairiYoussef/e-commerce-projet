public interface Product 
{
    String getName();
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);
    void addReview(String Reviews,double rating);
    double getRating();
    void getReviews();
    boolean equals(Product p);
}
