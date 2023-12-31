import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    // The shopping cart items are stored in a list of ProductCard to keep track of the quantity of each item.
    private final List<ProductCard> cartItems = new ArrayList<>();
    public void addItemToCart(ProductCard productCardFromInventory, int quantity) {
        // Create a new ProductCard object to store in the cart.
        ProductCard newProductCard = new ProductCard(productCardFromInventory.getProduct(), quantity);
        for (ProductCard productCard : cartItems) {
            // Check if the item already exists in the cart.
            if (productCard.getProduct().getId() == productCardFromInventory.getProduct().getId()) {
                System.out.println("Item already exists in the cart.");
                return;
            }
        }
        if(productCardFromInventory.getQuantity() == 0){
            System.out.println("Product is out of stock.");
            return;
        }
        if (productCardFromInventory.getQuantity() < quantity) {
            System.out.println("Quantity not available in inventory.");
            return;
        }
        // Add the new ProductCard object to the cart.
        cartItems.add(newProductCard);
        // Update the quantity of the item in the inventory.
        productCardFromInventory.setQuantity(productCardFromInventory.getQuantity() - quantity);
        System.out.println(productCardFromInventory.getProduct().getName() + " added to the cart.");
    }
    public void updateItemQuantity(ProductCard productCardFromInventory, int quantity) {
        for (ProductCard productCard : cartItems) {
            if (productCard.getProduct().getId() == productCardFromInventory.getProduct().getId()) {
                if(productCardFromInventory.getQuantity() == 0){
                    System.out.println("Product is out of stock.");
                    return;
                }
                if (productCardFromInventory.getQuantity() < quantity) {
                    System.out.println("Quantity not available in inventory.");
                    return;
                }
                productCard.setQuantity(quantity);
                System.out.println("Item quantity updated.");
                return;
            }
        }
        System.out.println("Item not found in the cart.");
    }
    public void removeItemFromCart(Product product) {
        for (ProductCard productCard : cartItems) {
            if (productCard.getProduct().getId() == product.getId()) {
                cartItems.remove(productCard);
                System.out.println("Item removed from the cart.");
                return;
            }
        }
        System.out.println("Item not found in the cart.");
    }
    public void displayCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Items in cart:");
            for (ProductCard productCard : cartItems) {
                productCard.getProduct().displayInfo();
                System.out.println("Quantity: " + productCard.getQuantity());
            }
        }
    }
    public void checkout(User currentUser, InventoryManager inventoryManager) {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Items in cart:");
            double total = 0;
            for (ProductCard productCard : cartItems) {
                productCard.getProduct().displayInfo();
                System.out.println("Quantity: " + productCard.getQuantity());
                total += productCard.getProduct().getPrice() * productCard.getQuantity();
            }
            System.out.println("Total: $" + total);
            double discount = 0;
            System.out.println("Do you have a coupon? (Y/N)");
            String choice = Main.scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                System.out.print("Enter coupon code: ");
                String couponCodeText = Main.scanner.nextLine();
                Coupon coupon = inventoryManager.getCouponManager().getCouponByCode(couponCodeText);
                if(coupon != null){
                    discount = coupon.getDiscount();
                    total = total - (total * discount / 100);
                    System.out.println("Coupon applied successfully.");
                }
                else {
                    return;
                }
            }
            System.out.println("Thank you for shopping with us.");
            System.out.println("Go back to main menu to pay for your order!");
            Order order = new Order(currentUser.getLogin(), cartItems, total, discount, LocalDate.now(),"Pending");
            cartItems.clear();
            currentUser.addOrder(order);
        }
    }
    public void shoppingMenu(InventoryManager inventoryManager) {
        while (true) {
            System.out.println("1. Show all products");
            System.out.println("2. Rate a product");
            System.out.println("3. Add item to cart");
            System.out.println("4. Update item quantity");
            System.out.println("5. Remove item from cart");
            System.out.println("6. Display cart items");
            System.out.println("7. Checkout");
            System.out.println("8. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = -1;
            if(Main.scanner.hasNextInt()){
                choice = Main.scanner.nextInt();
            }
            Main.scanner.nextLine();
            ProductCard productCardFromInventory;
            int id = -1;
            switch (choice) {
                case 1:
                    // Display all products in the inventory.
                    inventoryManager.displayInventory();
                    break;
                case 2:
                    System.out.print("Enter product id: ");
                    if(Main.scanner.hasNextInt()){
                        id = Main.scanner.nextInt();
                    }
                    Main.scanner.nextLine();
                    productCardFromInventory = inventoryManager.getProductCardById(id);
                    // Check if the product exists in the inventory and the user has paid for it and not rated it yet.
                    if (productCardFromInventory != null && inventoryManager.getCurrentUser().havePaidProduct(productCardFromInventory) && !inventoryManager.getCurrentUser().haveRatedProduct(productCardFromInventory)) {
                        int rating = -1;
                        do {
                            System.out.print("Enter rating: ");
                            if(Main.scanner.hasNextInt()){
                                rating = Main.scanner.nextInt();
                            }
                            Main.scanner.nextLine();
                            if (rating < 1 || rating > 5) {
                                System.out.println("Invalid rating (should be between 1 and 5)");
                            }
                        } while (rating < 1 || rating > 5);
                        System.out.print("Enter comment: ");
                        String comment = Main.scanner.nextLine();
                        productCardFromInventory.addReview(new Review(rating,comment,inventoryManager.getCurrentUser()));
                        System.out.println("Product rated successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter product id: ");
                    if(Main.scanner.hasNextInt()){
                        id = Main.scanner.nextInt();
                    }
                    Main.scanner.nextLine();
                    productCardFromInventory = inventoryManager.getProductCardById(id);
                    if (productCardFromInventory != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = -1;
                        if(Main.scanner.hasNextInt()){
                            quantity = Main.scanner.nextInt();
                        }
                        Main.scanner.nextLine();
                        if(quantity < 1){
                            System.out.println("Invalid quantity.");
                            break;
                        }
                        addItemToCart(productCardFromInventory, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter product id: ");
                    if(Main.scanner.hasNextInt()){
                        id = Main.scanner.nextInt();
                    }
                    Main.scanner.nextLine();
                    productCardFromInventory = inventoryManager.getProductCardById(id);
                    if (productCardFromInventory != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = -1;
                        if(Main.scanner.hasNextInt()){
                            quantity = Main.scanner.nextInt();
                        }
                        Main.scanner.nextLine();
                        if(quantity < 1){
                            System.out.println("Invalid quantity.");
                            break;
                        }
                        updateItemQuantity(productCardFromInventory, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter product id: ");
                    if(Main.scanner.hasNextInt()){
                        id = Main.scanner.nextInt();
                    }
                    Main.scanner.nextLine();
                    productCardFromInventory = inventoryManager.getProductCardById(id);
                    if (productCardFromInventory != null) {
                        removeItemFromCart(productCardFromInventory.getProduct());
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 6:
                    displayCartItems();
                    break;
                case 7:
                    checkout(inventoryManager.getCurrentUser(), inventoryManager);
                    break;
                case 8:
                    System.out.println("Exiting shopping menu...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}