import java.util.ArrayList;

public class User {
    private String login;
    private String password;
    private String name;
    private boolean isAdmin;
    private final ArrayList<Order> orders = new ArrayList<>();

    public User(String login, String password, String name, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Orders:");
            for (Order order : orders) {
                order.displayInfo();
            }
        }
    }
    private Order getOrder(int id){
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public void payOrder(int id, PaymentStrategy paymentStrategy) {
        Order order = getOrder(id);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        if (order.getStatus().equals("Paid")) {
            System.out.println("Order already paid.");
            return;
        }
        double totalPrice = order.getTotalPrice();
        paymentStrategy.processPayment(totalPrice);
        order.setStatus("Paid");
        order.setPaidAt(java.time.LocalDate.now());
        order.setPaymentMethod(paymentStrategy.toString());
        System.out.println("Order paid successfully.");
    }

    public void cancelOrder(int id, InventoryManager inventoryManager) {
        Order order = getOrder(id);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        if (order.getStatus().equals("Cancelled")) {
            System.out.println("Order already cancelled.");
            return;
        }
        order.setStatus("Cancelled");
        order.setCancelledAt(java.time.LocalDate.now());
        for (ProductCard productCard : order.getProductCards()) {
            for (ProductCard productCard1 : inventoryManager.getInventory()) {
                if (productCard.getProduct().getId() == productCard1.getProduct().getId()) {
                    productCard1.setQuantity(productCard1.getQuantity() + productCard.getQuantity());
                }
            }
        }
        System.out.println("Order cancelled successfully.");
    }

    Boolean havePaidProduct(ProductCard productCard) {
        for (Order order : orders) {
            if (order.getStatus().equals("Paid")) {
                for (ProductCard productCard1 : order.getProductCards()) {
                    if (productCard.getProduct().getId() == productCard1.getProduct().getId()) {
                        return true;
                    }
                }
            }
        }
        System.out.println("You have not paid for this product.");
        return false;
    }

    Boolean haveRatedProduct(ProductCard productCard) {
        for (Order order : orders) {
            if (order.getStatus().equals("Paid")) {
                for (ProductCard productCard1 : order.getProductCards()) {
                    if (productCard.getProduct().getId() == productCard1.getProduct().getId()) {
                        for (Review review : productCard1.getReviews()) {
                            if (review.getUser().getLogin().equals(login)) {
                                System.out.println("You have already rated this product.");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}