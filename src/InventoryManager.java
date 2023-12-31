import java.util.ArrayList;

public class InventoryManager {
    // The inventory is an array list of product cards
    // Each product card contains a product and its quantity
    private final ArrayList<ProductCard> inventory = new ArrayList<>();
    // The product manager is used to create, update products independently of the inventory
    private final ProductManager productManager = new ProductManager();
    private final ShoppingCart shoppingCart = new ShoppingCart();
    // The coupon manager is used to create, deactivate and display coupons
    private final CouponManager couponManager = new CouponManager();
    private User currentUser;
    public User getCurrentUser() {
        return currentUser;
    }
    public CouponManager getCouponManager() {
        return couponManager;
    }
    public void setCurrentUser(User anotherUser) {
        this.currentUser = anotherUser;
    }
    public ArrayList<ProductCard> getInventory() {
        return inventory;
    }
    public ProductCard getProductCardById(int id) {
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                return productCard;
            }
        }
        return null;
    }
    private int readProductId(){
        // Read the product id from the user
        System.out.println("Enter product id: ");
        int id = -1;
        if(Main.scanner.hasNextInt()){
            id = Main.scanner.nextInt();
        }
        Main.scanner.nextLine();
        return id;
    }
    public void addProduct() {
        // Create a new product and add it to the inventory
        Product product = productManager.createProduct();
        if(product == null){
            return;
        }
        ProductCard productCard = new ProductCard(product, 0);
        inventory.add(productCard);
    }
    public void displayProduct() {
        // Read the product id from the user
        int id = readProductId();
        // Display the product information
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void updateProduct() {
        // Read the product id from the user
        int id = readProductId();
        // Update the product information
        for (ProductCard productCard : inventory) {
            // If the product id matches, update the product
            if (productCard.getProduct().getId() == id) {
                productManager.updateProduct(productCard.getProduct());
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void deleteProduct() {
        // Read the product id from the user
        int id = readProductId();
        for (ProductCard productCard : inventory) {
            // If the product id matches, delete the product from the inventory
            if (productCard.getProduct().getId() == id) {
                inventory.remove(productCard);
                System.out.println("Product deleted.");
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Products in inventory:");
            for (ProductCard productCard : inventory) {
                productCard.displayInfo();
            }
        }
    }
    public void modifyQuantityProduct() {
        // Read the product id from the user
        int id = readProductId();
        for (ProductCard productCard : inventory) {
            // If the product id matches, update the quantity
            if (productCard.getProduct().getId() == id) {
                System.out.println("Enter quantity (Old quantity: " + productCard.getQuantity() + "): ");
                int quantity = -1;
                if(Main.scanner.hasNextInt()){
                    quantity = Main.scanner.nextInt();
                }
                Main.scanner.nextLine();
                if(quantity < 0){
                    System.out.println("Invalid quantity.");
                    return;
                }
                productCard.setQuantity(quantity);
                System.out.println("Quantity updated.");
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void searchProductMenu(){
        System.out.println("1. Search by id");
        System.out.println("2. Search by name");
        System.out.println("3. Search by price range");
        System.out.println("4. Search by category");
        System.out.print("Your choice: ");
        int choice = -1;
        if(Main.scanner.hasNextInt()){
            choice = Main.scanner.nextInt();
        }
        Main.scanner.nextLine();
        switch (choice){
            case 1:
                searchProductById();
                break;
            case 2:
                searchProductByName();
                break;
            case 3:
                searchProductByPriceRange();
                break;
            case 4:
                searchProductByCategory();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    public void searchProductById() {
        System.out.println("Enter product id: ");
        int id = Main.scanner.nextInt();
        Main.scanner.nextLine();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void searchProductByName() {
        System.out.println("Enter product name: ");
        String name = Main.scanner.nextLine();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getName().equalsIgnoreCase(name)) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void searchProductByPriceRange() {
        double minPrice = -1;
        double maxPrice = -1;

        System.out.println("Enter min price: ");
        if(Main.scanner.hasNextInt() || Main.scanner.hasNextDouble()){
            minPrice = Main.scanner.nextDouble();
        }
        Main.scanner.nextLine();

        System.out.println("Enter max price: ");
        if(Main.scanner.hasNextInt() || Main.scanner.hasNextDouble()){
            maxPrice = Main.scanner.nextDouble();
        }
        Main.scanner.nextLine();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getPrice() >= minPrice && productCard.getProduct().getPrice() <= maxPrice) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void searchProductByCategory() {
        System.out.println("Enter product category: ");
        String category = Main.scanner.nextLine();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getClass().getSimpleName().equalsIgnoreCase(category)) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void inventoryMenu() {
        // If the current user is an admin, display the inventory menu
        if(currentUser.getIsAdmin()){
            while (true) {
                System.out.println("1. Add product");
                System.out.println("2. Display product");
                System.out.println("3. Update product");
                System.out.println("4. Delete product");
                System.out.println("5. Display inventory");
                System.out.println("6. Modify quantity product");
                System.out.println("7. Search product");
                System.out.println("8. Add coupon");
                System.out.println("9. Deactivate coupon");
                System.out.println("10. Display coupons");
                System.out.println("11. Back to main menu");
                System.out.print("Enter your choice: ");
                int choice = -1;
                if(Main.scanner.hasNextInt()){
                    choice = Main.scanner.nextInt();
                }
                Main.scanner.nextLine();
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        displayProduct();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        displayInventory();
                        break;
                    case 6:
                        modifyQuantityProduct();
                        break;
                    case 7:
                        searchProductMenu();
                        break;
                    case 8:
                        couponManager.createCoupon(currentUser);
                        return;
                    case 9:
                        couponManager.deactivateCoupon(currentUser);
                        return;
                    case 10:
                        couponManager.displayCoupons();
                        return;
                    case 11:
                        System.out.println("Back to main menu...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        }
        // If the current user is a customer, display the shopping cart menu
        else{
            shoppingCart.shoppingMenu(this);
        }
    }
}