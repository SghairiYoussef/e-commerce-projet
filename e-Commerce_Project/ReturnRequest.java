public class ReturnRequest 
{
    private Client client;
    private Product product;
    private String reason;
    private boolean isApproved;

    public ReturnRequest(Client Client, Product product, String reason) {
        this.client = Client;
        this.product = product;
        this.reason = reason;
        this.isApproved = false;
    }

    public String toString()
    {

        return ("ClientName: "+client.getUsername()+",Product: "+product.getName()+",reason: "+reason);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client Client) {
        this.client = Client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
