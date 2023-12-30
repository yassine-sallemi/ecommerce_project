import java.time.LocalDate;
import java.util.List;

public class Order {
    private static int idCounter = 0;
    private int id;
    private String customerLogin;
    private List<ProductCard> productCards;
    private double totalPrice;
    private double discount;
    private LocalDate createdAt;
    private LocalDate paidAt;
    private LocalDate cancelledAt;
    private String paymentMethod;
    private String status;

    public Order(String customerLogin, List<ProductCard> productCards, double totalPrice,double discount, LocalDate createdAt, String status) {
        this.id = idCounter++;
        this.customerLogin = customerLogin;
        this.productCards = productCards;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.status = status;
        this.discount = discount;
    }

    public void displayInfo() {
        System.out.println("Order ID: " + id);
        System.out.println("Customer login: " + customerLogin);
        System.out.println("Total price: " + totalPrice);
        System.out.println("Created at: " + createdAt);
        System.out.println("Status: " + status);
        if(status.equals("Paid")){
            System.out.println("Paid at: " + paidAt);
            System.out.println("Payment method: " + paymentMethod);
        }
        if(status.equals("Cancelled")){
            System.out.println("Cancelled at: " + cancelledAt);
        }
        if(discount != 0){
            System.out.println("Discount: " + discount*100 + "%");
        }
    }

    public int getId() {
        return id;
    }
    public List<ProductCard> getProductCards() {
        return productCards;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setPaidAt(LocalDate paidAt) {
        this.paidAt = paidAt;
    }
    public void setCancelledAt(LocalDate cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
