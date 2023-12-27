import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int idCounter = 0;
    private int id;
    private User customer;
    private List<ProductCard> productCards = new ArrayList<>();
    private double totalPrice;
    private LocalDate createdAt;
    private String status;

    public Order(User customer, List<ProductCard> productCards, double totalPrice, LocalDate createdAt, String status) {
        this.id = idCounter++;
        this.customer = customer;
        this.productCards = productCards;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.status = status;
    }
}
