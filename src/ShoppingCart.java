import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<ProductCard> cartItems = new ArrayList<>();
    public void addItemToCart(InventoryManager inventoryManager,Product product, int quantity) {
        ProductCard newProductCard = new ProductCard(product, quantity);
        for (ProductCard productCard : cartItems) {
            if (productCard.getProduct().getId() == product.getId()) {
                System.out.println("Item already exists in the cart.");
                return;
            }
        }
        // check if quantity is available in inventory
        ProductCard productCard = inventoryManager.getProductCardById(product.getId());
        if (productCard == null) {
            System.out.println("Product not found in inventory.");
            return;
        }
        if (productCard.getQuantity() < quantity) {
            System.out.println("Quantity not available in inventory.");
            return;
        }


        cartItems.add(newProductCard);
        System.out.println(product.name + " added to the cart.");
    }
    public void updateItemQuantity(Product product, int quantity) {
        for (ProductCard productCard : cartItems) {
            if (productCard.getProduct().getId() == product.getId()) {
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
    public void checkout(User currentUser) {
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
            System.out.println("Thank you for shopping with us.");
            Order order = new Order(currentUser, cartItems, total, LocalDate.now(),"Pending");
            cartItems.clear();
            currentUser.addOrder(order);
        }
    }
    public void shoppingMenu(InventoryManager inventoryManager) {
        while (true) {
            System.out.println("1. Show all products");
            System.out.println("2. Add item to cart");
            System.out.println("3. Update item quantity");
            System.out.println("4. Remove item from cart");
            System.out.println("5. Display cart items");
            System.out.println("6. Display cart items");
            System.out.println("7. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();
            Product product;
            int id;
            switch (choice) {
                case 1:
                    System.out.println("All products:");
                    inventoryManager.displayInventory();
                    break;
                case 2:
                    System.out.println("Add item to cart");
                    System.out.print("Enter product id: ");
                    id = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    product = inventoryManager.getProductCardById(id).getProduct();
                    if (product != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = Main.scanner.nextInt();
                        Main.scanner.nextLine();
                        addItemToCart(inventoryManager, product, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    System.out.println("Update item quantity");
                    System.out.print("Enter product id: ");
                    id = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    product = inventoryManager.getProductCardById(id).getProduct();
                    if (product != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = Main.scanner.nextInt();
                        Main.scanner.nextLine();
                        updateItemQuantity(product, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.println("Remove item from cart");
                    System.out.print("Enter product id: ");
                    id = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    product = inventoryManager.getProductCardById(id).getProduct();
                    if (product != null) {
                        removeItemFromCart(product);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 5:
                    System.out.println("Display cart items");
                    displayCartItems();
                    break;
                case 6:
                    checkout(inventoryManager.getCurrentUser());
                    break;
                case 7:
                    System.out.println("Exiting shopping menu...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}