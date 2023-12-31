import java.util.ArrayList;

public class ProductCard {
    private final Product product;
    private int quantity;
    private final ArrayList<Review> reviews = new ArrayList<>();
    public ProductCard(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }
    public ArrayList<Review> getReviews() {
        return reviews;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void addReview(Review review){
        reviews.add(review);
    }
    public void displayInfo() {
        System.out.println("Product info:");
        product.displayInfo();
        System.out.println("\tQuantity: " + quantity);
        System.out.println("\tReviews: ");
        for(Review review : reviews){
            review.displayInfo();
        }
    }
}
